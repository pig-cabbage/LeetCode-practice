import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ���� {

    //����һ���ϳ��ַ���big��һ�������϶��ַ���������smalls��
    // ���һ������������smalls�е�ÿһ���϶��ַ�������big�������������smalls�е��ַ�����big����ֵ�����λ��positions������positions[i]Ϊsmalls[i]���ֵ�����λ�á�
    public List<List<Integer>> jiansuo(String big, String[] smalls){
        int length=big.length();
        List<List<Integer>>result=new LinkedList<>();

        for(String temp:smalls){
            int len=temp.length();
            List<Integer>k=new LinkedList<>();
            for(int i=0;i<length-len;i++){
                if(big.substring(i,i+len).equals(temp)){
                    k.add(i);
                }
            }
            result.add(k);
        }

    return result;
    }

}
