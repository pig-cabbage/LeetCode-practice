package com.company.����ģʽ;

import java.util.HashMap;
import java.util.Map;

public class ��̬�����ӷ��� {
    private static Map<String, Bean> factory = new HashMap<>();
    static {
        factory.put("Item1", new Item1());
        factory.put("item2", new Item2());
    }
//ʹ������ͨ�������ö�Ӧ��
public  Bean getBean(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    return factory.get(str);

}

public  static void main(String[]args){
    ��̬�����ӷ��� temp=new ��̬�����ӷ���();
    try {
        Bean test=temp.getBean("Item1");
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
