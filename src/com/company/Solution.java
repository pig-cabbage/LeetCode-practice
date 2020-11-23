package com.company;
import java.util.*;
public class Solution {
  public  class LRUCache{
    private class ListNode{
      private int key;
      private int value;
      ListNode prev;
      ListNode next;

      public ListNode(int key,int value){
        this.key=key;
        this.value=value;
      }

      public ListNode(){

      }
    }
    private int capacity=1024;

    private Map<Integer,ListNode> table=new HashMap<>();

    private ListNode head;

    private ListNode tail;

    public  LRUCache(int k){
      head=new ListNode();
      tail=new ListNode();
      head.next=tail;
      head.prev=null;
      tail.prev=head;
      tail.next=null;
      capacity = k;
    }
    public int get(int key){
      if(!table.containsKey(key)){
        return -1;
      }else{
        ListNode node = table.get(key);
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.next=head.next;
        head.next.prev=node;
        node.prev=head;
        head.next=node;
        return node.value;
      }
    }
    public void set(int key, int value){
      ListNode newNode = new ListNode(key, value);
      if(!table.containsKey(key)) {
        if (table.size() == capacity) {
          table.remove(tail.prev.key);
          tail.prev = tail.prev.prev;
          tail.prev.prev.next = tail;
          table.put(key, newNode);
          newNode.next = head.next;
          head.next.prev = newNode;
          newNode.prev = head;
          head.next = newNode;
        } else {
          table.put(key, newNode);
          newNode.next = head.next;
          head.next.prev = newNode;
          newNode.prev = head;
          head.next = newNode;
        }
      }else{
        ListNode node = table.get(key);
        node.value = value;
        node.prev.next=node.next;
        node.next.prev=node.prev;
        node.next=head.next;
        head.next.prev=node;
        node.prev=head;
        head.next=node;
      }
    }

  }



  public int[] LRU (int[][] operators, int k) {
    // write code here
    LRUCache cache = new LRUCache(k);
    // write code here
    List<Integer> temp = new ArrayList<>();
    for(int i = 0; i< operators.length; i++){
      if(operators[i][0] == 1){
        cache.set(operators[i][1], operators[i][2]);
      }else{
        temp.add(cache.get(operators[i][1]));
      }
    }
    return temp.stream().mapToInt(Integer::intValue).toArray();

  }
}
