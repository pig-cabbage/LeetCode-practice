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


    //复杂链表拷贝
    //使用hahmap，key为旧节点，value为新节点
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
    //UndirectedGraphNode链表的拷贝
    //和上面那道拷贝一样，都是要用map来保存从旧节点到拷贝节点的映射
    //使用集合和栈来辅助存储中间数据
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
