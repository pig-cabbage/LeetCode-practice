package ����ģʽ;

public class ��̬�����ӷ��� {
//ʹ������ͨ�������ö�Ӧ��
public static Bean getBean(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    Bean result=(Bean)Class.forName(str).newInstance();
    return result;

}

public  static void main(String[]args){
    ��̬�����ӷ��� temp=new ��̬�����ӷ���();
    try {
        Bean test=temp.getBean("����ģʽ.Item1");
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
