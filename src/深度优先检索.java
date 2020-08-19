import javax.swing.tree.TreeNode;
import java.lang.reflect.Array;
import java.util.*;

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
    public List<List<Integer>>resultList=new ArrayList<>();

    public List<List<Integer>> getTargetList(int[]input,int target){
        boolean[]flag=new boolean[input.length];
        List<Integer>l=new ArrayList<>();
        dfsList(input,flag,target,0,l,0);
        return resultList;
    }
    public void dfsList(int[]input,boolean[]flag,int target,int temp,List<Integer>k,int index){
        if(temp==target){
            List<Integer>newList=new ArrayList<>();
            newList.addAll(k);
            resultList.add(newList);
        }
        else{
           for(int a=index;a<input.length;a++){
               if(flag[a]==false){
                   flag[a]=true;
                   k.add(input[a]);
                   temp+=input[a];
                   dfsList(input,flag,target,temp,k,a+1);
                   flag[a]=false;
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
    //给定两个单词（初始单词和目标单词）和一个单词字典，请找出所有的从初始单词到目标单词的最短转换序列的长度：
    //因为是取最短的路径，所以采用广度度优先搜索的方法
    // 不需要深度优先一样找出所有的路径，一地个满足条件的路径就是最小值。



    public static void main(String[]args){
        深度优先检索 temp=new 深度优先检索();
        String start="hot";
        String end="dog";
        HashSet<String>l=new HashSet<>();

        l.add("hot");
        l.add("cog");
        l.add("dog");
        l.add("tot");
        l.add("hog");
        l.add("hop");
        l.add("pot");
        l.add("dot");


    }

}
