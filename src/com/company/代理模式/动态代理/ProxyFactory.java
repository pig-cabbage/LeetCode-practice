package com.company.����ģʽ.��̬����;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//��������,����Ҫ������ʵ�ֽӿ�
public class ProxyFactory {
    //ά��һ��Ŀ�����
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //��Ŀ��������ɴ������
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("��������");
                        Object returnValue = method.invoke(target, args);
                        System.out.println("�ر�����");
                        return returnValue;
                    }
                }
        );
    }

public static void main(String args[]){
    //Ŀ�����
    IUseDao target=new UserDao();

    //��Ŀ����󣬴����������
    IUseDao proxy=(IUseDao)new ProxyFactory(target).getProxyInstance();
    proxy.save();
}
}
