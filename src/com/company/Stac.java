package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Stac {

    //通过两个栈来模拟队列
    Stack<Integer> stack1=new Stack<Integer>();
    Stack<Integer>stack2=new Stack<Integer>();

    public void push(int node){
        stack1.push(node);
    }
    public int poll(){
        if(stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
    //判断入栈和出栈的顺序是否合理
    public boolean isPopOrder(int []pushA,int []popA){
        int index=0;
        int start=0;
        int len=pushA.length;
        Stack<Integer>temp=new Stack<>();
        for(int i=0;i<len;i++){
            if(pushA[i]!=popA[index])
                temp.push(pushA[i]);
            else{
                start=i;
                break;
            }
        }
        index+=1;
        while(index<len){
            if(temp.peek()==popA[index]){
                temp.pop();
                index+=1;
                continue;
            }else{

                for(int i=start+1;i<len;i++){
                    if(pushA[i]!=popA[index]){
                        temp.push(pushA[i]);
                    }else{
                        start=i;
                        index+=1;
                        continue;
                    }
                }

            }

        }
        return true;
    }
    //计算逆波兰式（后缀表达式）的值
    //运算符仅包含"+","-","*"和"/"，被操作数可能是整数或其他表达式
    //使用栈，遍历数组，不是操作符就放到栈顶，是操作符就取出栈的前两个元素进行计算
    public int evalRPN (String[] tokens) {
        // write code here
        Stack<Integer>temp=new Stack<>();

        int len=tokens.length;
        for(int i=0;i<len;i++){
            String index=tokens[i];
            if(!(index.equals("+")||index.equals("-")||index.equals("*")||index.equals("/")))
                temp.add(Integer.valueOf(index+""));
            else{
                int right=temp.pop();
                int left=temp.pop();

                switch (index){
                    case "+":
                        temp.add(left+right);
                        break;
                    case "-":
                        temp.add(left-right);
                        break;
                    case "*":
                        temp.add(left*right);
                        break;
                    case "/":
                        temp.add(left/right);
                        break;
                }
            }

        }
        return temp.pop();
    }
    //计算直方图的最大面积
    //采用栈，借助贪心算法，当遇到小于上一个的值时，上一个的值就已经没有作用。
    public int calculateZHifang(int[]temp){
        List<Integer> temp1 = Arrays.stream(temp).boxed().collect(Collectors.toList());
        temp1.add(0);
        int result = 0;
        Stack<Integer> s = new Stack<>();
        for(int  i =0;i<temp1.size();){
            if(s.isEmpty() || temp1.get(i) >= temp1.get(s.peek())){
                s.add(i);
                i++;
            }else{
                int k =s.peek();
                s.pop();
                result = Math.max(result, (s.isEmpty()? i: i-s.peek()-1)*temp1.get(k));
            }
        }
        return result;
    }
    //给出n个数字，表示一个高程图，高程图中每一条的宽度为1，请计算下雨之后这个地形可以存储多少水
    public int trap(int[] A) {
        //感觉这题目都是数学啊。
        int sum = 0;
        for(int i=0; i< A.length; i++){
            int max_left = 0;
            int max_right = 0;
            for(int j = i; j>=0;j--)//一定要从i开始，这样的话如果是个独峰，就不能积水了
                max_left = Math.max(max_left, A[j]);
            for(int j = i; j< A.length;j++)//一定要从i开始，这样的话如果是个独峰，就不能积水了
                max_right = Math.max(max_right, A[j]);

            sum+= Math.min(max_left, max_right)-A[i];
            System.out.println(sum);
        }
        return sum;
    }

    //实现字符串算式
    public static int solve (String s) {
        // write code here
        if(s.equals(""))
            return 0;
        Stack<String> temp = new Stack<>();
        int i = 0;
        while(i < s.length()){
            char c = s.charAt(i);
            if(c == ')'){
                List<String> temp1 = new ArrayList<>();
                while(!temp.peek() .equals("(")){
                    temp1.add(temp.pop());
                }
                temp.pop();
                temp.add(String.valueOf(slove1(temp1)));
                i++;
            }else{
                if(c == '+' || c == '-' || c == '*' || c == '('){
                    temp.add(String.valueOf(c));
                    i++;
                }else {
                    int j = i;
                    StringBuilder right = new StringBuilder();
                    for(; j < s.length(); j++){
                        if(s.charAt(j) >= '0' && s.charAt(j) <= '9')
                            right.append(s.charAt(j));
                        else
                            break;
                    }
                    temp.add(right.toString());
                    i = j;
                }
            }
        }
        Stack<String> temp1 = new Stack<>();
        temp1.add(temp.pop());
        while(!temp.isEmpty()){
            if(temp.peek().equals("*")){
                temp.pop();
                temp1.add(String.valueOf(Integer.parseInt(temp1.pop()) * Integer.parseInt(temp.pop())));
            }else{
                temp1.add(temp.pop());
            }
        }
        int result = Integer.parseInt(temp1.pop());
        while(!temp1.isEmpty()){
            if(temp1.pop().equals("+")){
                result += Integer.parseInt(temp1.pop());
            }else{
                result -= Integer.parseInt(temp1.pop());
            }
        }
        return result;

    }

    public static int slove1 (List<String> input){
        int len = input.size();
        List<String> temp = new ArrayList<>();
        temp.add(input.get(len - 1));
        int i = len - 2;
        while (i>=0){
            if(input.get(i).equals("*")){
                temp.set(temp.size() - 1, String.valueOf(Integer.parseInt(temp.get(temp.size() - 1)) * Integer.parseInt(input.get(i - 1))));
                i -=2;
            }else{
                temp.add(input.get(i));
                i-=1;
            }
        }
        int result = Integer.parseInt(temp.get(0));
        int j = 1;
        while (j < temp.size()){
            if(temp.get(j) .equals("-")){
                result -= Integer.parseInt(temp.get(j + 1));
            }if(temp.get(j).equals("+")){
                result += Integer.parseInt(temp.get(j + 1));
            }
            j+=2;
        }
        return result;
    }
    //实现一个特殊功能的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
    public int[] getMinStack (int[][] op) {
        // write code here
        Map<Integer, Integer> temp = new TreeMap<>();
        List<Integer> result = new ArrayList<>();
        Stack<Integer> temp1 = new Stack<>();
        for(int i =0; i < op.length; i++){
            if(op[i][0] == 1){
                if(temp.containsKey(op[i][1])){
                    temp.put(op[i][1], temp.get(op[i][1]) + 1);
                }else{
                    temp.put(op[i][1], 1);
                }
                temp1.add(op[i][1]);
            }if(op[i][0] == 2){
                int k = temp1.pop();
                if(temp.get(k) == 1){
                    temp.remove(k);
                }else{
                    temp.put(k, temp.get(k) - 1);
                }
            }if(op[i][0] == 3){
                result.add(new ArrayList<Map.Entry<Integer, Integer>>(temp.entrySet()).get(0).getKey());
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    //给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水。
    public long maxWater(int[] arr){
        if(arr.length <=2)
            return 0;
        else{
            int result = 0;
            Stack<Integer> temp = new Stack<>();
            int q = arr[0];
            for(int i = 1; i < arr.length; i++){
                if(arr[i] < q){
                    temp.add(arr[i]);
                }else{
                    while(!temp.isEmpty()){
                        result += q - temp.pop();
                    }
                    q = arr[i];
                    temp.add(q);
                }
            }
            while(!temp.isEmpty()){
                int p = temp.pop();
                while(!temp.isEmpty() && temp.peek() < p){
                    result += p - temp.pop();
                }
            }
            return result;
        }

    }

}
