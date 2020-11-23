package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class 线程相关 {
    private static Object lock = new Object();
    private static Object lock2 = new Object();
    public static volatile int index=0;
    public static AtomicInteger result=new AtomicInteger(0);
    public static AtomicBoolean flag=new AtomicBoolean(true);
    public static   AtomicInteger appleNumber=new AtomicInteger(9458);
//    public  static volatile  int kk=0;
    public void change(){
        flag.compareAndSet(true,false);
    }
    public void increase(){
        result.getAndIncrement();
        System.out.println(result.intValue());
//        kk+=1;
//        System.out.println(kk);
    }
    public void _20threads(){
        Thread[]threads=new Thread[20];
        for(int i=0;i<10;i++){
            threads[i]=new Thread(){
                public void run(){
                    for(int i=0;i<10000;i++){
                        increase();
                    }
                }
            };
            threads[i].start();
        }
    }
    //实现两个线程交替打印1到100的数字
    public void jiaotiDayin(){
        Thread thread1=new Thread() {
            public void run() {
                synchronized (lock) {
                    while(index<=1000) {
                        if (index % 2 == 1) {
                            System.out.print("Thread A: ");
                            System.out.println(index++);
                        } else {
                            lock.notify();
                            try {
                                lock.wait(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }


        };

        Thread thread2=new Thread() {
            public void run() {
                synchronized (lock) {
                    while(index<=1000) {
                        if (index % 2 == 0) {
                            System.out.print("Thread B: ");
                            System.out.println(index++);
                        } else {
                            lock.notify();
                            try {
                                lock.wait(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
        thread1.start();
        thread2.start();

    }
    public void help(int i,char[]inputList) throws InterruptedException {
            synchronized (lock) {
//                while(index>=inputList.length){
//                    Thread.currentThread().
//                }
                while (index<inputList.length&&inputList[index] - '0' != i) {
                    lock.wait();
                }
                if(index<inputList.length) {
                    System.out.println(String.format("线程%d : 打印数字%c", i, inputList[index++]));
                    lock.notifyAll();
                }
            }
    }

    //设计一个多线程打印程序，第i个线程只打印i-1数字，比如第1个线程打印数字0，第2个线程只打印数字1，依次类推。任意给定一个数字序列，比如3382019835830，能够使用该程序打印出来。
    public void dayin(String input){
        char[]inputList=input.toCharArray();
        for(int i=0;i<10;i++){
            int j=i;
            Thread temp=new Thread(){
                public void run(){
                    while(true){
                        try {
                            help(j,inputList);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            temp.start();
        }
    }

    //共计9个苹果，有2只猴子，一个猴子每次拿2个苹果，一个猴子每次拿3个苹果，如果剩余的苹果不够猴子每次拿的数量，则2只猴子停止拿苹果，请用java多线程模拟上面的描述，要求性能尽可能高效
    public void apple(){
        new Thread(()->{
                while(appleNumber.intValue()>=3){
                    synchronized (lock){
                        if(appleNumber.intValue()<3){
                            break;
                        }
                    appleNumber.compareAndSet(appleNumber.intValue(),appleNumber.intValue()-3);
                    System.out.println(String.format("吃掉3个苹果,剩余%d个苹果",appleNumber.intValue()));
                }

            }
        }).start();
        new Thread(()->{

                while(appleNumber.intValue()>=2){
                    synchronized (lock){
                        if(appleNumber.intValue()<2){
                            break;
                        }
                    appleNumber.compareAndSet(appleNumber.intValue(),appleNumber.intValue()-2);
                        System.out.println(String.format("吃掉2个苹果,剩余%d个苹果",appleNumber.intValue()));

                }

            }
        }).start();
    }
    //有4个线程，其编号分别为1-4，每个线程只能打印自身编号到文件。写一个完整的程序，控制4个线程，打印以下内容到文件
    //文件a: 1 2 3 4
    //文件b: 2 3 4 1
    //文件c: 3 4 1 2
    //文件d: 4 1 2 3
    public void write(int index) throws IOException {
        RandomAccessFile randomAccessFile=new RandomAccessFile(".\\a.txt","rw");
        long fileLength=randomAccessFile.length();
        randomAccessFile.seek(fileLength);
        randomAccessFile.writeBytes(String.valueOf(index)+" ");
        randomAccessFile.close();

    }
    public void tencent(){
        Thread thread1=new Thread(){
            public void run(){
                synchronized (lock){
                    while(true){
                        if(index>=4){
                            break;
                        }
                        if(Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)==String.valueOf(index).charAt(0)){

                            try {
                                write(index+1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            index+=1;

                        }else{
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        };
        Thread thread2=new Thread(){
            public void run(){
                synchronized (lock){
                    while(true){
                        if(index>=4){
                            break;
                        }
                        if(Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)==String.valueOf(index).charAt(0)){
                            try {
                                write(index+1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            index+=1;

                        }else{
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        };
        Thread thread3=new Thread(){
            public void run(){
                synchronized (lock){
                    while(true){
                        if(index>=4){
                            break;
                        }
                        if(Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)==String.valueOf(index).charAt(0)){
                            try {
                                write(index+1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            index+=1;

                        }else{
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        };
        Thread thread4=new Thread(){
            public void run(){
                synchronized (lock){
                    while(true){
                        if(index>=4){
                            break;
                        }
                        if(Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)==String.valueOf(index).charAt(0)){
                            try {
                                write(index+1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            index+=1;

                        }else{
                            lock.notifyAll();
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
        };
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
    public void siSuo() throws InterruptedException {
        Thread t1 = new Thread(){
            public void run(){
                synchronized (lock){
                    System.out.println("get lock");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                            System.out.println("get lock2");
                        }
                        lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2 = new Thread(){
            public void run(){
                synchronized (lock2){
                    System.out.println("get lock2");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock){
                        System.out.println("get lock");
                    }
                    lock2.notifyAll();
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        t2.start();
    }

    //三个线程交替打印ABC
    private char[] chars = {'A', 'B', 'C'};
    private volatile int i = 0;
    public void jiaoTi() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                    synchronized (lock) {
                        while (true) {
                            System.out.println("Thread 1: " + i);
                            if (i == 0) {
                                System.out.println('A');
                                i += 1;
                            } else {
                                lock.notifyAll();
                                try {
                                    lock.wait(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while(true){
                        System.out.println("Thread 2: " + i);
                        if (i == 1) {
                            System.out.println('B');
                            i += 1;
                        }
                        else {
                            lock.notifyAll();
                            try {
                                lock.wait(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    while(true){
                        System.out.println("Thread 3: " + i);
                        if (i == 2) {
                            System.out.println('C');
                            i = 0;
                        }
                        else {
                            lock.notifyAll();
                            try {
                                lock.wait(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                }
            }
        };
        t1.start();
        t2.start();
        t3.start();
    }
    public static void main(String[] args) throws InterruptedException {
        Callable<Integer> c = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        };




    }
}
