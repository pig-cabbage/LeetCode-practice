import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class �߳���� {
    public static Object lock=new Object();
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
    //ʵ�������߳̽����ӡ1��100������
    public void jiaotiDayin(){
        Thread thread1=new Thread1() {
            public void run() {
                synchronized (lock) {
                    while(index<=100) {
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

        Thread thread2=new Thread2() {
            public void run() {
                synchronized (lock) {
                    while(index<=100) {
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
                    System.out.println(String.format("�߳�%d : ��ӡ����%c", i, inputList[index++]));
                    lock.notifyAll();
                }
            }
    }

    //���һ�����̴߳�ӡ���򣬵�i���߳�ֻ��ӡi-1���֣������1���̴߳�ӡ����0����2���߳�ֻ��ӡ����1���������ơ��������һ���������У�����3382019835830���ܹ�ʹ�øó����ӡ������
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

    //����9��ƻ������2ֻ���ӣ�һ������ÿ����2��ƻ����һ������ÿ����3��ƻ�������ʣ���ƻ����������ÿ���õ���������2ֻ����ֹͣ��ƻ��������java���߳�ģ�������������Ҫ�����ܾ����ܸ�Ч
    public void apple(){
        new Thread(()->{
                while(appleNumber.intValue()>=3){
                    synchronized (lock){
                        if(appleNumber.intValue()<3){
                            break;
                        }
                    appleNumber.compareAndSet(appleNumber.intValue(),appleNumber.intValue()-3);
                    System.out.println(String.format("�Ե�3��ƻ��,ʣ��%d��ƻ��",appleNumber.intValue()));
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
                        System.out.println(String.format("�Ե�2��ƻ��,ʣ��%d��ƻ��",appleNumber.intValue()));

                }

            }
        }).start();
    }
    //��4���̣߳����ŷֱ�Ϊ1-4��ÿ���߳�ֻ�ܴ�ӡ�����ŵ��ļ���дһ�������ĳ��򣬿���4���̣߳���ӡ�������ݵ��ļ�
    //�ļ�a: 1 2 3 4
    //�ļ�b: 2 3 4 1
    //�ļ�c: 3 4 1 2
    //�ļ�d: 4 1 2 3
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
    public static void main(String[] args) {
        �߳���� test=new �߳����();
//        test.dayin("3382019835830");
//        System.out.println(�߳����.result);
        test.tencent();
        Map<String,String>k=new HashMap<>();



    }
}
