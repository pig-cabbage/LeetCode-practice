import java.util.*;

public class 数组 {
    //二分查找
    public int findIndex(int []input,int target){
        int start=0;
        int end=input.length-1;
        while(start<=end){
            int mid=(start+end)/2;
            if(input[mid]<target)
                start=mid+1;
            else {
                if (input[mid] > target)
                    end = mid;
                else
                    return mid;
            }
        }
        return -1;
    }
    //反转字符串句子
    //将句子转化为字符串数组，反转其中的每个字符串
    public String fanzhuangString(String input){

        if(input.length()==0)
            return "";
        else{
            String[]strs=input.split(" ");
            if(strs.length==0)
                return input;
            else{
                StringBuffer temp=new StringBuffer();
                for(int i=0;i<strs.length;i++){
                    temp.append(strs[strs.length-i-1]);
                    temp.append(" ");
                }
                temp.deleteCharAt(temp.length()-1);
                return temp.toString();
            }
        }
    }
    //数组全排列的所有情况
    List<Integer> path;
    List<List<Integer>> paths;
    public List<List<Integer>> calculate(int []input){
        boolean [] flag=new boolean[input.length];
        search(input,flag);
        return paths;

    }
    public void search(int[] nums,boolean[] flag){

        if(path.size()==nums.length){
            paths.add(new ArrayList<>(path));
            return;

        }else{
            for (int i=0;i<nums.length;i++){
                if(!flag[i]){
                    flag[i]=true;
                    path.add(nums[i]);
                    search(nums,flag);
                    path.remove(path.size()-1);
                    flag[i]=false;
                }
            }
        }
    }
    //找出数组中第二大的数，不用排序
    //和寻找最大值一样，保留最大值和第二大的值，遍历数组进行更新
    public int findSecond(int[]input){
        int max=input[0];
        int second=0;
        int flag=0;
        for(int i=1;i<input.length;i++){
            if(input[i]!=max){
                flag=i;
                second=input[i];
                break;
            }

        }
        if(second>max){
            int temp=max;
            max=second;
            second=max;
        }
        for(int i=flag+1;i<input.length;i++){
            if(input[i]>max){
                second=max;
                max=input[i];
                continue;
            }
            if(input[i]>second){
                second=input[i];
            }
        }
        return second;

    }
    //快速找出一个数组中的两个数字，让这两个数字之和等于一个给定的值，为了简化起见，我们假设这个数组中肯定存在至少一组符合要求的解。
    public List<Integer> findTwo(int[]input,int target){
        Map<Integer,Integer>map=new HashMap<>();
        List<Integer>result=new ArrayList<>();
        for(int i=0;i<input.length;i++){
            if(map.get(input[i])==null){
                map.put(input[i],1);
            }else{
                map.put(input[i],map.get(input[i])+1);
            }
        }
        for(int i=0;i<input.length;i++){
            if(input[i]==target-input[i]){
                if(map.get(input[i])>=2){
                    result.add(input[i]);
                    result.add(input[i]);
                    return result;
                }
            }else{
                if(map.containsKey(target-input[i])){
                    result.add(input[i]);
                    result.add(target-input[i]);
                }
            }
        }
        return null;
    }
    //输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    //对字符串数组进行排序，排序规则为item1+item2<item2+item1;
    public String pinjie(int []nums){
        List<Integer>newList=new ArrayList<>();
        int len=nums.length;
        for(int i=0;i<len;i++){
            newList.add(nums[i]);
        }
//        Collections.sort(newList, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return ((String.valueOf(o1)+String.valueOf(o2)).compareTo(String.valueOf(o2)+String.valueOf(o1)));
//
//            }
//        });

    StringBuffer result=new StringBuffer();
    for(int i=0;i<len;i++){
        result.append(newList.get(i));
    }
    return result.toString();
    }
    //现在有一个整数类型的数组，数组中只有一个元素只出现一次，其余元素都出现三次。你需要找出只出现一次的元素
    //采用位运算，没数组中每个数字的同一位加起来，将结果对三取余，结果即为目标数字那一位的结果
    public int singkeNumber(int[]input){
        if(input==null||input.length==0){
            return -1;
        }else{
            int result=0;
            for(int i=0;i<32;i++){
                int sum=0;
                for(int j=0;j<input.length;j++){
                    sum+=(input[i]>>i)&1;
                }

                result|=(sum%3)<<i;
            }
            return result;
        }

    }
    //有N个小朋友站在一排，每个小朋友都有一个评分
    //你现在要按以下的规则给孩子们分糖果：
    //每个小朋友至少要分得一颗糖果
    //分数高的小朋友要他比旁边得分低的小朋友分得的糖果多
    //你最少要分发多少颗糖果？
    //解决方法：分发最少苹果的情况只有一种，所以分别从从左向右和从右向左遍历，只要发现不符合条件的就加一，使分发苹果符合题目条件操作得出的结果即为最小值。
    public int candy (int[] ratings) {
        // write code here
        int len=ratings.length;
        if(len==1)return 1;
        int sum=0;
        int[]temp=new int[len];
        for(int i=0;i<len;i++){
            temp[i]=1;
        }
        //从左向右扫描
        for(int i=1;i<len;i++){
            if(ratings[i]>ratings[i-1])
                temp[i]=temp[i-1]+1;
        }
        //从右向左扫描
        for(int i=len-2;i>=0;i--){
            if(ratings[i]>ratings[i+1]&&temp[i]<=temp[i+1]){
                temp[i]=temp[i+1]+1;
            }
        }
        for(int i=0;i<len;i++){
            sum+=temp[i];
        }
        return sum;


    }








    public static void main(String[] args) {
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
//            System.out.println(index);
//            System.out.println(result);

            for (int p = 0; p < index; p++) {
                input.put(p, input.get(p) - 1);
            }
            System.out.println(result);


        }
        System.out.println(result);
    }

}
