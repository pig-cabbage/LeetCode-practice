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
    public int minCut (String s) {
        // write code here

        int a=min(s);

        StringBuffer temp=new StringBuffer();
        temp.append(s);
        temp=temp.reverse();
        int b=min(temp.toString());
        return a<=b?a:b;

    }
    public int min(String s){
        if(isHuiWen(s)){
            return 0;
        }
        int size=s.length();
        int result=0;
        int end=1;

        while(size>1) {
            for (int i = size; i >= 1; i--) {
                if (isHuiWen(s.substring(0, i))) {
                    end = i;
                    result += 1;
                    break;
                }
            }

            s=s.substring(end,size);
            size=s.length();
        }
        return result;

    }
    public boolean isHuiWen(String s){

        StringBuffer temp=new StringBuffer();
        temp.append(s);
        temp=temp.reverse();
        if(s.equals(temp.toString())){
            return true;
        }
        return false;
    }

    public static void main(String[]args){
        �ַ��� temp=new �ַ���();
        String s="add";

        System.out.println(temp.minCut(s));
    }
}
