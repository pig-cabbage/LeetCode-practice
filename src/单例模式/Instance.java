
//线程安全的懒汉模式
public class Instance {
    //加volatile防止指令重排
    private static volatile Instance instance;
    private Instance(){

    }
    public static  Instance  getInstance(){
        if(instance==null){
            synchronized (Instance.class) {
                if(instance==null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }

}
////饿汉模式
//public class Singleton1{
//    public static Singleton1 instance=new Singleton1();
//    private Singleton1(){
//
//    }
//    public Singleton1 getInstance(){
//        return instance;
//    }
//}
////通过枚举实现单例模式（最好的方法）
//public enum Singleton{
//    INSTANCE;
//
//    public void doSomething(){
//        System.out.println("doSomething");
//    }
//}
////调用方法
//public static void main(String[]args){
//    Singleton.INSTANCE.doSomething();
//}
