import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class test1 {
    private int result=1;
    public int movingCount(int m, int n, int k) {

        boolean[][]flag=new boolean[m][n];
        int[][]fangfa={{-1,0},{0,1},{1,0},{0,-1}};
        flag[0][0]=true;
        for(int i=0;i<4;i++){
            int newI=fangfa[i][0];
            int newJ=fangfa[i][1];
            if(newI>=0 && newI<m && newJ>=0 && newJ<n &&flag[newI][newJ]==false&&isBig(newI,newJ,k)){
                result+=1;
                flag[newI][newJ]=true;
                dfs(flag,newI,newJ,fangfa,m,n,k);
            }
        }
        return result;



    }
    public void dfs(boolean[][]flag,int i,int j,int[][]fangfa,int m,int n,int k){

        for(int a=0;a<4;a++){
            int newI=i+fangfa[a][0];
            int newJ=j+fangfa[a][1];
            if(newI>=0 && newI<m && newJ>=0 && newJ<n && flag[newI][newJ]==false && isBig(newI,newJ,k)){

                flag[newI][newJ]=true;
                result+=1;
                dfs(flag,newI,newJ,fangfa,m,n,k);


            }
        }


    }
    public boolean isBig(int i,int j,int k){
        int huni=i/100;
        i%=100;
        int teni=i/10;
        i%=10;
        int hunj=j/100;
        j%=100;
        int tenj=j/10;
        j%=10;
        if(k>=huni+hunj+teni+tenj+i+j)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> input=new ArrayList<>();
        int len=sc.nextInt();
        for(int i=0;i<len;i++){
            input.add(sc.nextInt());
        }
        input.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int a=input.get(0);
        int b=input.get(1);
        for(int i=2;i<len;i++){
            if(a>b){
                b+=input.get(i);
            }else{
                a+=input.get(i);
            }
        }
        System.out.println(input.toString());
        System.out.println( Math.max(a,b));
    }
}
