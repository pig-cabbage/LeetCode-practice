import java.util.Stack;

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
    public static void main(String[]args){
        Stac test=new Stac();
        String[]input={"2", "1", "+", "3", "*"};
        System.out.println(test.evalRPN(input));
    }
}
