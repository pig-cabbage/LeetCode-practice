import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

//实现LRU算法
public class LRUCache<V> {


    //双向链表内部类
    private class ListNode<K,V>{
        private K key;
        private V value;
        ListNode<K,V>prev;
        ListNode<K,V>next;

        public ListNode(K key,V value){
            this.key=key;
            this.value=value;
        }

        public ListNode(){

        }
    }

    private int capacity=1024;

    //现存节点记录表
    private Map<String,ListNode<String,V>>table=new HashMap<>();

    //双向链表头部
    private ListNode<String,V> head;

    //双向链表尾部
    private ListNode<String,V> tail;


    public  LRUCache(){
        head=new ListNode<>();
        tail=new ListNode<>();
        head.next=tail;
        head.prev=null;
        tail.prev=head;
        tail.next=null;
    }
    public LRUCache(int capacity){
        this();
        this.capacity=capacity;
    }




    public V get(String key){
        ListNode<String,V>node=table.get(key);
        if(node==null)
            return null;
        else{
            node.prev.next=node.next;
            node.next.prev=node.prev;
            node.next=head.next;
            head.next.prev=node;
            node.prev=head;
            head.next=node;
            return node.value;
        }
    }

    public boolean put(String key,V value){
        ListNode<String,V>node=table.get(key);
        if(node==null){
            if(table.size()>capacity){
                table.remove(tail.prev.key);
                tail.prev.next=tail.next;
                tail.next=null;
                tail=tail.prev;
                ListNode<String,V>newNode=new ListNode<>(key,value);
                table.put(key,newNode);
                newNode.next=head.next;
                head.next.prev=newNode;
                newNode.prev=head;
                head.next=newNode;

            }
        }else{
            node.prev.next=node.next;
            node.next.prev=node.prev;
            node.next=head.next;
            head.next.prev=node;
            node.prev=head;
            head.next=node;
        }
        return true;
//


    }






}
