import com.sun.deploy.util.StringUtils;

import java.util.*;

public class �ַ��� {
    //�ж�һ���ַ����Ƿ��ʾ��ֵ��
    //��������������
    public boolean isNumber(String s) {
        char[]chars=s.toCharArray();
        int len=chars.length;
        boolean flag=true;
        if(len==1){
            if(chars[0]>='0'&&chars[0]<='9')
                return true;
        }
        if(chars[0]=='+' && chars[1]=='-')
            return false;
        for(char i :chars){
            if(i=='.'){
                if(flag)
                    flag=false;
                else
                    return false;
            }else{
                if((i>='0'&&i<='9') ||(i=='e')||i=='E')
                    continue;
                    else{
                       if(i=='+'||i=='-'){

                       }
                }
            }
        }
        return true;

    }
    //ȥ���ַ������ظ����ַ���
    public String quChong(String input){
        StringBuffer temp=new StringBuffer();
        int len=input.length();
        for(int i=0;i<len;i++){
            if(temp.indexOf(input.substring(i,i+1))>=0){
                continue;
            }else{
                temp.append(input.charAt(i));
            }
        }
        return temp.toString();
    }
    //ѹ���ַ���
    //���磺�ַ���"xxxyyyyyyz"ѹ����ͳ�Ϊ"3x6yz"��
    public String yasupString(String input){
        StringBuffer result=new StringBuffer();
        int len=input.length();
        int index=0;
        while(index<len){
            int cishu=1;
            char k=input.charAt(index);
            while (index+1<len&&input.charAt(index)==input.charAt(index+1)){
                cishu+=1;
                index+=1;
            }
            if(cishu==1){
                result.append(k);

            }else{
                result.append(String.valueOf(cishu));
                result.append(k);
            }
            index+=1;
        }
        return result.toString();
    }

    //����ӡ�������ʽ���ַ�����������
    public int calculate(String input){
        int temp=0;
        StringBuffer str=new StringBuffer();
        char fuhao='+';
        int result=0;
        for(int i=0;i<input.length();i++){
            if(input.charAt(i)=='+'||input.charAt(i)=='-'){
                if(fuhao=='+'){
                    result+=Integer.parseInt(str.toString());
                }else{
                    result-=Integer.parseInt(str.toString());
                }
                fuhao=input.charAt(i);
                str.delete(0,str.length());
            }else{
                str.append(input.charAt(i));
            }

        }
        if(fuhao=='+'){
            result+=Integer.parseInt(str.toString());
        }else{
            result-=Integer.parseInt(str.toString());
        }
        return result;

    }
    //�ַ���ת��Ϊ����������ǰ����ֵĿո�ͺ�����ֵĿո���߷������ַ�
    //flag��ʾ�������Ƿ�����ˣ�flag1��ʾ�����Ƿ�����ˣ����ַ����ı����з����������
    public long strToInt(String str) {
        StringBuffer temp=new StringBuffer();
        temp.append(str);
        char fuhao='+';
        boolean flag=true;
        StringBuffer result=new StringBuffer();
        boolean flag1=false;
        long sum=0;
        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i)==' '){
                if(flag1)
                    break;
                if(flag==false)
                    return 0;
                continue;
            }if(!(temp.charAt(i)=='+'||temp.charAt(i)=='-'||(temp.charAt(i)>='0'&&temp.charAt(i)<='9'))){
                if(flag1){
                    break;
                }
                return 0;
            }else{
                if((temp.charAt(i)=='+'||temp.charAt(i)=='-')){
                    if(flag1){
                        break;
                    }
                    if(flag){
                        fuhao=temp.charAt(i);
                        flag=false;
                    }else{
                        return 0;
                    }
                }else{
                    result.append(temp.charAt(i));
                    flag1=true;
                }


            }
        }
        int len=result.length();

        for(int i=0;i<len;i++){
            sum+=(result.charAt(i)-'0')*Math.pow(10,len-i-1);
        }
        if(fuhao=='-'){
            if(0-sum<Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
            return (int)(0-sum);
        }else{
            if(sum>Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
            return (int)sum;
        }
    }
    //������ת��Ϊ�ַ���
    public int translateNum(int num) {
        String str=String.valueOf(num);
        int[]dp=new int[str.length()+1];
        dp[1]=1;
        if(Integer.parseInt(str.substring(0,2))<=25){
            dp[2]=2;
        }else{
            dp[2]=1;
        }
        for(int i=2;i<str.length();i++){
            if(i+1<=str.length()&&Integer.parseInt(str.substring(i-1,i+1))<=25&&Integer.parseInt(str.substring(i-1,i))!=0){
                dp[i+1]=dp[i]+dp[i-1];
            }else{
                dp[i+1]=dp[i];
            }
        }
        return dp[str.length()];

    }
    //����һ���ַ���s��һ�鵥��dict����s����ӿո�s���һ�����ӣ�ʹ�þ����е�ÿһ�����ʶ���dict�еĵ���
    //�������п��ܵĽ��
    //���磺�������ַ���s ="catsanddog",
    //dict =["cat", "cats", "and", "sand", "dog"].
    //��������㷨
//    public ArrayList<String> wordBreak(String s, Set<String> wordDict) {
//        return DFS(s, wordDict, new HashMap<String, ArrayList<String>>());
//    }
//
//    private ArrayList<String> DFS(String s, Set<String> wordDict, HashMap<String, ArrayList<String>> map) {
//        if (map.containsKey(s))
//            return map.get(s);
//        ArrayList<String> res = new ArrayList<String>();
//        if (s.length() == 0){
//            res.add("");
//            return res;
//        }
//        for (String subStr : wordDict) {
//            if (s.startsWith(subStr)) {
//                for (String str : DFS(s.substring(subStr.length()), wordDict, map)) {
//                    res.add(subStr + (str == "" ? "" : " ")+ str);
//
//                }
//
//            }
//        }
//        map.put(s, res);
//        return res;
//
//    }
    public boolean wordBreak(String s, Set<String> wordDict) {
        for(String temp:wordDict){
            if(s.startsWith(temp)){
                if(dfs(s.substring(temp.length()),wordDict)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(String s,Set<String>wordDict){
        if(s.length()==0){
            return true;
        }else{
            for(String temp:wordDict){
                if(s.startsWith(temp)){
                    if(dfs(s.substring(temp.length()),wordDict)){
                        return true;
                    }


                }
            }
            return false;
        }
    }
    //����һ���ַ���s���ָ�sʹ�÷ָ����ÿһ���Ӵ����ǻ��Ĵ�
    //���㽫�ַ���s�ָ�ɻ��ķָ�������С�и���
    //����:�����ַ���s="aab",
    //����1����Ϊ���ķָ���["aa","b"]���и�һ�����ɵġ�
    //���ö�̬�滮�ķ�����dp[i][j]��ʾ�Ӵ�i��j���ַ��Ӵ�����С�и�����ת�Ʒ���Ϊ dp[i][j] = Math.min(dp[i][j], dp[i][a] + dp[a + 1][j] + 1);
    public int minCut (String s) {
        // write code here
        int size=s.length();
        int[][]dp=new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=i;j<size;j++){
                if(isReserve(s,i,j)){

                    dp[i][j]=0;
                }else
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int k=1;k<size;k++) {
            for (int i = 0, j = k; i < size - 1&&j<size; i++, j++) {
                for (int a = i; a < j; a++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][a] + dp[a + 1][j] + 1);
                }
            }
        }
        for(int i=0;i<size;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[0][size-1];
       

    }
    //����һ���ַ���s���ָ�sʹ��s��ÿһ���Ӵ����ǻ��Ĵ�
    //�������еĻ��ķָ�������ע�⣺���ؽ����˳����Ҫ�������ַ����е���ĸ˳��һ�¡���
    //����:�����ַ���s="aab",
    //Ҫ�ҳ����еĽ���ķ���һ�㶼���������
    ArrayList<ArrayList<String>>result=new ArrayList<>();
    public ArrayList<ArrayList<String>> partition (String s) {

        int size=s.length();
        for(int i=0;i<size;i++){
            ArrayList<String>temp=new ArrayList<>();
            if(isReserve(s,0,i)){
                temp.add(s.substring(0,i+1));
                String k=s.substring(i+1,size);
                DFS(k,temp);
            }
        }
        return result;
    }
    public void DFS(String s,ArrayList<String>temp){
        int size=s.length();
        if(size==0){
            ArrayList<String>p=new ArrayList<>();
            p.addAll(temp);
            result.add(p);
            return ;
        }

        else{
            for(int i=0;i<size;i++){
                if(isReserve(s,0,i)){
                    temp.add(s.substring(0,i+1));
                    String k=s.substring(i+1,size);
                    DFS(k,temp);
                    temp.remove(temp.size()-1);

                }
            }
        }
    }
    public boolean isReserve(String s,int a,int b){
        String temp=s.substring(a,b+1);

        StringBuffer k=new StringBuffer();
        k.append(temp);
        k=k.reverse();
        if(temp.equals(k.toString())){
            return true;
        }
        return false;
    }


    public static void main(String[]args){
        �ַ��� temp=new �ַ���();
        String s="cdd";

        System.out.println(temp.partition(s).toString());
    }
}
