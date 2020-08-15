import javax.swing.tree.TreeNode;
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
    public static void main(String[]args){
        深度优先检索 temp=new 深度优先检索();
        int[]input={5,3,6,1,2,5,1,7};
        temp.getTargetList(input,15);
        System.out.println(temp.resultList);
    }

}
