package ����ģʽ.��̬����;

//�������
public class UserDaoProxy implements IUseDao {
    //���ձ���Ŀ�����
    private IUseDao target;
    public UserDaoProxy(IUseDao target){
        this.target=target;
    }
    public void save(){
        System.out.println("��ʼ����");
        target.save();
        System.out.println("��������");
    }


//public static void main(String[]args){
//    //Ŀ�����
//    UserDao target=new UserDao();
//
//    //������󣬰�Ŀ����󴫸��������,���������ϵ
//    UserDaoProxy proxy=new UserDaoProxy(target);
//    proxy.save();
}
