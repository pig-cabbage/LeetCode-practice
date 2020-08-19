import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 动态规划 {
    //爬楼梯，每次爬一阶或两阶，求爬到n阶的方案数
    //递归或者迭代的方法
    public int calculateCishu(int input) {
        int[] dp = new int[input + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= input; i++) {
            int index = 1;
            while (i - index >= 1) {
                dp[i] += dp[i - index];
                index += 1;
            }
        }
        return dp[input];
    }

    //一个数的因子只有2,3,5，求第n个这样的数
    //从小到大依次乘以2,3,5
    public int uglyNumber(int index) {
        if (index == 0) return 0;
        int p2 = 0;
        int p3 = 0;
        int p5 = 0;
        int[] res = new int[index];
        res[0] = 1;
        for (int i = 1; i < index - 1; i++) {
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p3] * 5));
            if (res[i] == res[p2] * 2) p2++;
            if (res[i] == res[p3] * 3) p3++;
            if (res[i] == res[p5] * 5) p5++;
        }
        return res[index - 1];
    }

    //将一段n米长的绳子切割成m段，每段长度都是整数
    //m段绳子的长度都平均
    public int qiege(int n, int m) {
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }
        for (int i = m + 1; i <= n; i++) {
            Arrays.sort(dp);
            dp[0] += 1;
        }
        int result = 1;
        for (int i = 0; i < m; i++)
            result *= dp[i];
        return result;
    }

    //计算输入字符串的最长的合法括号子串
    //动态规划，dp[n]表示索引到n时最长的合法括号子串
    public int maxKuoHao(String input) {
        if (input.length() == 0)
            return 0;
        int[] dp = new int[input.length()];
        int max = 0;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == ')') {
                if (input.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] : 0 + 2;
                } else {
                    if (i - dp[i - 1] > 0 && input.charAt(i - dp[i - 1] - 1) == '(')
                        dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0 + 2);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;

    }


    public int maxInt(int[][] grid) {
        int[][] df = new int[grid.length][grid.length];
        df[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    df[i][j] = df[i - 1][j] + grid[i][j];
                } else {
                    if (i == j) {
                        df[i][j] = df[i - 1][j - 1] + grid[i][j];
                    } else {
                        int max = Math.max(df[i - 1][j - 1], df[i - 1][j]);
                        df[i][j] = max + grid[i][j];
                    }
                }
            }
        }
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            if (df[grid.length - 1][i] > result)
                result = df[grid.length - 1][i];
        }
        return result;
    }

    //最长递增子序列
    //动态规划,dp[i]表示
    public void longest(int[] input) {
        int len = input.length;
        int[] df = new int[len];
        int[] index = new int[len];
        df[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            int temp = 0;
            int k = 0;
            for (int j = i + 1; j < len; j++) {
                if (df[j] >= temp && df[j] >= df[i]) {
                    temp = df[j];
                    k = j;

                }
            }
            df[i] = temp + 1;
            index[i] = k;
        }
        int result = 0;
        int result_index = 0;
        for (int i = 0; i < len; i++) {
            if (df[i] > result) {
                result = df[i];
                result_index = i;
            }
        }

    }

    //找出最大的“1”正方形
    //动态规划，df[i][j]=Math.min(df[i-1][j],Math.min(df[i][j-1],df[i-1][j-1]))+1;
    public int findMaxZhang(int[][] grid) {
        int max = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        } else {
            int[][] df = new int[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        df[i][j] = 1;
                        max = 1;
                    }

                }
            }
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[0].length; ) {
                    df[i][j] = Math.min(df[i - 1][j], Math.min(df[i][j - 1], df[i - 1][j - 1])) + 1;
                    if (df[i][j] > max)
                        max = df[i][j];
                }
            }
            return max * max;
        }
    }
    //1-n中1出现的次数
    //动态规划，如果数字为n位先计算出1位到n-1位数中一个个数，满i位数字（3位则数字应为999）的1个数为dp[i]=pow(10,i-1)+10*dp[i-1]
    //分别从高位到低位计算1的个数
    public int countDigitOne(int n) {
        if (n < 10) {
            if (n < 1)
                return 0;
            else {
                return 1;
            }
        }
        int result = 0;
        int weishu = 1;
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        while (n >= Math.pow(10, weishu)) {
            temp.add((int) Math.pow(10, weishu) + 10 * temp.get(temp.size() - 1));
            weishu += 1;
        }
        temp.remove(temp.size() - 1);
        String str_n = String.valueOf(n);
        StringBuffer Str_n = new StringBuffer();
        Str_n.append(str_n);
        while (Str_n.length() != 0) {
            if (Str_n.length() == 1) {
                if (Integer.parseInt(Str_n.substring(0, 1)) >= 1) {
                    result += 1;
                    break;
                } else {
                    break;
                }

            } else {
                char k = Str_n.charAt(0);

                if (k > '1') {
                    result += ((k - '0') * temp.get(temp.size() - 1) + Math.pow(10, Str_n.length() - 1));
                    System.out.println(result);
                } else {
                    if (k == '1')
                        result += Integer.parseInt(Str_n.substring(1)) + 1 + temp.get(temp.size() - 1);
                }
                temp.remove(temp.size() - 1);
                Str_n.deleteCharAt(0);


            }

        }
        return result;


    }
    //在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
    //运用动态规划，dp[i][j]表示到达第i行第j列时获得礼物的最大值，dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+dp[i][j]
    public int maxValue(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int[][]dp=new int[m][n];
        dp[0][0]=grid[0][0];
        for(int i=1;i<n;i++){
            dp[0][i]=dp[0][i-1]+dp[0][i];
        }
        for(int i=1;i<m;i++){
            dp[i][0]=dp[i-1][0]+dp[i][0];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1])+dp[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    //给出一个三角形，计算从三角形顶部到底部的最小路径和，每一步都可以移动到下面一行相邻的数字，
    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        int len=triangle.get(triangle.size()-1).size();
        int [][]dp=new int[len][len];
        for(int i=1;i<len;i++){
            dp[i][0]=dp[i-1][0]+triangle.get(i).get(0);
            dp[i][i]=dp[i-1][i-1]+triangle.get(i).get(i);
        }

        for(int i=1;i<len;i++){
            for(int j=1;j<i;j++){
                dp[i][j]=Math.min(dp[i-1][j-1],dp[i-1][j])+triangle.get(i).get(j);
            }
        }
        int result=Integer.MAX_VALUE;
        for(int i=0;i<len;i++){
            result=Math.min(dp[len-1][i],result);
        }
        return result;

    }
}
