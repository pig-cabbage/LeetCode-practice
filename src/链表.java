import java.util.ArrayList;
import java.util.List;

public class 链表 {
     class  ListNode{
        int val;
        ListNode next;
        ListNode(int value){
            val=value;
        }
    }
    public void reorderList() {
        ListNode head=new ListNode(12);
        head.next=new ListNode(23);
        head.next.next=new ListNode(89);
        head.next.next.next=new ListNode(45);
        head.next.next.next.next=new ListNode(34);
        if(head==null||head.next==null){
            return;
        }
        ListNode slow=head;
        ListNode fast=head;
        ListNode preslow=head;
        while(slow!=null&&fast!=null&&fast.next!=null){
            preslow=slow;
            slow=slow.next;
            if(fast.next.next==null){
                fast=fast.next;
            }else{
                fast=fast.next.next;
            }
        }

        ListNode a=head;
        ListNode b=slow;
        preslow.next=null;

        ListNode k=b;
        if(b.next!=null){
            ListNode p=b;
            ListNode q=b.next;
            while(q!=null){
                ListNode r=q.next;
                q.next=p;
                p=q;
                q=r;
            }
            k=p;
        }
        b=k;
System.out.println(a.val);
System.out.println(b.val);
//System.out.println(a.next.val);
//System.out.println(b.next.val);
//System.out.println(a.next.next.val);
//System.out.println(b.next.next.val);
        while(a!=null&&b!=null){
            ListNode temp=a.next;
            a.next=b;
            a=a.next;
            b=b.next;
            if(temp==null){
                if(b==null){
                    break;
                }else{
                    a.next=b;
                    break;
                }
            }else{
                a.next=temp;
                a=a.next;
            }

        }


    }
    //反转链表
//    public Node fanzhuan(Node head){
//        Node p=head;
//        Node q=head.next;
//        while(q!=null){
//            Node r=q.next;
//            q.next=p;
//            p=q;
//            q=r;
//        }
//        return head;
//    }
//    //在m到n之间反转链表
//    public Node fanzhuanFromMtoN(Node head,int m,int n){
//        Node p=head;
//        Node q=head.next;
//        int index=0;
//        Node first=null;
//        Node temp=null;
//        Node end=null;
//        Node temp1=null;
//        while(index<m){
//
//            p=p.next;
//            q=q.next;
//            if(p==null||q==null){
//                return head;
//            }
//            index+=1;
//            if(index==m-1) {
//                first = p;
//
//            }
//        }
//        while(index<n){
//            Node r=q.next;
//            if(index==n){
//
//                temp1=q;
//                end=r;
//            }
//            q.next=p;
//            p=q;
//            if(index==n){
//                temp=p;
//            }
//            q=r;
//            index+=1;
//
//        }
//        first.next=temp;
//        temp1.next=end;
//
//        return head;
//
//    }
//    //按n个数为一组反转链表
//    public Node fanzhuanN(Node head,int k) {
//        Node p = head;
//        Node q = head.next;
//        int index = 0;
//        List<Node>temp=new ArrayList<>();
//        while (p != null&&q!=null) {
//
//        while (index < k-1&&q!=null) {
//            index += 1;
//            if (index == k-1) {
//                temp.add(q);
//            }
//            Node r = q.next;
//            q.next = p;
//            p = q;
//            q = r;
//
//        }
//        temp.add(p);
//        p=q;
//        q=q.next;
//        index=0;
//    }
//    int o=0;
//    while(o<temp.size()&&o+3<temp.size()){
//        temp.get(o).next=temp.get(o+3);
//        o+=2;
//    }
//    return head;
//    }
//    //判断链表是否存在环
//    //使用快慢指针
//    //也可以借助集合类
//    public boolean panduanHuan(Node head){
//        Node slow=head;
//        Node fast=head;
//        while(slow!=null && fast!=null){
//            slow=slow.next;
//            fast=fast.next.next;
//            if(slow==fast)
//                return true;
//        }
//        return false;
//    }
//
//    //使用归并算法对链表进行排序
//    public Node sortList (Node head) {
//        // write code here
//        if(head==null){
//            return null;
//        }
//        if(head.next==null){
//            return null;
//        }
//        Node preSlow=head;
//        Node slow=head;
//        Node fast=head;
//        while(slow!=null&&fast!=null&&fast.next!=null){
//            preSlow=slow;
//            slow=slow.next;
//            fast=fast.next.next;
//        }
//        Node listA=head;
//        Node listB=slow;
//        preSlow.next=null;
//        listA=sortList(listA);
//        listB=sortList(listB);
//        return merge(listA,listB);
//    }
//    public Node merge(Node listA,Node listB){
//        Node dummy=new Node(0);
//        Node nodeR=dummy;
//        Node nodeA=listA;
//        Node nodeB=listB;
//        while(nodeA!=null&&nodeB!=null){
//            if(nodeA.val<nodeB.val){
//                nodeR.next=nodeA;
//                nodeA=nodeA.next;
//            }
//            else{
//                nodeR.next=nodeB;
//                nodeB=nodeB.next;
//            }
//            nodeR=nodeR.next;
//        }
//        if(nodeA!=null){
//            nodeR.next=nodeA;
//        }
//        if(nodeB!=null){
//            nodeR.next=nodeB;
//        }
//        return dummy.next;
//    }
public static void main(String[]args){
    链表 test =new 链表();


    test.reorderList();
}

}
