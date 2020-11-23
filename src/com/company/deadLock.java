public class deadLock {

    public static void main(String[]args) {
        Object lockA = new Object();
        Object lockB = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockA) {
                    System.out.println(name + "got lockA,want LockB");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {
                        System.out.println(name + "got lockB");
                        System.out.println(name + ": say Hello!");
                    }
                }
            }
        }, "线程-A").start();

        new Thread(new Thread() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                synchronized (lockB) {
                    System.out.println(name + "got lockB,want LockA");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockA) {
                        System.out.println(name + "got lockA");
                        System.out.println(name + ": say Hello!");
                    }
                }
            }
        }, "线程-B").start();
    }
    Thread temp=new Thread();
}
