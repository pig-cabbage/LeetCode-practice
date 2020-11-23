package com.company.工厂模式;

import java.util.HashMap;
import java.util.Map;

public class 静态工厂加反射 {
    private static Map<String, Bean> factory = new HashMap<>();
    static {
        factory.put("Item1", new Item1());
        factory.put("item2", new Item2());
    }
//使用类名通过反射获得对应的
public  Bean getBean(String str) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    return factory.get(str);

}

public  static void main(String[]args){
    静态工厂加反射 temp=new 静态工厂加反射();
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
