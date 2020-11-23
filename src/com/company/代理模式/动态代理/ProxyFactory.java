package com.company.代理模式.动态代理;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//代理工厂类,不需要代理类实现接口
public class ProxyFactory {
    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //给目标对像生成代理对象
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");
                        Object returnValue = method.invoke(target, args);
                        System.out.println("关闭事务");
                        return returnValue;
                    }
                }
        );
    }

public static void main(String args[]){
    //目标对象
    IUseDao target=new UserDao();

    //给目标对象，创建代理对象
    IUseDao proxy=(IUseDao)new ProxyFactory(target).getProxyInstance();
    proxy.save();
}
}
