import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class 贪心算法 {
    //在字符串s中中找到最短的包含字符串t中所有字符的子串
    //采用滑动窗口，找到包含t所有字符的子串的最小索引和最大索引，进行更新，多余的map为负值，需要的map为正值
    public String kk(String chang,String duan){
        char[]changs=chang.toCharArray();
        char[]duans=duan.toCharArray();
        int[]map=new int[128];
        for(int i=0;i<duans.length;i++){
            map[duans[i]]+=1;
        }

        int counter=duans.length;
        int begin=0;
        int end=0;
        int head=0;
        int len= Integer.MAX_VALUE;
        while(end<changs.length){
            if(map[changs[end++]]-->0) {
                --counter;
                System.out.println(end);
            }
            while(counter==0){
                if(end-begin<len){
                    len=end-begin;
                    head=begin;

                }
                if(map[changs[begin++]]++==0)
                    ++counter;
            }

        }
        return(chang.substring(head,len));
    }
    //计算数组中子数组最大和
    //贪心算法，当sum<0时把他赋值为0
    public int calculateMax(int[]input){
        int max=Integer.MIN_VALUE;
        int sum=0;
        for(int i=0;i<input.length;i++){
            sum+=input[i];
            max=sum>max? sum:max;
            if(sum<0)
                sum=0;
        }
        return max;
    }
    //求整数数组中连续k个数和的最大值
    //滑动窗口从左向右滑动，滑动一次更新一次最大值
    public int huadongChuangkou(int []intput,int k){
        int max=0;
        for(int i=0;i<k;i++)
            max+=intput[i];
        int index=1;

        while(index+k-1<intput.length){
            if(max-intput[index-1]+intput[index+k-1]>max)
                max=max-intput[index-1]+intput[index+k-1];


        }
        return max;
    }
    //判断回文字符串的长度的最大值
    //使用hashmap记录出现次数为奇数的个数，长度减去这些个数就是结果
    public int calculateHuiwen(String s){
        Map<Character,Integer> map=new HashMap<>();
        for (int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),map.get(s.charAt(i))+1);
            }else{
                map.put(s.charAt(i),1);
            }
        }
        int size=0;
        for(Map.Entry item:map.entrySet()){
            if((int)item.getValue()%2!=0){
                size+=1;

            }
        }
        if(size==0){
            return s.length();
        }else{
            return s.length()-size+1;
        }
    }
    //给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
    //贪心算法，计算分别剪成2-n段时的最大值，更新最大值
    public long cuttingRope(int n) {
        long result=0;


        for(int i=1;i<n;i++){
            int temp=i+1;
            int zhengshu=n/temp;
            int yvshu=n%temp;
            long sum=(long)Math.pow(zhengshu+1,yvshu)*(long)Math.pow(zhengshu,temp-yvshu);

            result=Math.max(result,sum);



        }

        return result%1000000007;
    }
    //实现pow()
    //快速幂算法 while(absN>0){
    //            if(absN%2==1)
    //                res*=absX;
    //            absX=absX*absX;
    //            absN/=2;
    //
    //        };
    public double myPow(double x, int n) {
        if(n==0)
            return 1.0;

        int absN=Math.abs(n);
        double absX=Math.abs(x);
        if( n==-2147483648){
            if(absX==1)
                return 1.0;
            else
                return 0;

        }
        double l=absX;
        double res=1.0;
        while(absN>0){
            if(absN%2==1)
                res*=absX;
            absX=absX*absX;
            absN/=2;

        };
        double kk=res;

        if(x<0){
            if(Math.abs(n)%2==0){
                kk=kk;
            }else{
                kk=0-kk;
            }
        }
        if(n<0){
            return 1/kk;
        }else{
            return kk;
        }

    }
    //吃饼问题
    //贪心算法，找到饼最少的盘子，先吃这个盘子前面盘子的饼，如果是第一个盘子的饼最少，则同时吃所有盘的饼
    public int chiBing(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> input = new HashMap<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            temp.add(k);
            input.put(i, k);
        }
        int result = 0;
        boolean flag=true;
        while (flag) {
            List<Map.Entry<Integer, Integer>> list_set = new ArrayList<>(input.entrySet());
            Collections.sort(list_set, new Comparator<Map.Entry<Integer,Integer>>() {
                @Override
                public int compare(Map.Entry<Integer,Integer> o1, Map.Entry<Integer,Integer> o2) {
                    if(o1.getValue()==o2.getValue()){
                        return o1.getKey()-o2.getKey();
                    }else {
                        return o1.getValue() - o2.getValue();
                    }
                }
            });
            int i = 0;
            int index = list_set.get(i).getKey();

            System.out.println(index);
            if(input.get(index)==0){
                break;
            }
            if(index==0&&input.get(index)==1){
                result+=list_set.size();
                break;
            }
            if(index==0){
                result+=list_set.get(index).getValue()*list_set.size();
                break;
            }
            result += index;


            for (int p = 0; p < index; p++) {
                input.put(p, input.get(p) - 1);
            }
            System.out.println(result);
        }
        System.out.println(result);
        return result;
    }
    //翻书问题，输入每一页看的时间，输出每个小时看了多少页，超过4页则输出false
    public void fanshu(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int temp = sc.nextInt();
            int mine = 60;
            int yeshu = 0;
            int p = yeshu;
            int o = 0;
            boolean flag=true;
            for (int j = 0; j < temp; j++) {
                int k = sc.nextInt();
                o += k;
                while (o >= mine) {
                    if (p - yeshu > 4) {
                        System.out.println(0);
                        flag=false;
                        break;
                    }
                    mine += 60;
                    yeshu = p;
                }
                p += 1;
                if(o<mine){
                    if(p-yeshu>4){
                        System.out.println(0);
                        flag=false;
                        break;
                    }
                }
            }
            if(flag)
                System.out.println(1);


        }

    }
    //计算一个数字的立方根
    public  double getCubeRoot(double input){
        double index=0;
        for(int i=0;i<input;i++){
            if(i*i*i==input){
                return (double)i;
            }else{
                if(i*i*i>input){
                    index=(double)i-1;
                    break;
                }
            }
        }
        while(true){
            if(index*index*index<input) {
                index += 0.1;
            }else{
                if(index*index*index==input){
                    return index;
                }else{
                    index-=0.1;
                    return index;
                }
            }
        }

    }
    //输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
    //
    //序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
    public Integer[][] findContinuousSequence(int target) {
        List<List<Integer>>result=new ArrayList<>();

        for(int i=1;i<target;i++){
            int temp=i;
            List<Integer>oo=new ArrayList<>();
            oo.add(temp);
            for(int j=i+1;j<target;j++){
                if(temp>target){
                    break;
                }
                if(temp==target){
                    result.add(oo);

                    break;
                }

                oo.add(j);
                temp+=j;
            }
        }
//        int len=result.size();
//        int[][]pp=new int[len][];
//        for(int i=0;i<len;i++) {
//            int[]temp=new int[result.get(i).size()];
//            for(int j=0;j<result.get(i).size();++j){
//                temp[j]=result.get(i).get(j);
//            }
//            pp[i]=temp;
//        }
        Integer[][]pp=new Integer[result.size()][];
        for(int i=0;i<result.size();i++){
            pp[i]=(Integer[])result.get(i).toArray(new Integer[result.get(i).size()]);
        }

        return pp;
    }
    //假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
    //贪心算法，不断更新花费的最小值cost，用当前price-cost保存最大收益
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;



    }
    //环形路上有n个加油站，第i个加油站的汽油量是gas[i].
    //你有一辆车，车的油箱可以无限装汽油。从加油站i走到下一个加油站（i+1）花费的油量是cost[i]，你从一个加油站出发，刚开始的时候油箱里面没有汽油。
    //求从哪个加油站出发可以在环形路上走一圈。返回加油站的下标，如果没有答案的话返回-1。
    //注意：
    //答案保证唯一。
    //按照逻辑解答就行。
    public int canCompleteCircuit (int[] gas, int[] cost) {
        // write code here
        int size=gas.length;
        int a=0;
        int b=0;
        for(int i=0;i<size;i++){
            int temp=i;
            boolean flag1=true;
            for(int j=i;j<size;j++){
                a+=gas[j];
                b+=cost[j];
                if(b>a){
                    a=0;b=0;
                    flag1=false;
                    break;
                }
            }
            if(flag1){
                boolean flag2=true;
                for(int k=0;k<temp;k++){
                    a+=gas[k];
                    b+=cost[k];
                    if(b>a){
                        a=0;
                        b=0;
                        flag2=false;
                        break;
                    }
                }
                if(flag2){
                    return temp;
                }
            }
        }
        return -1;
    }
    //买卖股票求最大利润，可以交易两次，设置四个用户余额状态量，变量更新，最后输出第四个状态量
    public int maxProfit1 (int[] prices) {
        // write code here
        int buy1=Integer.MAX_VALUE;
        int sell1=0;
        int buy2=Integer.MIN_VALUE;
        int sell2=0;
        for(int i=0;i<prices.length;i++){
            buy1=Math.min(buy1,prices[i]);
            sell1=Math.max(sell1,prices[i]-buy1);
            System.out.println(sell1);
            buy2=Math.max(buy2,sell1-prices[i]);
            System.out.println(buy2);
            sell2=Math.max(sell2,prices[i]+buy2);
        }
        return sell2;
    }
    //假设你有一个数组，其中第i个元素表示某只股票在第i天的价格。
    //设计一个算法来寻找最大的利润。你可以完成任意数量的交易(例如，多次购买和出售股票的一股)。但是，你不能同时进行多个交易(即，你必须在再次购买之前卖出之前买的股票)。
    //只要后面的值比前一个大，就加上差值。
    public int maxProfit3 (int[] prices) {
        // write code here
        if(prices==null || prices.length<2)
            return 0;
        int res=0;
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1])
                res+=prices[i]-prices[i-1];
        }
        return res;

    }


    public static void main(String[] args) {
        贪心算法 k=new 贪心算法();
        int[]input={2,1,4};
        System.out.println(k.maxProfit1(input));
//        System.out.println(Arrays.toString(k.findContinuousSequence(9)[0]));
//        Scanner sc = new Scanner(System.in);
//       String input=sc.next();
//       int len=input.length();
//       Map<Character,Integer>oo=new HashMap<>();
//       for(int i=0;i<len;i++){
//           char temp=input.charAt(i);
//           if(oo.containsKey(temp)){
//               oo.put(temp,oo.get(temp)+1);
//           }else{
//               oo.put(temp,1);
//           }
//       }
//       List<Map.Entry<Character,Integer>>list_set=new ArrayList<>(oo.entrySet());
//       Collections.sort(list_set, new Comparator<Map.Entry<Character,Integer>>() {
//           @Override
//           public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character,Integer> o2) {
//               if(o1.getValue()==o2.getValue()){
//                   return o1.getKey()-o2.getKey();
//               }else {
//                   return o2.getValue()-o1.getValue();
//               }
//           }
//       });
//       StringBuffer result=new StringBuffer();
//       for(Map.Entry<Character,Integer> item:list_set){
//           result.append(item.getKey());
//       }
//       System.out.println(result.toString());
    }
}
