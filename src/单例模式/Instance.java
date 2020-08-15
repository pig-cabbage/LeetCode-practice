
//�̰߳�ȫ������ģʽ
public class Instance {
    //��volatile��ָֹ������
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
////����ģʽ
//public class Singleton1{
//    public static Singleton1 instance=new Singleton1();
//    private Singleton1(){
//
//    }
//    public Singleton1 getInstance(){
//        return instance;
//    }
//}
////ͨ��ö��ʵ�ֵ���ģʽ����õķ�����
//public enum Singleton{
//    INSTANCE;
//
//    public void doSomething(){
//        System.out.println("doSomething");
//    }
//}
////���÷���
//public static void main(String[]args){
//    Singleton.INSTANCE.doSomething();
//}
