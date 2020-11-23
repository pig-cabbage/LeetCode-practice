package com.company;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockQueue1 {
  private Object[] items = new Object[100];
  private Lock lock = new ReentrantLock();
  private Condition notEmpty = lock.newCondition();
  private  Condition notFull = lock.newCondition();
  AtomicInteger count = new AtomicInteger(0);
  int putidx, takeidx;

  public void put(Object obj){
    lock.lock();
    try {
      while (count.intValue() == items.length){
        notFull.await();
      }
      items[putidx] = obj;
      System.out.println("存入"+putidx+"个元素");
      if(++putidx == items.length){
        putidx = 0;
      }
      count.addAndGet(1);
      notEmpty.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    finally {
      lock.unlock();
    }
  }
  public  void take(){
    lock.lock();
    try {
      while (count.intValue() == 0){
        notEmpty.await();
      }
      Object x = items[takeidx];
      System.out.println("取第"+takeidx+"个元素"+x);
      if(++takeidx == items.length){
        takeidx = 0;
      }
      count.addAndGet(-1);
      notFull.signal();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      lock.unlock();
    }
  }
  public static void main(String[] args) throws InterruptedException {
    final BlockQueue bb = new BlockQueue();
    System.out.println(Thread.currentThread()+","+bb);
    Thread a = Thread.currentThread();

    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for(int i = 0; i < 3000; i++) {
            System.out.println(Thread.currentThread() + "," + bb);
            bb.take();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          for(int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread() + "," + bb);
            bb.put("xx");
            bb.put("yy");
            bb.put("zz");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}
