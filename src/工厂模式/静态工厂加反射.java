package 工厂模式;

public class 静态工厂加反射 {
//使用类名通过反射获得对应的
public static Bean getBean(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Bean result=(Bean)Class.forName(str).newInstance();
    return result;

}

public  static void main(String[]args){
    静态工厂加反射 temp=new 静态工厂加反射();
    try {
        Bean test=temp.getBean("工厂模式.Item1");
        test.operate();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }
}
}
