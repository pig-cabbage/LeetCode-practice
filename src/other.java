import netscape.security.UserDialogHelper;

import java.util.*;

public class other {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
  class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }


    //����������
    //ʹ��hahmap��keyΪ�ɽڵ㣬valueΪ�½ڵ�
    public Node copyRandomList(Node head){
        Map<Node,Node> temp=new HashMap<>();
        Node cur=head;
        while(cur!=null){
            temp.put(cur,new Node(cur.val));
            cur=cur.next;
        }
        cur=head;
        while(cur!=null){
            temp.get(cur).next=temp.get(cur.next);
            temp.get(cur).random=temp.get(cur.random);
        }
        return temp.get(head);
    }
    //UndirectedGraphNode����Ŀ���
    //�������ǵ�����һ��������Ҫ��map������Ӿɽڵ㵽�����ڵ��ӳ��
    //ʹ�ü��Ϻ�ջ�������洢�м�����
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null){
            return null;
        }
        Map<UndirectedGraphNode,UndirectedGraphNode>map=new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        UndirectedGraphNode head=new UndirectedGraphNode(node.label);
        map.put(node,head);
        Stack<UndirectedGraphNode> stack=new Stack<UndirectedGraphNode>();
        stack.push(node);
        while(!stack.isEmpty()){
            UndirectedGraphNode root=stack.pop();
            ArrayList<UndirectedGraphNode> lists=new ArrayList<>();
            for(UndirectedGraphNode n:root.neighbors){
                if(map.containsKey(n)){
                    lists.add(map.get(n));
                }else{
                    UndirectedGraphNode n1=new UndirectedGraphNode(n.label);
                    stack.push(n);
                    map.put(n,n1);
                    lists.add(n1);
                }
            }
            map.get(root).neighbors=lists;
        }
        return head;


    }

}
