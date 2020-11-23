package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class 深度优先检索 {
    private int result=1;
    //地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
    public int movingCount(int m, int n, int k) {

        boolean[][] flag = new boolean[m][n];
        int[][] fangfa = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        flag[0][0] = true;
        for (int i = 0; i < 4; i++) {
            int newI = fangfa[i][0];
            int newJ = fangfa[i][1];
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && flag[newI][newJ] == false && isBig(newI, newJ, k)) {
                result += 1;
                flag[newI][newJ] = true;
                dfs(flag, newI, newJ, fangfa, m, n, k);
            }
        }
        return result;


    }

    public void dfs(boolean[][] flag, int i, int j, int[][] fangfa, int m, int n, int k) {

        for (int a = 0; a < 4; a++) {
            int newI = i + fangfa[a][0];
            int newJ = j + fangfa[a][1];
            if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && flag[newI][newJ] == false && isBig(newI, newJ, k)) {

                flag[newI][newJ] = true;
                result += 1;
                dfs(flag, newI, newJ, fangfa, m, n, k);


            }
        }


    }

    public boolean isBig(int i, int j, int k) {
        int huni = i / 100;
        i %= 100;
        int teni = i / 10;
        i %= 10;
        int hunj = j / 100;
        j %= 100;
        int tenj = j / 10;
        j %= 10;
        if (k >= huni + hunj + teni + tenj + i + j)
            return true;
        else
            return false;
    }
    //找二维字符数组中找出匹配的字符串
    public boolean exist(char[][] board, String word) {
        int m=board.length;
        int n=board[0].length;
        int len =word.length();
        boolean[][]flag=new boolean[m][n];
        int[][]fangfa={{-1,0},{0,1},{1,0},{0,-1}};
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(board[i][j]==word.charAt(0)){
                    flag[i][j]=true;
                    if(dfs(board,flag,i,j,1,len,fangfa,m,n,word)){
                        return true;
                    }

                }
                flag[i][j]=false;
            }
        }
        return false;

    }
    public boolean dfs(char[][]board,boolean[][]flag,int i,int j,int length,int len,int[][]fangfa,int m,int n,String word){
        if(length==len){
            return true;
        }else{
            for(int a=0;a<4;a++){
                int newI=i+fangfa[a][0];
                int newJ=j+fangfa[a][1];

                if(newI>=0 && newI<m && newJ>=0 && newJ<n && flag[newI][newJ]==false && board[newI][newJ]==word.charAt(length)){

                    flag[newI][newJ]=true;
                    if(dfs(board,flag,newI,newJ,length+1,len,fangfa,m,n,word))
                        return true;
                    flag[newI][newJ]=false;
                }
            }
        }
        return false;

    }
    //二维数组到目标的所有可能路径
    int [][] pos={{-1,0},{0,1},{1,0},{0,-1}};
    int count=0;
    public int search(int [][]grid){
        if(grid==null || grid.length==0 || grid[0].length==0){
            return 0;
        }
        int m=grid.length;
        int n=grid[0].length;
        int ans=0;
        int s1=0;
        int s2=0;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if(grid[i][j]==1){
                    s1=i;
                    s2=j;
                }
                if(grid[i][j]==0){
                    ans+=1;
                }

            }
        }
        boolean[][] flag=new boolean[m][n];
        flag[s1][s2]=true;

        dfs1(grid,flag,s1+1,s2,0,ans);
        dfs1(grid,flag,s1,s2+1,0,ans);
        dfs1(grid,flag,s1-1,s2,0,ans);
        dfs1(grid,flag,s1,s2-1,0,ans);
        return count;
    }
    public void dfs1(int[][] grid,boolean[][] flag,int cur1,int cur2,int curK,int ans){
        if(cur1<grid.length && cur1<grid[0].length && cur1>=0 && cur2>=0){
            if(grid[cur1][cur2]==2 && curK==ans){
                count+=1;
                return;

            }
            for(int i=0;i<4;i++){
                if(!flag[cur1][cur2] && grid[cur1][cur2]==0){
                    flag[cur1][cur2]=true;
                    curK+=1;
                    dfs1(grid,flag,cur1+pos[i][0],cur2+pos[i][1],curK,ans);
                    flag[cur1][cur2]=false;

                }
            }
        }
    }
    //输入一个字符串，打印出该字符串中字符的所有排列。
    //深度优先算法,结果保存到Set里面起到去重的作用，也可以在深度搜索的时候截肢去重
    public String[] permutation(String s) {
        Set<String>result=new HashSet<>();
        StringBuffer temp=new StringBuffer();
        boolean[]flag=new boolean[s.length()];
        dfsString(s,temp,flag,result);
        String[]sum=new String[result.size()];
        int i=0;
        for(String a:result){
            sum[i]=a;
            i++;
        }
        return sum;
    }
    public void dfsString(String s, StringBuffer temp, boolean[]flag, Set<String>result){
        if(temp.length()==s.length()){
            result.add(temp.toString());
        }else{

            for(int i=0;i<s.length();i++){
                if(flag[i]==false){
                    temp.append(s.charAt(i));
                    flag[i]=true;
                    dfsString(s,temp,flag,result);
                    flag[i]=false;
                    temp.deleteCharAt(temp.length()-1);
                }
            }
        }
    }

    //输入一个数组，找出其中和为目标数的子数组
    //典型的深度优先搜索
    public ArrayList<ArrayList<Integer>>resultList=new ArrayList<>();

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target){
        Arrays.sort(num);
        ArrayList<Integer>l=new ArrayList<>();
        dfsList(num,target,0,l,0);
        return new ArrayList<>(resultList);
    }
    public void dfsList(int[]input,int target,int temp,ArrayList<Integer>k,int index){
        if(temp==target){
            ArrayList<Integer>newList=new ArrayList<>();
            newList.addAll(k);
            resultList.add(newList);
        }
        if(temp > target){
            return;
        }
        else{
            for(int a=index;a<input.length;a++){
                if(a > index && input[a] == input[a-1]){
                    continue;
                }
                if(input[a] <= target){
                    k.add(input[a]);
                    temp+=input[a];
                    dfsList(input,target,temp,k,a+1);
                    k.remove(k.size()-1);
                    temp-=input[a];
                }
            }
        }
    }
    //现在有一个仅包含‘X’和‘O’的二维板，请捕获所有的被‘X’包围的区域
    //捕获一个被包围区域的方法是将被包围区域中的所有‘O’变成‘X’
    public void solve(char[][] board) {
        int m=board.length;
        int n=board[0].length;
        int[][]fangfa={{-1,0},{0,1},{1,0},{0,-1}};
        boolean[][]visited=new boolean[m][n];
        char [][]newBoard=new char[m][n];
        for(int i=0;i<m;i++){
            for(int j=0; j<n;j++){
                newBoard[i][j]='X';
                visited[i][j]=false;
            }
        }
        for(int j=0;j<n;j++){
            if(board[0][j]=='O'&&visited[0][j]==false){
                visited[0][j]=true;
                newBoard[0][j]='O';
                dfs(board,newBoard,visited,fangfa,0,j,m,n);
            }
        }
        for(int j=0;j<n;j++){
            if(board[m-1][j]=='O'&&visited[m-1][j]==false){
                visited[m-1][j]=true;
                newBoard[m-1][j]='O';
                dfs(board,newBoard,visited,fangfa,m-1,j,m,n);
            }
        }
        for(int i=0;i<m;i++){
            if(board[i][0]=='O'&&visited[i][0]==false){
                visited[i][0]=true;
                newBoard[i][0]='O';
                dfs(board,newBoard,visited,fangfa,i,0,m,n);
            }
        }
        for(int i=0;i<m;i++){
            if(board[i][n-1]=='O'&&visited[i][n-1]==false){
                visited[i][n-1]=true;
                newBoard[i][n-1]='O';
                dfs(board,newBoard,visited,fangfa,i,n-1,m,n);
            }
        }
        for(int i=0;i<m;i++){
            System.out.println(Arrays.toString(newBoard[i]));
        }
        board=newBoard;
        for(int i=0;i<m;i++){
            System.out.println(Arrays.toString(board[i]));
        }


    }
    public void dfs(char[][]board,char[][]newBoard,boolean[][]visited,int[][]fangfa,int i,int j,int m,int n){
        for(int a=0;a<4;a++){
            int newI=i+fangfa[a][0];
            int newJ=j+fangfa[a][1];
            if(newI>=0&&newI<m&&newJ>=0&&newJ<n&&visited[newI][newJ]==false&&board[newI][newJ]=='O'){
                newBoard[newI][newJ]='O';
                visited[newI][newJ]=true;
                dfs(board,newBoard,visited,fangfa,newI,newJ,m,n);
            }
        }
    }

    //现在有一个没有重复元素的整数集合S，求S的所有子集
    private ArrayList<ArrayList<Integer>> resultSub = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> subsets(int[] S) {

        resultSub.add(new ArrayList<>());
        for(int i = 1; i<=S.length; i++){
            for(int j = 0;j<S.length;j++){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(S[j]);
                dfs(S, i, temp, j);
            }
        }
        return resultSub;
    }
    public void dfs(int[]S, int len, ArrayList<Integer> temp ,int index ){
        if(temp.size() == len){
            ArrayList<Integer>temp1 = new ArrayList<>();
            temp1.addAll(temp);
            resultSub.add(temp1);
            return ;
        }else{
            for(int j = index + 1; j<S.length;j++){
                temp.add(S[j]);
                dfs(S, len, temp, j);
                temp.remove(temp.size()-1);
            }
        }
        return;
    }
    //思考“n-queens”问题
    //返回n皇后的所有摆放方案
    ArrayList<String[]> result1 = new ArrayList<>();
    StringBuilder o =new StringBuilder();
    public ArrayList<String[]> totalQueens(int n){
        for(int i = 0;i<n;i++){
            o.append('.');
        }
        int index = 0;
        for(int i = 0;i<n;i++){
            StringBuilder s = new StringBuilder();
            s.append(o);
            s.setCharAt(i, 'Q');
            String[] q = new String[n];
            q[index] = s.toString();
            List<List<Integer>>temp = new ArrayList<>();
            temp.add(new ArrayList<>(Arrays.asList(index, i)));
            dfs(temp, n, index+1, q);
        }
        return result1;
    }
    public void dfs(List<List<Integer>>temp, int n, int index, String[] q ){
        if(temp.size()==n){
            List<String> q1 = new ArrayList<>(Arrays.asList(q));

        }else{
            for(int i = 0;i<n;i++){
                boolean flag = true;
                for (List<Integer> integers : temp) {
                    if (Math.abs(integers.get(0) - index) == Math.abs(integers.get(1) - i) ||
                      integers.get(0) == index || integers.get(1) == i) {
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    temp.add(new ArrayList<>(Arrays.asList(index, i)));
                    StringBuilder s = new StringBuilder();
                    s.append(o);
                    s.setCharAt(i, 'Q');
                    q[index] = s.toString();
                    dfs(temp, n, index+1, q);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }
    //解决数独问题
    public void solveSudoku(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][]board1 = new int[m][n];
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(board[i][j] != '.'){
                    board1[i][j] = board[i][j] - '0';
                }
            }
        }
        for(int i = 0;i < m; i++){
            for(int j = 0;j < n;j++){
                if(board1[i][j] == 0){
                    Set<Integer> temp = new HashSet<>(Arrays.stream(board1[i]).boxed().collect(Collectors.toList()));
                    for(int l = 0;l<9;l++){
                        temp.add(board1[l][j]);
                    }
                    for(int d = i/3*3;d<i/3*3+3;d++){
                        for(int f = j/3*3;f<j/3*3+3;f++){
                            temp.add(board1[d][f]);
                        }
                    }
                    for(int k = 1; k<=9;k++){
                        if(!temp.contains(k)){
                            board1[i][j] = k;
                            if(dfs22(board1, i, j)){
                                for(int p = 0;p<m;p++){
                                    for(int q = 0;q<n;q++){
                                        board[p][q] = String.valueOf(board1[p][q]).charAt(0);
                                    }
                                }
                                return ;
                            }
                        }
                    }
                }
            }
        }

    }
    public boolean dfs22(int[][] board1, int a ,int b){
            for (int i = a; i < 9; i++) {
                for (int j = b; j < 9; j++) {
                    if (board1[i][j] == 0) {
                        Set<Integer> temp = new HashSet<>(Arrays.stream(board1[i]).boxed().collect(Collectors.toList()));
                        for (int l = 0; l < 9; l++) {
                            temp.add(board1[l][j]);
                        }
                        for(int m = i/3*3;m<i/3*3+3;m++){
                            for(int n = j/3*3;n<j/3*3+3;n++){
                                temp.add(board1[m][n]);
                            }
                        }
                        for (int k = 1; k <= 9; k++) {
                            if (!temp.contains(k)) {
                                board1[i][j] = k;
                                if (dfs22(board1, i, j)) {
                                    return true;
                                }
                                    board1[i][j]=0;

                            }
                        }
                        return false;
                    } else {
                        if (j < 8) {
                            if (dfs22(board1, i, j + 1))
                                return true;
                            else{
                                return false;
                            }
                        } else {
                            if (dfs22(board1, i + 1, 0))
                                return true;
                            else{
                                return false;
                            }
                        }
                    }

                }
            }
            return true;
    }
    //判断给定的数独是否符合规则
    public boolean isValidSudoku(char[][] board) {
        for(int i = 0;i<9;i++){
            Set<Integer> temp1 = new HashSet<>();
            Set<Integer> temp2 = new HashSet<>();
            for(int j =0;j<9;j++){
                if(temp1.contains(board[i][i]-'0')){
                    return false;
                }else{
                    temp1.add(board[i][j]-'0');
                }
                if(temp2.contains(board[j][i] - '0')){
                    return false;
                }else{
                    temp2.add(board[j][i] - '0');
                }
            }
        }
        for(int i = 0;i<3;i++){
            for(int j = 0;j<3;j++){
                Set<Integer> temp3 = new HashSet<>();
                for(int m = i/3*3;m<i/3*3+3;m++){
                    for(int n = j/3*3;n<j/3*3+3;n++){
                        if(temp3.contains(board[m][n] - '0')){
                            return false;
                        }else{
                            temp3.add(board[m][n] - '0');
                        }
                    }
                }
            }
        }
        return true;

    }





}
