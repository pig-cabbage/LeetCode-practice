import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class 总结出规律 {

    //数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
    //总结规律，发现，1-满n位的字符序列长度为10+90*2+900*3+。。。+9*pow(10,n)*n
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
        总结出规律 temp=new 总结出规律();
        System.out.println(temp.findN(2889));

    }
}
