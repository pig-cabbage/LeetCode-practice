package 代理模式.静态代理;

//代理对象
public class UserDaoProxy implements IUseDao {
    //接收保存目标对象
    private IUseDao target;
    public UserDaoProxy(IUseDao target){
        this.target=target;
    }
    public void save(){
        System.out.println("开始事务");
        target.save();
        System.out.println("结束事务");
    }


//public static void main(String[]args){
//    //目标对象
//    UserDao target=new UserDao();
//
//    //代理对象，把目标对象传给代理对象,建立代理关系
//    UserDaoProxy proxy=new UserDaoProxy(target);
//    proxy.save();
}
