package com.company;
import java.util.*;
import java.util.stream.Collectors;

public class ���� {
    //���ֲ���
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
    //��ת�ַ�������
    //������ת��Ϊ�ַ������飬��ת���е�ÿ���ַ���
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
    //����ȫ���е��������
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
    //�ҳ������еڶ����������������
    //��Ѱ�����ֵһ�����������ֵ�͵ڶ����ֵ������������и���
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
    //�����ҳ�һ�������е��������֣�������������֮�͵���һ��������ֵ��Ϊ�˼���������Ǽ�����������п϶���������һ�����Ҫ��Ľ⡣
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
    //����һ���Ǹ��������飬����������������ƴ�������ų�һ��������ӡ��ƴ�ӳ���������������С��һ����
    //���ַ���������������������Ϊitem1+item2<item2+item1;
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
    //������һ���������͵����飬������ֻ��һ��Ԫ��ֻ����һ�Σ�����Ԫ�ض��������Ρ�����Ҫ�ҳ�ֻ����һ�ε�Ԫ��
    //����λ���㣬û������ÿ�����ֵ�ͬһλ�����������������ȡ�࣬�����ΪĿ��������һλ�Ľ��
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
    //��N��С����վ��һ�ţ�ÿ��С���Ѷ���һ������
    //������Ҫ�����µĹ���������Ƿ��ǹ���
    //ÿ��С��������Ҫ�ֵ�һ���ǹ�
    //�����ߵ�С����Ҫ�����Աߵ÷ֵ͵�С���ѷֵõ��ǹ���
    //������Ҫ�ַ����ٿ��ǹ���
    //����������ַ�����ƻ�������ֻ��һ�֣����Էֱ�Ӵ������Һʹ������������ֻҪ���ֲ����������ľͼ�һ��ʹ�ַ�ƻ��������Ŀ���������ó��Ľ����Ϊ��Сֵ��
    public int candy (int[] ratings) {
        // write code here
        int len=ratings.length;
        if(len==1)return 1;
        int sum=0;
        int[]temp=new int[len];
        for(int i=0;i<len;i++){
            temp[i]=1;
        }
        //��������ɨ��
        for(int i=1;i<len;i++){
            if(ratings[i]>ratings[i-1])
                temp[i]=temp[i-1]+1;
        }
        //��������ɨ��
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

    public long minimumValueAfterDispel (int[] nums) {
        long result = Integer.MAX_VALUE;
        // write code here
        int size = nums.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i; j < size; j++) {
                int[] temp = nums.clone();
                for (int a = 0; a < size; a++) {
                    if (temp[a] >= temp[i]) {
                        temp[a] -= temp[i];
                    }
                }
                for (int a = 0; a < size; a++) {
                    if (temp[a] >= temp[j]) {
                        temp[a] -= temp[j];
                    }
                }
                long sum = 0;
                for (int a = 0; a < size; a++) {
                    sum += temp[a];
                }
                if (sum < result)
                    result = sum;
            }
        }
        return result;
    }
    //������������Ӽ�
    //��һ��Ԫ�ؿ�ʼ����������㷨
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        HashSet<Integer> p = new HashSet<>();
        for(int i = 0; i < num.length; i++){
            if(!p.contains(num[i])) {
                p.add(num[i]);
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(num[i]);
                ArrayList<Integer> temp1 = new ArrayList<>();
                temp1.addAll(temp);
                result.add(temp1);
                help(temp, i + 1, num);
            }
        }
        result.add(new ArrayList<>());
        return result;
    }
    public void help(ArrayList<Integer> temp , int index, int[] num){
        HashSet<Integer> p = new HashSet<>();
        for(int i = index; i < num.length; i++){
            if(!p.contains(num[i])) {
                p.add(num[i]);
                temp.add(num[i]);
                ArrayList<Integer> temp1 = new ArrayList<>();
                temp1.addAll(temp);
                result.add(temp1);
                help(temp, i + 1, num);
                temp.remove(temp.size() - 1);
            }
        }
    }
    //��������һ�ֶ����Ʊ���ϵͳ����������������ڵĴ���ֻ��һλ����������ͬ��������ֱ���Ϊ�����루Gray Code����
    //����һ���Ǹ�����n����ʾ�����λ������ӡ����������С����������б�����0��ͷ��
    //���磺����n=2������[0,1,3,2]. �����������Ϊ��
    //����һ�εĽ��ת����ӵ���һ�εĽ���У�ת�õĲ��ֳ���2�ı���
    public ArrayList<Integer> grayCode (int n) {
        // write code here
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.add(1);
        if(n == 1)
            return temp;
        else{
            int help = 2;
            for(int i = 1; i < n; i++){
                ArrayList<Integer> temp1 = new ArrayList<>();
                for(int j = 0; j < temp.size(); j++){
                    temp1.add(temp.get(temp.size()-j-1));
                }
                temp.addAll(temp1);
                for(int a = temp1.size(); a< temp.size();a++){
                    temp.set(a, temp.get(a) + help);
                }
                help *= 2;
            }
        }
        return temp;
    }
    //���������������������A��B���뽫���� �ϲ������� �У����һ�����������
    //�������˿�ʼ����
    public void merge(int nums1[], int m, int nums2[], int n) {
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 && j >= 0)
            nums1[index--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
        while (j >= 0)
            nums1[index--] = nums2[j--];
    }
    //������һ������n����������飬����������ɫΪ��ɫΪ��ɫ����ɫ����ɫ�����������������������ͬ��ɫ���������ڣ���ɫ��˳��Ϊ��ɫ����ɫ����ɫ��
    //������0,1,2�ֱ������ɫ�죬�ף���
    public void sortColors(int[] A) {
        int start = 0;
        int end = A.length-1;
        while(start < end && start<A.length){
            if(A[start]!=0){
                while(A[end]!=0 && end > start){
                    end--;
                }
                if(A[end] ==0) {
                    int temp = A[start];
                    A[start] = 0;
                    A[end] = temp;
                    start ++;
                    end--;
                }
            }else{
                start+=1;
            }


        }
        System.out.println(Arrays.toString(A));
        System.out.println(start);
        end =A.length-1;
        while(start < end && start<A.length){
            if(A[start]==2){
                while(A[end]!=1 && end> start){
                    end--;
                }
                if(A[end] ==1) {
                    int temp = A[start];
                    A[start] = 1;
                    A[end] = temp;
                    start ++;
                    end--;
                }
            }else{
                start+=1;
            }

        }
    }
    //��д��һ����Ч����m*n�������ж�Ŀ��ֵ�Ƿ���ڵ��㷨�������������������
    //ÿһ�е����ֶ�����������
    //ÿһ�еĵ�һ�����ֶ�����һ�����һ�����ִ�
    //�ⷨ�������½ǻ����Ͻǿ�ʼ�ߡ�
    public boolean searchMatrix (int[][] matrix, int target) {
        // write code here
        if(matrix == null)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int i = m-1;
        int j = 0;
        while(i>=0 && i<m && j>=0 && j<n){
            if(matrix[i][j]>target){
                i--;
            }else{
                if(matrix[i][j]<target){
                    j++;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
    //���������������ʾ��һ���Ǹ���������Ը�������1��
    public int[] plusOne (int[] digits) {
        // write code here
        int len = digits.length;
        int help = 1;
        for(int i = len-1;i>=0;i--){
            int temp = digits[i]  + help;
            if(temp ==10){
                help = 1;
                digits[i] = 0;
            }else{
                help = 0;
                digits[i] = temp;
                break;
            }
        }
        if(help == 1){
            List<Integer> result= new ArrayList<>();
            result.add(1);
            result.addAll(Arrays.stream(digits).boxed().collect(Collectors.toList()));
            return result.stream().mapToInt(Integer::intValue).toArray();
        }
        return digits;
    }
    //����һ������n��������1��n^2
    // ��������˳������n��n�ľ���
    public int[][] generateMatrix (int n) {
        // write code here
        int[][] result = new int[n][n];
        boolean[][] flag = new boolean[n][n];
        int temp = 1;
        int x = 0;
        int y = 0;
        int[][] fangfa = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int index = 0;
        while(temp <= n*n){
            result[x][y] = temp;
            temp +=1;
            flag[x][y] = true;
            int newX = x + fangfa[index][0];
            int newY = y + fangfa[index][1];
            if( !(newX>=0 && newX<n && newY >=0 && newY<n) || flag[newX][newY] == true ){
                index = index==3? 0: index+1;
                newX = x +fangfa[index][0];
                newY = y + fangfa[index][1];
            }
            x = newX;
            y = newY;
        }
        return result;

    }
    public static class Interval {
        int start;
        int end;
        Interval() { start = 0; end = 0; }
        Interval(int s, int e) { start = s; end = e; }
    }
    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        Collections.sort(intervals,(a,b)->a.start-b.start);
        int len = intervals.size();
        int i = 0;
        while (i < len) {
            int left = intervals.get(i).start;
            int right = intervals.get(i).end;
            while (i < len-1 && intervals.get(i+1).start <= right) {
                right = Math.max(right,intervals.get(i+1).end);
                i++;
            }
            res.add(new Interval(left,right));
            i++;
        }
        return res;
    }
    //ʹ��ԭ���㷨����ά����˳ʱ����ת90��
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0;i<m/2;i++){
            for(int j = i;j<n-i-1;j++){
                int x = i;
                int y = j;
                int temp = matrix[x][y];
                for(int k = 0;k<4;k++){
                    int temp1 = matrix[y][m-1-x];
                    matrix[y][m-1-x] = temp;
                    temp = temp1;
                    int w = x;
                    x = y;
                    y = m-1-w;
                }

            }
        }
    }
    //����һ����������������飬���ڸ������������С��������
    //�Ƚ���������Ԫ�أ� ʹ��Ԫ�ص���Ӧ�ڵ�λ�ã���A[i] = i + 1;
    public int firstMissingPositive (int[] A) {
        // write code here
        for(int i = 0;i<A.length;i++){
            while(A[i] > 0 && A[i] != i + 1 && A[i] - 1 < A.length && A[i] !=A[A[i] - 1]){
                int temp = A[A[i] - 1];
                A[A[i] -1] = A[i];
                A[i] = temp;
            }
        }
        for(int i = 0; i < A.length;i++){
            if(A[i] != i + 1)
                return i + 1 ;
        }
        return A[A.length - 1] + 1;
    }
    //����һ��ת�������������飬�����Ȳ�֪��������ת���˶���
    //(����,0 1 2 4 5 6 7���ܱ�Ϊ4 5 6 7 0 1 2).
    //������������������Ŀ��ֵ����������������ҵ��������������������򷵻�-1��
    //���������в������ظ��
    public int search (int[] A, int target) {
        // write code here
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) return mid;
            if (A[mid] >= A[left]) {
                // �������(��A[mid])
                if (A[mid] > target && A[left] <= target)
                    right = mid - 1;
                else
                    left = mid + 1;
            } else {
                // �Ҳ�����(��A[mid])
                if (A[mid] < target && A[right] >= target)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }
    //ʵ�ֺ���next permutation����һ�����У����������е������������г��ֵ����е���һ����������С�
    //1,2,3��1,3,2
    public void nextPermutation(int[] num) {
        int index = num.length -1;
        boolean flag = false;
        int r = num.length-1;
        while(index >=1){
            if(num[index] > num[index - 1]){
                while(num[r] <= num[index-1]){
                    r-=1;
                }
                flag  = true;
                int temp = num[index-1];
                num[index-1] = num[r];
                num[r] = temp;
                reserve(num , index);

                return;
            }else{
                index -=1;
            }
        }
        if(!flag) {
            List<Integer> p = Arrays.stream(num).boxed().collect(Collectors.toList());
            Collections.reverse(p);
            for(int i = 0;i<num.length;i++){
                num[i] = p.get(i);
            }

            return;
        }

    }
    public void reserve(int[] num, int index){
        List<Integer> temp = new ArrayList<>();
        for(int i = index;i<num.length;i++){
            temp.add(num[i]);
        }
        Collections.reverse(temp);
        for(int i = index;i<num.length;i++){
            num[i] = temp.get(i - index);
        }
    }

    //��������n������������s���ҳ�s�кͼ������ĺ���ӽ�������Ŀ��ֵ���������������������������ĺ͡�����Լ���ÿ�����붼ֻ��Ψһ�⡣
    public ArrayList<ArrayList<Integer>> result11 = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> threeSumClosest (int[] num, int target) {
        // write code here
        Arrays.sort(num);
        int val = 0;
        for(int i = 0;i<num.length - 2;i++){
            if( i > 0 && num[i] == num[i-1])
                continue;
            int be = i+1;
            int af = num.length-1;
            int temp = num[i] + num[be] + num[af];
                while(af > be) {
                    temp = num[i] + num[be] + num[af];
                    if(temp == target){
                        ArrayList<Integer> k = new ArrayList<>(Arrays.asList(num[i], num[be], num[af]));
                        result11.add(k);
                    }
                        if(temp < target)
                            be += 1;
                        else {
                            while (num[af] == num[af + 1] && af > be)
                                af -= 1;
                        }
                    }


            }
        return result11;
        }
    //����n���Ǹ�����a1��a2������an������ÿ�����ֱ�ʾ����(i, ai)����һ���㡣�ԣ�i��ai���ͣ�i��0����i=1,2,3...n��Ϊ�˵㻭��n��ֱ�ߡ�����Դ���ѡ����������x��һ�𹹳�һ������������������װ����ˮ��
    //�������������˫ָ�뷨���ı�С�ƶ��ı�
    public int maxArea (int[] height) {
        // write code here
        if(height.length == 0)
            return 0;
        int result = Integer.MIN_VALUE;
        int start = 0;
        int end = height.length - 1;
        while(start <= end){
            result = Math.max(result, Math.min(height[start], height[end]) * (end - start));
            if(height[start] < height[end])
                start +=1;
            else{
                end -=1;
            }
        }
        return result;
    }
    public int[] twoSum (int[] numbers, int target) {
        // write code here
        int[] result = new int[2];
        Map<Integer, List<Integer>>temp = new HashMap<>();
        for(int i = 0;i<numbers.length;i++){
            if(temp.containsKey(numbers[i])){
                temp.get(numbers[i]).add(i);
            }else{
                temp.put(numbers[i], new ArrayList<>(Arrays.asList(i)));
            }
        }
        for(int i = 0; i < numbers.length; i++){
            if(target == numbers[i] *2){
                if(temp.containsKey(numbers[i]) && temp.get(numbers[i]).size() >= 2){
                    result[0] = temp.get(numbers[i]).get(0);
                    result[1] = temp.get(numbers[i]).get(1);
                    return result;
                }
            }else{
                if(temp.containsKey(numbers[i]) && temp.containsKey(target - numbers[i])){
                    result[0] = temp.get(numbers[i]).get(0);
                    result[1] = temp.get(target - numbers[i]).get(0);
                    return result;
                }
            }
        }
        return result;
    }
    //��������С�ֱ�Ϊm��n����������A��B�����ҳ��������������λ��������Ҫ����ʱ�临�Ӷ���O(log (m+n))���ڵ��㷨��
    //˫ָ�뷨
    public double findMedianSortedArrays (int[] A, int[] B) {
        // write code here
        int m = A.length;
        int n = B.length;
        int p1 = 0;
        int p2 = 0;
        int left = -1;
        int right = -1;
        for(int i = 0; i < (m + n)/2 + 1; i++){
            left = right;
            if(p1 >= m || p2 < n && A[p1] > B[p2]){
                right = B[p2++];
            }else{
                right = A[p1++];
            }
        }
        return (m + n) % 2 == 1 ? right : (left + right) / 2.0;
    }
  //�ж������Ƿ�Ϊ
    public static boolean isPalindrome (int x) {
      // write code here
      x = Math.abs(x);
      int bei = 1;
      int temp = x;
      int o = 0;
      while(temp >= 10){
        bei *=10;
        o += 1;
        temp /=10;
        if(temp < bei)
          break;
      }
      if(Math.pow(10, o - 1) > temp) {
        bei /= 10;
      }

      int right = x%bei;
      if(bei == 1){
        right = x%10;
      }
      bei /=10;
      int temp1 = 10;
      while(true){
        if(temp < 10){
          if(temp == right)
            return true;
          else{
            return false;
          }
        }
        int m = temp % temp1;
        int n = right / bei;
        if(m != n)
          return false;
        temp = temp / temp1;
        right = right % bei;
        bei/=10;

      }

    }
    //���ݿ��������˼�룬 �ҳ������е�K�����
    public int findKth(int[] a, int n, int K) {
      // write code here
      int[]result =  findByQuick(a, 0, n -1, K, n);
      if(result != null)
        return result[1];
      else{
        return -1;
      }
    }
    public int[] findByQuick(int[] a, int start, int end, int K, int n) {
      if (start < end) {
        int low = a[start];
        int i = start ;
        int j = end;
        while (i < j) {
          while (j > i && a[j] >= low) {
            j--;
          }
          if(i < j){
            a[i] = a[j];
            i++;
          }
          while (low > a[i] && i < j) {
            i++;
          }
          if(i < j){
            a[j] = a[i];
            j--;
          }
        }
        a[i] = low;
        if (i == n - K ) {
//          System.out.println(a[i]);
          int[] result ={i, a[i]};
          return result;
        }
        else {

          int[] b = findByQuick(a, start, i - 1, K, n);
          if(b != null) return b;
          int[] c = findByQuick(a, i + 1, end, K, n);
          if(c != null) return c;
        }
      } else {
        if(start == n - K){
          int[] result ={start, a[start]};
          return result;
        }
      }
      return null;
    }

    //����n���������ҳ�������С��K��������������4,5,1,6,2,7,3,8��8�����֣�����С��4��������1,2,3,4��
  //����һ������Ϊk����С��
  public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
    int[] dui = new int[k];
    int len = input.length;
    if(len <= k)
      return new ArrayList<>(Arrays.stream(input).boxed().collect(Collectors.toList()));
    for(int i = 0; i < k; i++){
      dui[i] = input[i];
    }
    for(int i = dui.length/2 - 1; i >= 0; i--) {
      adujust(dui, i);
    }
    for(int i = k; i < input.length; i++){
      if(input[i] < dui[0]) {
        dui[0] = input[i];
        adujust(dui, 0);
      }
    }
    return new ArrayList<>(Arrays.stream(dui).boxed().collect(Collectors.toList()));
  }
  public void adujust(int[] dui, int i){

        int p =dui[i];
        for(int k = i * 2 + 1; k < dui.length; k = k * 2 + 1){
          if( k + 1 < dui.length && dui[ k + 1] > dui[ k ]){
            k++;
          }
          if(dui[k] > p){
            dui[i] = dui[k];
            i = k;
          }
        }
        dui[i] = p;
  }
  public int maxLength (int[] arr) {
    // write code here
    if(arr == null || arr.length == 0)
      return 0;
    int len = arr.length;
    int result = Integer.MIN_VALUE;
    Map<Integer, Integer>temp = new HashMap<>();
    for(int start = 0, end=0; end < len;end++){
      if(temp.containsKey(arr[end])){
        start = Math.max(start, temp.get(arr[end] + 1));
      }
      result = Math.max(result, end - start + 1);
      temp.put(arr[end], end);
    }
    return result;
  }
  //����һ������arr�����������������ۼӺ�
  //���磬arr = [1, -2, 3, 5, -2, 6, -1]�������������У�[3, 5, -2, 6]�����ۼӳ����ĺ�12�����Է���12.
  //����̰���㷨������ǰ�Ӵ���С��0ʱ�����䶪��
  public int maxsumofSubarray (int[] arr) {
    // write code here
    if(arr == null || arr.length == 0){
      return 0;
    }
    else{
      int result = Integer.MIN_VALUE;
      int temp = 0;
      for(int i = 0; i < arr.length; i++){
        temp += arr[i];
        if(temp < 0)
          temp = 0;
        else{
          result = Math.max(result, temp);
        }
      }
      result = Math.max(result, temp);
      return result;
    }
  }
  //�ҳ����������д��ڵ���Ŀ�����ĵ�һ����
  public int find(List<Integer> input, int target){
      int low = 0;
      int hidh = input.size() - 1;
      while (low < hidh){
        int mid = (low + hidh)/2;
        if(input.get(mid) < target){
          low = mid + 1;
        }else{
          hidh = mid;
        }
      }
      return hidh;
  }
  //���������е������������
  //���ö�̬�滮
  public int[] LIS (int[] arr) {
    // write code here
      if(arr.length <= 1){
        return arr;
      }else{
        List<Integer> res = new ArrayList<>();
        List<Integer> maxLen = new ArrayList<>();
        res.add(arr[0]);
        maxLen.add(1);
        for (int i = 1; i < arr.length; i++){
          if(arr[i] > res.get(res.size() - 1)){
            res.add(arr[i]);
            maxLen.add(res.size());
          }else{
            int pos = find(res, arr[i]);
            res.set(pos, arr[i]);
            maxLen.add(pos + 1);
          }
        }
        for(int i = arr.length - 1, j = res.size(); j>0;--i){
          if(maxLen.get(i) == j){
            res.set(--j, arr[i]);
          }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
      }
  }
  //������ַ�������������ǰ׺
  public String maxLen(String[] strs){
      if(strs.length == 0)
        return "";
      else{
        String minLen = strs[0];
        for(int i = 1; i < strs.length; i++){
          if(strs[i].length() < minLen.length()){
            minLen = strs[i];
          }
        }
        for(int i = minLen.length() - 1; i >=0; i--){
          String s = minLen.substring(0, i + 1);
          int finalI = i;
          if(Arrays.stream(strs).allMatch(s1 -> s1.substring(0, finalI + 1).equals(s))){
            return s;
          }
        }
        return "";
      }
  }
  //һ�����������������������֮�⣬���������ֶ����������Ρ���д�����ҳ�������ֻ����һ�ε����֡�
  //����λ����
  public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
    int temp = 0;
    for(int i : array){
      temp^=i;
    }
    int temp1 = 1;
    //�ҳ�Ŀ�������֮���һ��Ϊ1��λ
    while((temp1 & temp) == 0){
      temp1 = temp1<<1;
    }
    num1[0] = 0;
    num2[0] = 0;
    //���temp1�������Ϊ�����֣�Ŀ�����ֱ�������������
    for(int i : array){
      if((i & temp1) > 0){
        num1[0] ^=i;
      }else
        num2[0] ^=i;
    }
  }

  public String[][] topKstrings (String[] strings, int k) {
    // write code here
    if(strings.length == 0)
      return null;
    Map<String, Integer> temp = new HashMap<>();
      for(String s : strings){
        if(temp.containsKey(s)){
          temp.put(s, temp.get(s) + 1);
        }else{
          temp.put(s, 1);
        }
      }
    List<Map.Entry<String, Integer>> temp1 = new ArrayList<>(temp.entrySet());
    Map.Entry<String, Integer>[] temp2 = new Map.Entry[k];
    for(int i = 0; i < k; i++){
      temp2[i] = temp1.get(i);
    }
    for(int i = k/2 - 1; i >= 0; i--) {
      adujust(temp2, i);
    }
    for(int i = k; i < temp1.size(); i++){
      if(temp1.get(i).getValue() > temp2[0].getValue()){
        temp2[0] = temp1.get(i);
        adujust(temp2, 0);
      }
      if(temp1.get(i).getValue() == temp2[0].getValue() && temp1.get(i).getKey().compareTo(temp2[0].getKey()) < 0){
        temp2[0]  = temp1.get(i);
      }
    }
    Arrays.sort(temp2, new Comparator<Map.Entry<String, Integer>>() {
      @Override
      public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o2.getValue() - o1.getValue();
      }
    });
    String[][] result = new String[k][k];
    for(int i = 0; i< k; i++){
      result[i][0] = temp2[i].getKey();
      result[i][1] = String.valueOf(temp2[i].getValue());
    }
    return result;
  }

  public void adujust(Map.Entry<String, Integer>[] input, int k){
      Map.Entry<String, Integer> p = input[k];
      for(int i = 2 * k + 1; i < input.length; i = i * 2 + 1){
        if(i + 1 < input.length && input[i].getValue() > input[i + 1].getValue()){
          i++;
        }
        if(p.getValue() > input[i].getValue()) {
          input[k] = input[i];
          k = i;
        }
      }
      input[k] = p;
  }



}
