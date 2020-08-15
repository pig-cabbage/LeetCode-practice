import javax.swing.tree.TreeNode;
import java.util.*;

public class �� {
    //�ж����Ƿ�Ϊ����Ѱ����
    public static class Node{
        Node left;
        Node right;
        int value;
        Node(int val){
            value=val;
        }
    }
    //�ж����Ƿ�Ϊ����Ѱ����
    //ͨ��������Сֵ�����ֵ
    public boolean isBTS(Node root,int min,int max){
        if(root==null)
            return true;
        else{
            if(root.value<min || root.value>max)
                return false;
            else{
                return isBTS(root.left,min,root.value-1)&& isBTS(root.right,root.value+1,max);
            }

        }
    }

    //�����������
    //�ݹ��㷨
    public int calculateShendu(Node root){
        if(root==null)
            return 0;
        else{
            int left=calculateShendu(root.left);
            int right=calculateShendu(root.right);
            return Math.max(left,right)+1;
        }

    }
    //�����������������ڵ����Զ����
    int max=0;
    public int calculateMaxLength(Node root){
        if(root==null)
            return 0;
        else{
            int left=calculateMaxLength(root.left);
            int right=calculateMaxLength(root.right);
            if(left+right>max)
                max=left+right;
            return left>right? left+1:right+1;
        }

    }
    public int findi(int[]args,int target){
        for(int i=0;i<args.length;i++){
            if(args[i]==target)
                return i;
        }
        return -1;
    }
    //ͨ��ǰ��������������������
    //�ҵ�ǰ�������������������е�λ�ã��и�����������飬�ݹ�
    //�ر�ע�⣬pEnd��iEndΪ���ȼ�һ
    public Node createTree(int []pre,int pStart,int pEnd,int[]in,int iStart,int iEnd){
        if(iStart>iEnd || pStart>pEnd)return null;

        int index=findi(in,pre[pStart]);
        int leftSize=index-iStart;
        int rightSize=iEnd-index;

        Node root=new Node(in[index]);
        root.left=createTree(pre,pStart+1,pStart+leftSize,in,iStart,index-1);
        root.right=createTree(pre,pStart+leftSize+1,pEnd,in,index+1,iEnd);
        return root;
    }
    //������������
    //��ת�ڵ�������ӽڵ�
    public void jingXiangTree(Node root){
        if(root==null)
            return ;
        else{
            Node temp=root.left;
            root.left=root.right;
            root.right=temp;
            jingXiangTree(root.left);
            jingXiangTree(root.right);
        }
    }
    //֮���δ�ӡ��
    //��һ��һ��Ľ����ڵ㱣�浽�����У���ȥ��ת����
    public List<List<Integer>> zhiDayinTree(Node root){
        Queue<Node> temp=new LinkedList<>();
        temp.add(root);
        List<List<Integer>>temp1=new ArrayList<>();
        while(temp.size()!=0){
            List<Integer>a=new ArrayList<>();
            int k=temp.size();
            for(int i=0;i<k;i++){
                Node l=temp.poll();
                a.add(l.value);
                temp.add(l.left);
                temp.add(l.right);

            }
            temp1.add(a);
        }
        return temp1;

    }
    public List<List<Integer>>result;
    //������������к�Ϊָ����������·��
    public List<List<Integer>>calculate(Node root,Integer target,List<Integer>temp){
        if(root==null){
            return result;
        }
        if(target==0&&root.left==null&&root.right==null){
            List<Integer>k=new ArrayList<>();
            k.addAll(temp);
            result.add(k);
            return result;
        }


        else{
            if(root.left!=null){

                temp.add(root.left.value);
                target-=root.left.value;
                calculate(root.left,target,temp);
                temp.remove(temp.size()-1);
                target+=root.left.value;
            }
            if(root.right!=null){
                temp.add(root.right.value);
                target-=root.right.value;
                calculate(root.right,target,temp);
                temp.remove(temp.size()-1);
                return result;
            }
            return result;
        }

    }


    //��������ת��Ϊ˫������left��ʾpre��right��ʾnext
    //�ݹ鷽�����ó���������ͷ����β�ڵ����������ͷ���β�ڵ㣬��������root�ڵ�����
    public List<Node> treeToDoublyListCore(Node root){
        if(root==null)
        return null;
        else{
            List<Node> left=treeToDoublyListCore(root.left);
            List<Node> right=treeToDoublyListCore(root.right);
            Node left_head=left.get(0);
            Node left_tail=left.get(1);
            Node right_head=right.get(0);
            Node right_tail=right.get(1);
            root.left=left_tail;
            root.right=right_head;
            if(left_tail!=null){
                left_tail.right=root;
            }if(right_head!=null){
                right_head.left=root;
            }
            List<Node>result=new ArrayList<>();
            if(left_head!=null){
                result.add(left_head);
            }else{
                result.add(root);
            }if(right_tail!=null){
                result.add(right_tail);
            }else{
                result.add(root);
            }
            return result;
        }

    }
    //���������������黹ԭ�ɶ������Ƿ��Ƕ���Ѱ����
    //���÷��ε�˼�룬���С�����ҵĽڵ㣬�ұߴ������ұߵĽڵ㣬�Ӷ��ֳ�����������
    public boolean verifyPostorder(int[] postorder) {
        int len=postorder.length;
        return cur(postorder,0,len-1);
    }
    public boolean cur(int[]postorder,int i,int j){
        if(i>=j)
            return true;
        else{
            int m=i;
            while(postorder[m]<postorder[j]){
                m++;
            }
            int n=m;
            while(postorder[n]>postorder[j]){
                n++;
            }
            return n==j&&cur(postorder,i,m-1)&&cur(postorder,m,j-1);
        }
    }
    public List<Integer>intList=new ArrayList<>();
    //���л�������
    //�Ȳ������������������������У���ת��ΪString
    public String serialize( Node root) {
        StringBuilder res = new StringBuilder("[");
        Queue<Node> queue = new LinkedList<Node>() {{ add(root); }};
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node != null) {
                res.append(node.value + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        System.out.println(res.toString());
        return res.toString();


    }

    // �����л�
    public Node deserialize(String data) {
StringBuffer temp=new StringBuffer();
temp.append(data);
temp.deleteCharAt(temp.length()-1);
temp.deleteCharAt(0);
String temp1=temp.toString();
String []temp2=temp1.split(",");
Queue<Node>temp3=new LinkedList<>();
temp3.add(new Node(Integer.parseInt(temp2[0])));
Node root=new Node(Integer.parseInt(temp2[0]));
Node result=root;
int index=1;
while(temp3.size()!=0){
    Node node=temp3.poll();
    if(temp2[index]!="null"){
        node.left=new Node(Integer.parseInt(temp2[index]));
        temp3.add(node.left);
    }
    index+=1;
    if(temp2[index]!="null"){
        node.right=new Node(Integer.parseInt(temp2[index]));
        temp3.add(node.right);
    }
    index+=1;
}
return result;

    }
    // ��һ�����飬����һ��������Сֵ������������,������������������,���ұ��������ұ�����,�����Ĺ�����ͬ��
    public Node buildTree(int[]input){
        int len=input.length;
        int []result=findSmalll(input,0,len-1);
        int index=result[1];
        int value=result[0];
        Node root=new Node(value);
        root.left=help(input,0,index-1);
        root.right=help(input,index+1,len-1);
        return root;
    }
    public Node help(int[]input,int start,int end){
        if(start==end){
            Node root=new Node(input[start]);
            return root;
        }
        if(start>end){
            return null;
        }
        int[]result=findSmalll(input,start,end);
        Node root=new Node(result[0]);
        int index=result[1];
        root.left=help(input,start,index-1);
        root.right=help(input,index+1,end);
        return root;


    }
    public int[] findSmalll(int[]input,int start,int end){
        int[]sum=new int[2];
        int result=input[start];
        int index=start;
        for(int i=start+1;i<=end;i++){
            if(input[i]<result){
                result=input[i];
                index=i;
            }
        }
        sum[0]=result;
        sum[1]=index;
        return sum;
    }
    //���������������С��ȡ���С�����ָ���ĸ���㵽���Ҷ�ӽ������·���Ͻ���������
    public int calculateMin(Node root){
        if(root==null){
            return 0;
        }else{
            int left=calculateMin(root.left);
            int right=calculateMin(root.right);
            if(root.left==null&&root.right!=null){
                return right+1;
            }
            if(root.left!=null&&root.right==null){
                return left+1;
            }
            else{
                return Math.min(left,right)+1;
            }
        }
    }
    //ʹ�õ����ķ�ʽʵ�ֶ����ĺ������
    public ArrayList<Integer> postorderTraversal (Node root) {
        ArrayList<Integer>temp=new ArrayList<>();
        Stack<Node>temp1=new Stack<>();
        if(root==null){
            return temp;
        }
        temp1.add(root);
        while(!temp1.empty()){
            Node s1=temp1.peek();
            if(s1.left==null&&s1.right==null){
                temp.add(s1.value);
                temp1.pop();
            }else{
                if(s1.right!=null){
                    temp1.add(s1.right);
                    s1.right=null;
                }
                if(s1.left!=null){
                    temp1.add(s1.left);
                    s1.left=null;
                }
            }
        }
        return temp;


    }
    //�õ����ķ�ʽʵ�ֶ�����ǰ�����
    public ArrayList<Integer> preorderTraversal (Node root) {
        // write code here
        ArrayList<Integer>temp=new ArrayList<>();
        Stack<Node>temp1=new Stack<>();
        if(root==null){
            return null;
        }
        temp1.add(root);
        while(!temp1.empty()){
            Node s1=temp1.pop();
            temp.add(s1.value);
            if(s1.right!=null){
                temp1.add(s1.right);
            }
            if(s1.left!=null){
                temp1.add(s1.left);
            }
        }
        return temp;



    }
    public static  void main(String[]args){
        �� test=new ��();
        Node k=new Node(1);
        k.left=new Node(2);
        k.right=new Node(3);
        k.right.left=new Node(4);
        k.right.right=new Node(5);
        test.serialize(k);

    }
}
