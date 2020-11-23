package com.company;

import java.io.*;
import java.util.*;


public class �ַ��� {
    //�ж�һ���ַ����Ƿ��ʾ��ֵ��
    //��������������

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
    //���������ַ���s1, s2, s3,�ж�s3�Ƿ������s1��s2��֯���ɡ�
    //���磺
    //������������㷨
    public boolean isInterleave (String s1, String s2, String s3) {
        // write code here
        char[] s1L = s1.toCharArray();
        char[] s2L = s2.toCharArray();
        char[] s3L = s3.toCharArray();
        if(s3L.length!=s1L.length+s2L.length)
            return false;
        return dfs(s1L,s2L,s3L, 0, 0, 0);
        
        
    }
    public boolean dfs(char[] s1, char[]s2, char[]s3, int a, int b, int c){
        System.out.print(a);
        System.out.print(b);
        System.out.println(c);
        if(c >= s3.length)
            return true;
        if(a>=s1.length && b<s2.length && s2[b]!=s3[c])
            return false;
        if(b>=s2.length && a<s1.length && s1[a]!=s3[c])
            return false;
        if (a< s1.length && s3[c] == s1[a]) {
                if (dfs(s1, s2, s3, a + 1, b, c + 1))
                    return true;
            }
            if (b<s2.length && s3[c] == s2[b] ) {
                if (dfs(s1, s2, s3, a, b + 1, c + 1))
                    return true;
            }
            return false;
        
        
    }
    //������һ��ֻ�������ֵ��ַ����������ַ���ת����IP��ַ����ʽ���������п��ܵ������
    //���磺
    //�������ַ���Ϊ"25525511135",
    //����["255.255.11.135", "255.255.111.35"]. (˳��û�й�ϵ)
    //������ȣ����ݣ��㷨����;���˵�����255����0��ͷ�ҳ��ȴ���1�����
    ArrayList<String>result2=new ArrayList<>();
    public ArrayList<String> restoreIpAddresses (String s) throws IOException {
        // write code here
        StringBuffer q =new StringBuffer();
        dfs(q, 0, s,0);
        return result2;
    }
    public void dfs(StringBuffer s, int index, String q, int pp){
        if(index==4 && pp==q.length()){
            StringBuffer newS = new StringBuffer();
            newS.append(s.toString());
            newS.deleteCharAt(0);
            result2.add(newS.toString());
            return;
        }else{
            for(int i=1;i<=3;i++){
                if(pp+i<=q.length()) {
                    String p = q.substring(pp, pp + i);
                    if(Integer.valueOf(p)<=255 && !(p.startsWith("0")&&i>1)) {
                        s.append("." + p);
                        dfs(s, index + 1, q, pp + i);
                        s.delete(s.length() - p.length() - 1, s.length());
                    }
                }
            }
        }
        
    }
    //һ����������ĸ��A��-��Z������Ϣ�����еķ�ʽ���ܳ�����
    //ע���������Ӵ���������0��ͷ
    private static int result1 = 0;
    public int numDecodings (String s) {
        // write code here
        if(s.equals("0") || s == null)
            return 0;
        if(s.length()==1)
            return 1;
        for (int j = 1; j <= 2; j++) {
                if (Integer.valueOf(s.substring(0, j)) <= 26 && Integer.valueOf(s.substring(0, j)) >= 1 && s.charAt(0) != '0') {
                    String k = s.substring(j, s.length());
                    dfsString(k);
                }
            }
        
        return result1;
    }
    public void dfsString(String s){
        if(s.length()<=1 && !s.equals("0")){
            result1 += 1;
            return;
        }else{
            if(s.equals("0")){
                return ;
            }
            for(int j = 1; j <= 2; j++){
                if(Integer.valueOf(s.substring(0, j)) <= 26 && Integer.valueOf(s.substring(0, j)) >= 1 && s.charAt(0) != '0') {
                    String k = s.substring(j, s.length());
                    dfsString(k);
                }
            }
        }
    }

    //���������ַ��� S��T ��Ҫ���ڵ�ʱ�临�Ӷ�����S ���ҳ���̵İ��� T�������ַ����Ӵ���
    public String minWindow (String S, String T) {
        // write code here
        int result = Integer.MAX_VALUE;
        String result1 = "";
        int start = 0;

        int len = S.length();
        Set<Character>temp = new HashSet<>();
        for(int i = 0;i<T.length();i++){
            temp.add(T.charAt(i));
        }
        while(start< len ){
            if(!temp.contains(S.charAt(start))){
                start+=1;
                continue;
            }else{
                int begin = start;
                Set<Character>temp1 = new HashSet<>();
                temp1.add(S.charAt(start));
                if(temp1.size()==temp.size()){
                    return String.valueOf(S.charAt(start));
                }
                for(int j = begin +1;j<S.length();j++){
                    if(temp.contains(S.charAt(j))){
                        temp1.add(S.charAt(j));
                    }
                    if(temp1.size() == temp.size() && temp1.size() <result){
                        result1 =S.substring(start, j+1);
                    }
                }
                start+=1;
            }
        }
        return result1;
    }
    //������������word1��word2������㽫word1ת��Ϊword2������Ҫ���ٲ�������
    //���ö�̬�滮
    //���word1�ĵ�i���ַ���word2�ĵ�j���ַ���ȣ���ô��С�༭�������dp[i-1][j-1]����С�༭����
    //���word1�ĵ�i���ַ���word2�ĵ�j���ַ�����ȣ���ô��С�༭�����������ֵ�е���С�ߣ�
    //word1��ǰi-1���ַ���word2��ǰj-1���ַ�����༭����+1�����룩
    //word1��ǰi-1���ַ���word2��ǰj���ַ�����༭����+1��ɾ����
    //word1��ǰi���ַ���word2��ǰj-1���ַ�����༭����+1�����룩
    //��׼1: ���word1Ϊ�գ���ô��word2�ı༭����Ϊword2�ĳ��ȣ��������룩
    //��׼2: ���word2Ϊ�գ���ô��word2�ı༭����Ϊword1�ĳ��ȣ�����ɾ����
    public int minDistance (String word1, String word2) {
        // write code here
        if(word1.length() == 0 && word2.length() == 0)
            return 0;
        if(word1.length() == 0)
            return word2.length();
        if(word2.length()==0)
            return word1.length();
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0;i<=word2.length();i++){
            dp[0][i] = i;
        }
        for(int i = 0;i<=word1.length();i++){
            dp[i][0] = i;
        }
        for(int i = 1; i<=word1.length();i++){
            for(int j = 1; j<=word2.length();j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j-1]+1, Math.min(dp[i-1][j]+1, dp[i][j-1]+1));
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
    //��򻯸�����Unix��ʽ���ļ�����·����Ҳ����ת���ɹ淶·��
    //�ļ�·�� = "/home/", =>"/home"
    //�ļ�·�� = "/a/./b/../../c/", =>"/c"
    public String simplifyPath (String path) {
        // write code here
        List<String> result = new ArrayList<>();
        result.add("/");
        String[] temp = path.split("/");
        for(String item : temp){
            switch (item){
                case ".":
                    break;
                case "..":
                    if(result.size()>=2) {
                        result.remove(result.size() - 1);
                    }
                    break;
                case "":
                    break;
                default:
                    result.add(item + "/");
                    break;
            }
        }
        StringBuffer result1 = new StringBuffer();
        for(int i = 0; i<result.size();i++){
            result1.append(result.get(i));
        }
        if(result1.toString().equals(""))
            return "/";
        else {
            if (result.size() >= 2) {
                result1.deleteCharAt(result1.length() - 1);
            }
            return result1.toString();
        }
    }
    //�жϸ������ַ����Ƿ�������
    public boolean isNumber(String s) {
        if(s == null || s.length()==0){
            return false;
        }
        s = s.trim();
        int index =0;
        boolean numExist = false;
        boolean numExistAfterE = false;
        boolean pointExist = false;
        boolean eExist = false;
        while(index<s.length()){
            if(s.charAt(index) == '-' || s.charAt(index) == '+'){
                if(index == 0 || s.charAt(index-1) == 'e' || s.charAt(index-1) == 'E'){//�Ӽ���ֻ���ܳ�������ǰ����߽�����e��E
//����
                    index ++;
                }
                else{
                    return false;
                }
            }
            else if(s.charAt(index) >= '0' && s.charAt(index) <= '9'){
                numExist = true;
                numExistAfterE = true;
                index++;
            }
            else if(s.charAt(index) == 'e' || s.charAt(index) == 'E'){
//e��Eֻ�ܳ��������ֺ��棬�������һ��Ҫ������
                if(eExist || !numExist){
//e�Ѿ����ֹ�����eû�г��ֹ�������ǰ��û�����ֶ�����false
                    return false;
                }
                eExist = true;
                numExistAfterE = false;//��ǰ�ڵ�Ϊe������󲻿��ܳ������֣���numExisAfterE��ֵΪfalse
                index++;
            }
            else if(s.charAt(index) == '.'){
//��дfalse��������������Ѿ����ڻ��ߵ��û���ڵ���e�Ѿ����ڣ��򷵻�false����Ϊeֻ�ܳ����ڵ�ź��棬����1.2e10
                if(pointExist || eExist){
                    return false;
                }
                pointExist = true;
                index ++;
            }
            else{//�����������ַ���Ϊfalse
                return false;
            }
        }
        return numExist&&numExistAfterE;

    }

    //�����������ַ�����ʾ�Ķ����������������ǵĺͣ�Ҳ���ַ�����ʾ��
    public String addBinary (String a, String b) {
        // write code here
        StringBuffer result = new StringBuffer();
        StringBuffer newA = new StringBuffer();
        newA.append(a);
        StringBuffer newB = new StringBuffer();
        newB.append(b);
        newA.reverse();
        newB.reverse();
        int lenA = newA.length();
        int lenB = newB.length();
        int index = 0;
        int help = 0;
        while(true){
            if(index >=lenA && index >=lenB)
                break;
            if(index < lenA && index <lenB){
                int temp = Integer.valueOf(newA.charAt(index) - '0') + Integer.valueOf( newB.charAt(index) - '0') + help;
                if(temp >= 2){
                    result.append(temp - 2);
                    help = 1;
                    index++;
                }else{
                    result.append(String.valueOf(temp));
                    help = 0;
                    index++;
                }
            }else{
                if(index >= lenA){
                    int temp = Integer.valueOf( newB.charAt(index) - '0') +help;
                    if(temp == 2){
                        result.append("0");
                        help = 1;
                        index++;
                    }else{
                        result.append(String.valueOf(temp));
                        help = 0;
                        index++;
                    }
                }else{
                    int temp = Integer.valueOf( newA.charAt(index) - '0') +help;
                    if(temp == 2){
                        result.append("0");
                        help = 1;
                        index++;
                    }else{
                        result.append(String.valueOf(temp));
                        help = 0;
                        index++;
                    }
                }
            }
        }
        if(help ==1)
            result.append("1");
        return result.reverse().toString();
    }
    //��ʵ��֧��'?'and'*'.��ͨ���ģʽƥ��
    //������*���ţ��������������㷨
    //�����������ַ�����ʾ�����֣����������ֵĳ˻���Ϊ�ַ������ء�
    //��ע�����ֿ������޴����ǷǸ�����
    public String multiply (String num1, String num2) {
        // write code here
        StringBuilder a = new StringBuilder();
        a.append(num1);
        StringBuilder b = new StringBuilder();
        b.append(num2);
        a.reverse();
        b.reverse();
        if(b.length() > a.length()){
            StringBuilder temp = b;
            b = a;
            a = temp;
        }
        List<String> qq = new ArrayList<>();
        StringBuilder lin = new StringBuilder();
        for(int i = 0;i<b.length();i++){
            StringBuilder temp = new StringBuilder();
            int jin = 0;
            for(int j = 0;j<a.length();j++){
                if(b.charAt(i) == '0') {
                    temp.append('0');
                    break;
                }
                int re = (b.charAt(i) - '0') * (a.charAt(j) - '0') + jin;
                    jin = re/10;
                    temp.append(re%10);

            }
            if(jin > 0){
                temp.append(jin);
            }
            temp.reverse();
            temp.append(lin);
            lin.append('0');
            temp.reverse();
            StringBuilder k = new StringBuilder();
            k.append(temp);
            qq.add(k.toString());
        }
        System.out.println(qq.toString());
        StringBuilder result = new StringBuilder();
        int index = 0;
        int jin = 0;
        while(index < qq.get(qq.size() - 1).length()) {
            int temp = jin;
            for (String item : qq){
                if(index < item.length()){
                    temp += item.charAt(index) -'0';
                }
            }
            jin = temp/10;
            result.append(temp%10);
            index += 1;
        }
        if(jin > 0){
            result.append(jin);
        }
        return result.reverse().toString();
    }
    //������е�ǰ�������£�
    //
    //1, 11, 21, 1211, 111221, ...
    //1������1��1����11
    //11������2��1������21
    //21������1��2��1��1������1211
    //����һ������n����������еĵ�n��
    public String countAndSay (int n) {
        // write code here
        List<String> temp = new LinkedList<>();
        temp.add("1");
        for(int i =2 ;i<=n;i++){
            StringBuilder q = new StringBuilder();
            String before = temp.get(i-2);
            for(int j = 0; j<before.length();){
                char k = before.charAt(j);
                int time =0;
                while(j <before.length() -1){
                    if(before.charAt(j) == before.charAt(j + 1)){
                        time += 1;
                        j+=1;
                        }else{
                            break;
                        }
                    }
                j+=1;
                    q.append(String.valueOf(time + 1) + k);

            }
            temp.add(q.toString());
        }
        return temp.get(temp.size() - 1);

    }
    //����һ���������ַ�'('��')'���ַ�����������ĸ�ʽ��ȷ�������Ӵ��ĳ��ȡ�
    public int longestValidParentheses (String s) {
        // write code here

        Stack<Character> temp = new Stack<>();
        char[] cs = s.toCharArray();
        int result = Integer.MIN_VALUE;
        for(int i = 0;i < cs.length;){
            char p = cs[i];
            if(p == '(') {
                temp.add(p);
                i++;
            }
            else{
                if(!temp.isEmpty() && temp.peek() == '('){
                    int q = 2;
                    temp.pop();
                    i+=1;
                    for(;i<cs.length;){
                        char w = cs[i];
                        if(w == '(') {
                            temp.add(w);
                            i++;
                        }else{
                            if(!temp.isEmpty() &&temp.peek() == '('){
                                q +=2;
                                System.out.print(Arrays.toString(temp.toArray()));
                                System.out.print(q);
                                System.out.println(" " + i);
                                temp.pop();
                                i++;
                            }else{
                                temp.add(w);
                                break;
                            }
                        }
                    }
                    result = Math.max(result, q);

                }else{
                    temp.add(p);
                }
                i+=1;
            }
        }
        if(result == Integer.MIN_VALUE)
            return 0;
        return result;
    }
    //����n�����ţ����дһ���������������е���n��������ɵĺϷ���ϡ�
    //���˼·�� ������ȣ���֦����ǰ�������������������������
    public ArrayList<String> result222 = new ArrayList<>();
    public ArrayList<String> generateParenthesis (int n) {
        // write code here
        StringBuilder temp = new StringBuilder();
        dfs222(temp, 0, 0, n);
        return result222;
    }
    public void dfs222(StringBuilder temp, int zuo, int you, int n){
        if(zuo == n && you == n){
            result222.add(temp.toString());
        }else{
            if(zuo > you){
                if(zuo < n) {
                    temp.append("(");
                    dfs222(temp, zuo + 1, you, n);
                    temp.deleteCharAt(temp.length() - 1);
                }
                temp.append(")");
                dfs222(temp, zuo, you+1, n);
                temp.deleteCharAt(temp.length() - 1);
            }if(zuo == you){
                temp.append("(");
                dfs222(temp, zuo+1, you, n);
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }
    //����һ���������ַ�'(',')','{','}','['��']',���ַ������жϸ������ַ����Ƿ��ǺϷ�����������
    //ʹ��ջ
    public boolean isValid (String s) {
        // write code here
        if(s.equals(""))
            return true;
        Stack<Character> temp = new Stack<>();
        temp.add(s.charAt(0));
        for(int i = 1; i < s.length();i++){
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                temp.add(c);
            }else{
                switch (c){
                    case ')':
                        if(temp.isEmpty() || temp.peek() != '(')
                            return false;
                        else
                            temp.pop();
                        break;
                    case ']':
                        if(temp.isEmpty() || temp.peek() != '[')
                            return false;
                        else
                            temp.pop();
                        break;
                    case '}':
                        if(temp.isEmpty() || temp.peek() != '{')
                            return false;
                        else
                            temp.pop();
                        break;
                    default:
                        break;
                }
            }
        }
        return temp.isEmpty();
    }
    //����һ���ַ������ҳ���Ĳ������ظ��ַ����Ӵ��ĳ��ȡ����磬��abcabcbb���������ظ��ַ�����Ӵ��ǡ�abc��������Ϊ3�����ڡ�bbbbb������Ĳ������ظ��ַ����Ӵ��ǡ�b��������Ϊ1��
    public int lengthOfLongestSubstring (String s) {
        // write code here
        int result = Integer.MIN_VALUE;
        if(s.equals(""))
            return 0;
        else{

            for(int i = 0;  i < s.length(); i++){
                Set<Character> temp = new HashSet<>();
                int total = 0;
                int index = i;
                while(index < s.length() && !temp.contains(s.charAt(index))){
                    temp.add(s.charAt(index));
                    index +=1;
                    total +=1;

                }
                result = Math.max(result, total);
                System.out.print(temp.toString());
            }
        }
        return result;
    }
    //�ҳ��������ַ���S����Ļ����Ӵ�������S����󳤶�Ϊ1000������ֻ����Ψһ�⡣
    //������������ö�̬�滮 dp[i][j]��ʾ��i��j���Ӵ��ǲ��ǻ����ַ�������Ҫʹ��reverse�� ���reverse������logn��ʱ�临�Ӷ�
    //Ҫע��ѭ����˳���Ӵ�����Ҫ�ɶ̵���
    public String longestPalindrome (String s) {
        // write code here
        int len = s.length();

        if(len == 0 || len == 1)
            return s;
        else{
            String result = s.substring(0, 1);
            boolean[][]dp = new boolean[len][len];
            for(int i = 0; i < len; i++){
                dp[i][i] = true;
            }
            for(int j = 1; j < len ; j++){
                for(int i = 0; i + j < len; i++){
                    if(j == 1){
                        if(s.charAt(i) == s.charAt(i + j)){
                            dp[i][i + j] = true;
                        }else{
                            dp[i][i + j] = false;
                        }
                    }else{
                        dp[i][i + j] = s.charAt(i) == s.charAt(i + j) && dp[i + 1][i + j -1];

                    }
                    if(dp[i][i + j]){
                        if(j+1 > result.length()){
                            result = s.substring(i, i + j + 1);
                        }
                    }
                 }
            }
            return result;
        }

    }
    //�ַ���"ZXYZXLISHIRING"д��3�е�Z���ε���ʽ���£�
    //Z   X   H   N
    //X Z L S I I G
    //Y   I   R
    //���ж����Z����ͼ��Ӧ���� "ZXHNXZLSIIGYIR"
    //���д������ɽ��ַ���ת��Ϊָ��������Z�����ַ�����
    public String convert (String s, int nRows) {
        // write code here
        if(nRows == 1)
            return s;
        else{
            int k = (nRows - 2) * 2 + 2;
            int time = s.length()/k;
            int yv = s.length()%k;
            Character[][] temp = new Character[nRows][(time + 1) * (nRows - 1) ];
            int o = 0;
            for(int i = 0; i < time; i++){
                int index = 0;
                while(index < k){
                    if(index < nRows){
                        temp[index][i * (nRows - 1)] = s.charAt(o);
                    }else{
                        temp[nRows - (index - nRows + 1) - 1][i * (nRows - 1) + index -nRows + 1] = s.charAt(o);
                    }
                    index +=1;
                    o +=1;
                }
            }
            if(yv > 0){
                int index = 0;
                while(index < yv){
                    if(index < nRows){
                        temp[index][time * (nRows - 1)] = s.charAt(o);
                    }else{
                        temp[nRows - (index - nRows + 1) - 1][time * (nRows - 1) + index -nRows + 1] = s.charAt(o);
                    }
                    index +=1;
                    o +=1;
                }
            }
            StringBuilder result = new StringBuilder();
            for(int i = 0; i < nRows;i++){
                for(int j = 0; j<(time + 1) * (nRows - 1); j+= nRows -1){
                    if(temp[i][j] != null)
                        result.append(temp[i][j]);
                    if(i == 0 || i == nRows - 1)
                        continue;
                    if( temp[i][j + nRows - i - 1] != null){
                        result.append(temp[i][j + nRows - i - 1]);
                    }

                }
            }
            return result.toString();
        }
    }
    //���������ַ���str1��str2,��������ַ�����������Ӵ������������Ӵ�Ϊ�գ����-1��
    //���ö�̬�滮,dp[i][j]
    public String LCS (String str1, String str2) {
        // write code here
        if(str1.length() == 0 || str2.length() == 0){
            return "-1";
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int start1 = -1;
        int start2 = -1;
        int[][] dp = new int[s1.length][s2.length];
        int max = 0;
        for(int i=0; i<s1.length; i++){
            dp[i][0] = (s1[i] == s2[0] ? 1 : 0);
            for(int j=0; j<s2.length; j++){
                dp[0][j] = (s1[0] == s2[j] ? 1 : 0);
                if(i > 0 && j > 0){
                    if(s1[i] == s2[j]){
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                }
                if(max < dp[i][j]){
                    max = dp[i][j];
                    start1 = i + 1 - max;
                    start2 = j + 1 - max;
                }
            }
        }
        if(max == 0) return "-1";
        return str1.substring(start1, max+start1);
    }

    //���ַ�������ʽ�����������֣���дһ�������������ǵĺͣ����ַ�����ʽ���ء�
    public String solve(String s, String t){
        StringBuilder s1 = new StringBuilder(s).reverse();
        StringBuilder t1 = new StringBuilder(t).reverse();
        int index = 0;
        int jin = 0;
        StringBuilder result = new StringBuilder();
        while(index < s1.length() && index < t1.length()){
            int temp = s1.charAt(index) - '0' + t1.charAt(index) - '0' + jin;
            if(temp>=10){
                jin = 1;
            }else{
                jin = 0;
            }
            result.append(temp%10);
            index++;
        }
        while(index < s1.length()){
            int temp = s1.charAt(index) - '0' + jin;
            if(temp>=10){
                jin = 1;
            }else{
                jin = 0;
            }
            result.append(temp%10);
            index++;
        }
        while(index < t1.length()){
            int temp = t1.charAt(index) - '0' + jin;
            if(temp>=10){
                jin = 1;
            }else{
                jin = 0;
            }
            result.append(temp%10);
            index++;
        }
        if(jin == 1)
            result.append(1);
        return result.reverse().toString();
    }

    ArrayList<String> resultsss = new ArrayList<>();
    //����һ���ַ���,���ֵ����ӡ�����ַ������ַ����������С����������ַ���abc,���ֵ����ӡ�����ַ�a,b,c�������г����������ַ���abc,acb,bac,bca,cab��cba��
    public ArrayList<String> Permutation(String str) {
        if(str == null || str.equals("")){
            return resultsss;
        }else{
            int len = str.length();
            Set<Character> temp = new HashSet<>();
            char[] strs = str.toCharArray();
            Arrays.sort(strs);
            StringBuilder temp1 = new StringBuilder(new String(strs));
            for(int i = 0; i < temp1.length(); i++){
                if(!temp.contains(temp1.charAt(i))){
                    temp.add(temp1.charAt(i));
                    StringBuilder temp2 = new StringBuilder(String.valueOf(temp1.charAt(i)));
                    dfs(temp2, new StringBuilder(temp1).deleteCharAt(i), len);
                }
            }
            return resultsss;
        }

    }
    public void dfs(StringBuilder temp2, StringBuilder temp3, int len){
        if(temp2.length() == len){
            resultsss.add(new StringBuilder(temp2).toString());

        }else{
            Set<Character> temp = new HashSet<>();
            for(int i = 0; i < temp3.length(); i++){
                if(!temp.contains(temp3.charAt(i))){
                    temp.add(temp3.charAt(i));
                    temp2.append(temp3.charAt(i));
                    dfs(temp2, new StringBuilder(temp3).deleteCharAt(i), len);
                    temp2.deleteCharAt(temp2.length() - 1);
                }
            }
        }
    }

}
