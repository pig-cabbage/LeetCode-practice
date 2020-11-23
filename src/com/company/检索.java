import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class 检索 {

    //给定一个较长字符串big和一个包含较短字符串的数组smalls，
    // 设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
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
