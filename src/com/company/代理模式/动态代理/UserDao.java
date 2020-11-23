package com.company.代理模式.动态代理;

//目标对象
public class UserDao implements IUseDao{
    @Override
    public void save(){
        System.out.println("执行目标对象");
    }
}
