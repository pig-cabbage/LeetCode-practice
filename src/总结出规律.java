import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class �ܽ������ {

    //������0123456789101112131415���ĸ�ʽ���л���һ���ַ������С�����������У���5λ�����±�0��ʼ��������5����13λ��1����19λ��4���ȵȡ�
    //�ܽ���ɣ����֣�1-��nλ���ַ����г���Ϊ10+90*2+900*3+������+9*pow(10,n)*n
    public int findN(int target){
        if(target<=9){
            return target;
        }
        int beishu=90;
        int index=2;
        target-=9;
        while(target>beishu*index){
            target-=beishu*index;
            beishu*=10;
            index+=1;


        }



        long zhenshu=(target/index)+ (long)Math.pow(10,index-1);
        int yvshu=target%index;
        System.out.println(zhenshu);
        System.out.println(yvshu);
        if(yvshu==0){
            return Integer.parseInt(String.valueOf(zhenshu-1).charAt(index-1)+"");
        }
        return Integer.parseInt(String.valueOf(zhenshu).charAt(yvshu-1)+"");

    }

    public static  void main(String[] args){
        �ܽ������ temp=new �ܽ������();
        System.out.println(temp.findN(2889));

    }
}
