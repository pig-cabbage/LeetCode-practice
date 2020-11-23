package com.company;

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
    public static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int value){
            val=value;
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
    public Node fangzhuang(Node root){
        if(root == null)
            return  root;
        else{
            List<Queue> result = new ArrayList<>();
            Queue<Node> temp = new LinkedList<>();
            Queue<Node> temp2 = new LinkedList<>();
            temp.add(root);
            temp2.addAll(temp);
            result.add(temp2);
            while(!temp.isEmpty()){
                temp2 = new LinkedList<>();
                while(!temp.isEmpty()){
                    Node temp1 = temp.poll();
                    if(temp1 != null) {
                        temp2.add(temp1.left);
                        temp2.add(temp1.right);
                    }
                }
                result.add(temp2);
                temp.addAll(temp2);
            }
            for(int i = 0; i < result.size() -1; i++){
                List<Node> temp3 = new ArrayList<>(result.get(i));
                List<Node> temp4 = new ArrayList<>(result.get(i + 1));
                int m = temp3.size() -1;
                int n = temp4.size() -1;
                while(m >= 0){
                    temp3.get(m).left = temp4.get(n--);
                    temp3.get(m).right = temp4.get(n--);
                    m--;
                }

            }
            return root;

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
    static int sum=0;
    public int sumNumbers (TreeNode root) {
        // write code here
        if(root==null){
            return 0;
        }else{
            StringBuffer temp=new StringBuffer();
            help(root,temp);
            return sum;
        }
    }
    public int help(TreeNode root,StringBuffer temp){
        if(root.left==null&&root.right==null){

            String k=root.val+"";
            temp.append(k);

            sum+=Integer.valueOf(temp.toString());
            temp.delete(temp.length()-k.length(),temp.length());
            return 0;
        }else{
            String k=root.val+"";
            temp.append(k);

            if(root.left!=null){
                help(root.left,temp);
            }
            if(root.right!=null){
                help(root.right,temp);
            }
            temp.delete(temp.length()-k.length(),temp.length());

        }
        return 0;
    }
    //����һ���������������ڵ�ֵ֮������·���Ľڵ�ֵ֮���Ƕ��١�
    //���·���Ŀ�ʼ�ڵ�ͽ����ڵ�����Ƕ������е�����ڵ�
    public static  int result1=Integer.MIN_VALUE;
    public int maxPathSum (TreeNode root) {
        // write code here
        if(root==null)
            return 0;
        result1=root.val;
        diedai(root);
        return result1;

    }
    public int diedai(TreeNode root){
        if(root==null){
            return 0;
        }
        else{
            int left=diedai(root.left);
            int right=diedai(root.right);
            System.out.println(left);
            System.out.println(right);
            int temp=0;
            if(left<0)
                temp=right+root.val;
            if(right<0)
                temp=left+root.val;
            if(left>=0&&right>=0)
                temp=right+left+root.val;

            if(temp>result1)
                result1=temp;
            return Math.max(left,right)+root.val;

        }
    }
    
    //����һ����������������нڵ��nextָ��
    //ʹ�ò���������ڱ���ÿһ��ڵ������nextָ��
    public class TreeLinkNode {
      int val;
      TreeLinkNode left, right, next;
      TreeLinkNode(int x) { val = x; }
  }
    public void connect(TreeLinkNode root) {
        if(root==null)
            return;
        Queue<TreeLinkNode> temp = new LinkedList<>();
        temp.add(root);
        while(!temp.isEmpty()){
            int size = temp.size();
            TreeLinkNode first = null;
            for(int i =0;i<size;i++){
                TreeLinkNode p = temp.poll();
                if(p.left!=null){
                    first.next=p.left;
                    first=first.next;
                    temp.add(p.left);
                }
                if(p.right!=null){
                    first.next=p.right;
                    first=first.next;
                    temp.add(p.right);
                }
            }
        }
        
    }
    //����һ��������������ͺ���������빹����Ŷ�����
    //ע�⣺
    //��֤���������в������ظ��Ľڵ�
    //���÷��η�
    int p=0;
    public TreeNode buildTree (int[] inorder, int[] postorder) {
        // write code here
        int len = postorder.length;
        if(len == 0)
            return null;
        p=len;
        TreeNode root =new TreeNode(postorder[len-1]);
        int index = findIndex(inorder, postorder[len-1]);
        p-=1;
        root.right = help(inorder, postorder, index+1, len-1);
        root.left = help(inorder, postorder, 0, index-1);
        return root;
    }
    public TreeNode help (int[] inorder, int[] postorder, int start, int end){
        if(p<0 || start>end) {
            return null;
        }
        p-=1;
        TreeNode root = new TreeNode(postorder[p]);
        if(start==end)
            return root;
        else{
            int index = findIndex(inorder, postorder[p]);
            root.right = help(inorder, postorder, index+1, end);
            root.left = help(inorder, postorder, start, index-1);
            return root;
        }
        
    }
    public int findIndex(int[] inorder , int target){
        for(int i=0; i<inorder.length; i++){
            if(inorder[i]==target)
                return i;
        }
        return -1;
    }

    public boolean isSymmetric (TreeNode root) {
        // write code here
        
        TreeNode temp = bianli(root);
        jingxiang(root);
        System.out.println(temp.val);
        System.out.println(root.val);
        if(equal(root, temp))
            return true;
        else
            return false;
        
    }
    public boolean equal(TreeNode root, TreeNode temp){
        if(root==null&&temp==null)
            return true;
        else{
            if(root==null || temp==null)
                return false;
            else{
                return root.val==temp.val&&equal(root.left, temp.left)&&equal(root.right, temp.right);
            }
        }
        
    }
    public TreeNode bianli (TreeNode root){
        if(root==null) {
            return null;
        }
        else{
            TreeNode temp = new TreeNode(root.val);
            if(root.left!=null){
                temp.left = bianli(root.left);
            }
            if(root.right!=null)
                temp.right = bianli(root.right);
            return temp;
        }
    }
    public void jingxiang (TreeNode root){
        if(root==null){
            return;
        }else{
            TreeNode temp =root.left;
            root.left = root.right;
            root.right = temp;
            jingxiang(root.left);
            jingxiang(root.right);
        }
    }
    //������������BST���е������ڵ㱻����ؽ����ˣ�
    //���ڲ��ı����Ľṹ������»ָ��������
    //������������prev��current,��������Ҫ������ֵ
    public void recoverTree(TreeNode root){
        TreeNode prev = null;
        TreeNode current = null;
        inorder(root, prev, current);
        if(prev!=null && current !=null){
            int tmp = prev.val;
            prev.val = current.val;
            current.val = tmp;
        }
    }
    
    public void inorder(TreeNode root, TreeNode prev, TreeNode current){
        if(root==null) return;
        if(root.left!=null) inorder(root.left, prev, current);
        
        if(prev!=null && prev.val > root.val) current = root;
        if(prev==null || current == null) prev =root;
        if(root.right!=null) inorder(root.right, prev, current);
        return;
    }
    
    //����һ��ֵn,���������еĴ洢ֵ1...n.�Ķ�����������BST���Ľṹ
    //���÷�֧�����ֳ������������������ڶ���ѭ���������
    public ArrayList<TreeNode> help(int start, int end){
        ArrayList<TreeNode> result = new ArrayList<TreeNode>();
        if(start> end){
            result.add(null);//�ؼ��ĵط��������ĺܹؼ����������һ������Ϊnull�����
            return result;
        }
        for(int i=start; i<= end; i++){
            ArrayList<TreeNode> resultLeft = help(start, i-1);
            ArrayList<TreeNode> resultRight = help(i+1, end);
            //TreeNode newNode = new TreeNode(i); ��䲻�ܷ��������Ϊ�ᵼ�¸���㹲��֮���½������
            for (int j = 0; j < resultLeft.size(); j ++) {
                for (int k = 0; k < resultRight.size(); k ++) {
                    // �����������໥��ԣ�ÿһ����������������������ƥ�䣬ÿһ���������������е�������ƥ��
                    TreeNode newNode = new TreeNode(i);
                    newNode.left = resultLeft.get(j);
                    newNode.right = resultRight.get(k);
                    result.add(newNode);
                }
            }
        }
        return result;
    
    }
    public ArrayList<TreeNode> generateTrees(int n) {
        return help(1, n);
    }
    //����һ��ֵn���ܹ��������ٲ�ͬ��ֵ����1...n�Ķ�����������BST����
    //���ö�̬�滮
    public int numTrees (int n) {
        // write code here
        int[]dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++){
            int sum=0;
            for(int j=1;j<=i;j++){
                sum+=dp[j-1]*dp[i-j];
            }
            dp[i]=sum;
        }
        return dp[n];
    }
    
    Set<String> k = new HashSet<>();
    //ţ��Leetcode��������LC62���ж�s2�Ƿ�Ϊs1�������ַ�����
    //���õݹ��㷨
    //��֤�����ڵ���������Ͷ�Ӧ�ĸ�����ͬ���ɡ�
    public boolean isScramble (String s1, String s2) {
        // write code here
        if (s1.equals(s2))
            return true;
    
        int[] letters = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++)
            if (letters[i] != 0)
                return false;
    
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
              && isScramble(s1.substring(i), s2.substring(i)))
                return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i))
              && isScramble(s1.substring(i), s2.substring(0, s2.length() - i)))
                return true;
        }
        return false;
    }
    List<Integer> temp = new ArrayList<>();

    public int[] findError (TreeNode root) {
        // write code here
        int[]result = new int[2];
        find(root);
        System.out.println(temp.toString());
        for(int i = 0; i < temp.size() -1;i++){
            if(temp.get(i) > temp.get(i+1)){
                result[1] = temp.get(i);
                break;
            }
        }
        for(int j = temp.size() -1; j>0;j--){
            if(temp.get(j) < temp.get(j+1)) {
                result[0] = temp.get(j);
                break;
            }
        }
        return result;
    }
    public TreeNode find(TreeNode root){
        if(root == null)
            return null;
        else{
            TreeNode left = find(root.left);
            temp.add(root.val);
            TreeNode right = find(root.right);
        }
        return root;
    }

    //����һ�ö������Լ�������ϵ������ڵ� o1 �� o2�����ҵ� o1 �� o2 ������������Ƚڵ㡣
    public boolean find1 = false;
    public boolean find2 = false;
    public TreeNode resultNode = null;
    public boolean flag = false;
    public int lowestCommonAncestor (TreeNode root, int o1, int o2) {
        // write code here
        if(root == null)
            return -1;
        hou(root, o1, o2);
        if(resultNode == null)
            return root.val;
        return resultNode.val;
    }

    public boolean hou(TreeNode root, int o1, int o2){
        if(root == null)
            return false;
        else{
            if(hou(root.left, o1, o2) || hou(root.right, o1, o2)){
                if(!flag) {
                    flag = true;
                    resultNode = root;
                }

                return true;

            }
            if(find1 && find2){
                return  true;
            }
            if(root.val == o1){
                find1 = true;
            }
            if(root.val == o2)
                find2 = true;
            return false;
        }
    }
    List<Integer> result11 = new ArrayList<>();
    int pp = 0;
    public TreeNode solve(int[] xianxu, int[] zhongxu){
        // write code here
        if(xianxu.length == 0 || zhongxu.length == 0)
            return null;

        int index = findIndex(zhongxu, xianxu[0]);
        TreeNode root = new TreeNode(xianxu[0]);
        root.left = help(xianxu, zhongxu, pp, xianxu.length - 1, 0, index - 1);
        root.right = help(xianxu, zhongxu, pp, xianxu.length - 1, index + 1, zhongxu.length - 1);
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> temp = new ArrayDeque<>();
        temp.add(root);
        while(!temp.isEmpty()){
            Queue<TreeNode> temp1 = new LinkedList<>();
            while(!temp.isEmpty()){
                TreeNode k = temp.poll();
                if(temp.isEmpty()){
                    result.add(k.val);
                }
                if(k.left!=null)
                    temp1.add(k.left);
                if(k.right!=null)
                    temp1.add(k.right);
            }
            temp = new LinkedList<>(temp1);
        }
        return root;
//         return result.stream().mapToInt(Integer::intValue).toArray();
    }
    public TreeNode help(int[] xianxv, int[] zhongxu, int xStart, int xEnd, int zStart, int zEnd){
        if( zStart == zEnd){
            pp+=1;
            return new TreeNode(zhongxu[zStart]);
        }
        if(zStart > zEnd)
            return null;
        pp +=1;
        if(pp > xEnd)
            return null;
        int index = findIndex(zhongxu, xianxv[pp]);
        TreeNode root = new TreeNode(xianxv[pp]);
        root.left = help(xianxv, zhongxu, pp, xEnd, zStart, index -1);
        root.right = help(xianxv, zhongxu, pp, xEnd, index + 1, zEnd);
        return root;
    }
    //����һ�ö��������������ҳ����еĵ�kС�Ľ�㡣
    int ppp = 0;
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        if(pRoot == null)
            return null;
        else{
            TreeNode left = KthNode(pRoot.left, k);
            if(left != null)
                return left;
            ppp +=1;
            if(ppp == k){
                return pRoot;
            }
            TreeNode right = KthNode(pRoot.right, k);
            if(right != null)
                return right;
            return null;
        }
    }







    
}
