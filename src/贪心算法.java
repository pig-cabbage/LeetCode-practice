import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

public class ̰���㷨 {
    //���ַ���s�����ҵ���̵İ����ַ���t�������ַ����Ӵ�
    //���û������ڣ��ҵ�����t�����ַ����Ӵ�����С������������������и��£������mapΪ��ֵ����Ҫ��mapΪ��ֵ
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
    //��������������������
    //̰���㷨����sum<0ʱ������ֵΪ0
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
    //����������������k�����͵����ֵ
    //�������ڴ������һ���������һ�θ���һ�����ֵ
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
    //�жϻ����ַ����ĳ��ȵ����ֵ
    //ʹ��hashmap��¼���ִ���Ϊ�����ĸ��������ȼ�ȥ��Щ�������ǽ��
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
    //����һ������Ϊ n �����ӣ�������Ӽ����������ȵ� m �Σ�m��n����������n>1����m>1����ÿ�����ӵĳ��ȼ�Ϊ k[0],k[1]...k[m-1] ������ k[0]*k[1]*...*k[m-1] ���ܵ����˻��Ƕ��٣����磬�����ӵĳ�����8ʱ�����ǰ������ɳ��ȷֱ�Ϊ2��3��3�����Σ���ʱ�õ������˻���18
    //̰���㷨������ֱ����2-n��ʱ�����ֵ���������ֵ
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
    //ʵ��pow()
    //�������㷨 while(absN>0){
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
    //�Ա�����
    //̰���㷨���ҵ������ٵ����ӣ��ȳ��������ǰ�����ӵı�������ǵ�һ�����ӵı����٣���ͬʱ�������̵ı�
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
    //�������⣬����ÿһҳ����ʱ�䣬���ÿ��Сʱ���˶���ҳ������4ҳ�����false
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
    //����һ�����ֵ�������
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
    //����һ�������� target ��������к�Ϊ target ���������������У����ٺ�������������
    //
    //�����ڵ�������С�������У���ͬ���а����׸����ִ�С�������С�
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
    //�����ĳ��Ʊ�ļ۸���ʱ���Ⱥ�˳��洢�������У����������ù�Ʊһ�ο��ܻ�õ���������Ƕ��٣�
    //̰���㷨�����ϸ��»��ѵ���Сֵcost���õ�ǰprice-cost�����������
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;



    }
    //����·����n������վ����i������վ����������gas[i].
    //����һ���������������������װ���͡��Ӽ���վi�ߵ���һ������վ��i+1�����ѵ�������cost[i]�����һ������վ�������տ�ʼ��ʱ����������û�����͡�
    //����ĸ�����վ���������ڻ���·����һȦ�����ؼ���վ���±꣬���û�д𰸵Ļ�����-1��
    //ע�⣺
    //�𰸱�֤Ψһ��
    //�����߼������С�
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
    //������Ʊ��������󣬿��Խ������Σ������ĸ��û����״̬�����������£����������ĸ�״̬��
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
    //��������һ�����飬���е�i��Ԫ�ر�ʾĳֻ��Ʊ�ڵ�i��ļ۸�
    //���һ���㷨��Ѱ���������������������������Ľ���(���磬��ι���ͳ��۹�Ʊ��һ��)�����ǣ��㲻��ͬʱ���ж������(������������ٴι���֮ǰ����֮ǰ��Ĺ�Ʊ)��
    //ֻҪ�����ֵ��ǰһ���󣬾ͼ��ϲ�ֵ��
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
        ̰���㷨 k=new ̰���㷨();
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
