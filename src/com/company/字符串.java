package com.company;

import java.io.*;
import java.util.*;


public class 字符串 {
    //判断一个字符串是否表示数值。
    //采用有限向量机

    //去除字符串中重复的字符串
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
    //压缩字符串
    //例如：字符串"xxxyyyyyyz"压缩后就成为"3x6yz"。
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

    //输入加、减运算式的字符串，输出结果
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
    //字符串转化为整数，忽略前面出现的空格和后面出现的空格或者非数字字符
    //flag表示正负号是否出来了，flag1表示数字是否出来了，在字符串的遍历中分析各种情况
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
    //将数字转化为字符串
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
    //给定一个字符串s和一组单词dict，在s中添加空格将s变成一个句子，使得句子中的每一个单词都是dict中的单词
    //返回所有可能的结果
    //例如：给定的字符串s ="catsanddog",
    //dict =["cat", "cats", "and", "sand", "dog"].
    //深度优先算法
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
    //给出一个字符串s，分割s使得分割出的每一个子串都是回文串
    //计算将字符串s分割成回文分割结果的最小切割数
    //例如:给定字符串s="aab",
    //返回1，因为回文分割结果["aa","b"]是切割一次生成的。
    //采用动态规划的方法，dp[i][j]表示从从i到j的字符子串的最小切割数，转移方程为 dp[i][j] = Math.min(dp[i][j], dp[i][a] + dp[a + 1][j] + 1);
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
    //给定一个字符串s，分割s使得s的每一个子串都是回文串
    //返回所有的回文分割结果。（注意：返回结果的顺序需要和输入字符串中的字母顺序一致。）
    //例如:给定字符串s="aab",
    //要找出所有的结果的方法一般都是深度优先
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
    //给出三个字符串s1, s2, s3,判断s3是否可以由s1和s2交织而成。
    //例如：
    //采用深度优先算法
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
    //现在有一个只包含数字的字符串，将该字符串转化成IP地址的形式，返回所有可能的情况。
    //例如：
    //给出的字符串为"25525511135",
    //返回["255.255.11.135", "255.255.111.35"]. (顺序没有关系)
    //深度优先（回溯）算法，中途过滤掉大于255和以0开头且长度大于1的情况
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
    //一条仅包含字母‘A’-‘Z’的消息用下列的方式加密成数字
    //注意以数字子串都不能以0开头
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

    //给出两个字符串 S和T ，要求在的时间复杂度内在S 中找出最短的包含 T中所有字符的子串。
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
    //给定两个单词word1和word2，请计算将word1转换为word2至少需要多少步操作。
    //采用动态规划
    //如果word1的第i个字符和word2的第j个字符相等，那么最小编辑距离等于dp[i-1][j-1]的最小编辑距离
    //如果word1的第i个字符和word2的第j个字符不相等，那么最小编辑距离等于如下值中的最小者：
    //word1的前i-1个字符到word2的前j-1个字符所需编辑距离+1（插入）
    //word1的前i-1个字符到word2的前j个字符所需编辑距离+1（删除）
    //word1的前i个字符到word2的前j-1个字符所需编辑距离+1（插入）
    //基准1: 如果word1为空，那么到word2的编辑距离为word2的长度（持续插入）
    //基准2: 如果word2为空，那么到word2的编辑距离为word1的长度（持续删除）
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
    //请简化给出的Unix样式的文件绝对路径，也就是转换成规范路径
    //文件路径 = "/home/", =>"/home"
    //文件路径 = "/a/./b/../../c/", =>"/c"
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
    //判断给出的字符串是否是数字
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
                if(index == 0 || s.charAt(index-1) == 'e' || s.charAt(index-1) == 'E'){//加减号只可能出现在最前面或者紧挨着e、E
//后面
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
//e和E只能出现在数字后面，且其后面一定要有数字
                if(eExist || !numExist){
//e已经出现过或者e没有出现过但是其前面没有数字都返回false
                    return false;
                }
                eExist = true;
                numExistAfterE = false;//当前节点为e，故其后不可能出现数字，则numExisAfterE的值为false
                index++;
            }
            else if(s.charAt(index) == '.'){
//先写false的情况，如果点好已经存在或者点号没存在但是e已经存在，则返回false，因为e只能出现在点号后面，比如1.2e10
                if(pointExist || eExist){
                    return false;
                }
                pointExist = true;
                index ++;
            }
            else{//不是这五种字符则为false
                return false;
            }
        }
        return numExist&&numExistAfterE;

    }

    //给出两个用字符串表示的二进制数，返回他们的和（也用字符串表示）
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
    //请实现支持'?'and'*'.的通配符模式匹配
    //遇到‘*’号，则采用深度优先算法
    //给出两个用字符串表示的数字，将两个数字的乘积作为字符串返回。
    //备注：数字可以无限大，且是非负数。
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
    //外观数列的前几项如下：
    //
    //1, 11, 21, 1211, 111221, ...
    //1读作“1个1”或11
    //11读作“2个1“或者21
    //21读作”1个2，1个1“或者1211
    //给出一个整数n，请给出序列的第n项
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
    //给出一个仅包含字符'('和')'的字符串，计算最长的格式正确的括号子串的长度。
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
    //给出n对括号，请编写一个函数来生成所有的由n对括号组成的合法组合。
    //解决思路， 深度优先，剪枝：当前的左括号数必须大于右括号数
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
    //给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
    //使用栈
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
    //给定一个字符串，找出最长的不具有重复字符的子串的长度。例如，“abcabcbb”不具有重复字符的最长子串是“abc”，长度为3。对于“bbbbb”，最长的不具有重复字符的子串是“b”，长度为1。
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
    //找出给出的字符串S中最长的回文子串。假设S的最大长度为1000，并且只存在唯一解。
    //解决方法，采用动态规划 dp[i][j]表示从i到j的子串是不是回文字符串，不要使用reverse， 这个reverse会消耗logn的时间复杂度
    //要注意循环的顺序，子串长度要由短到长
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
    //字符串"ZXYZXLISHIRING"写成3行的Z字形的样式如下：
    //Z   X   H   N
    //X Z L S I I G
    //Y   I   R
    //按行读这个Z字形图案应该是 "ZXHNXZLSIIGYIR"
    //请编写代码完成将字符串转化为指定行数的Z字形字符串：
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
    //给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
    //采用动态规划,dp[i][j]
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

    //以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
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
    //输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
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
