package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class BlockQueue {
  private Object[] items = new Object[100];
  private final Object notEmpty = new Object();
  private final Object notFull = new Object();
  AtomicInteger count = new AtomicInteger(0);
  int putidx,takeidx;

  public  void put(Object obj) throws InterruptedException{
    synchronized(notFull){
      while(count.intValue() == items.length){
        notFull.wait();
      }
    }
    items[putidx] = obj;
    System.out.println("存入"+putidx+"个元素");
    if(++putidx == items.length){
      putidx = 0;
    }
    count.addAndGet(1);
    synchronized (notEmpty) {
      notEmpty.notify();
    }
  }
  public Object take() throws InterruptedException{
    synchronized(notEmpty){
      while(count.intValue() == 0){ // 啥也没有呢 取啥
        notEmpty.wait();
      }
    }
    Object x = items[takeidx];
    System.out.println("取第"+takeidx+"个元素"+x);
    if(++takeidx == items.length){
      takeidx = 0;
    }
    count.addAndGet(-1);
    synchronized (notFull) {
      notFull.notify();
    }
    return x;
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
