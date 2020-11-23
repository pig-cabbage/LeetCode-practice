package com.company;

import java.util.HashMap;
import java.util.Map;

public class Ç°×ºÊ÷ {
  public class TrieNode{
    public int path;
    public int end;
    public Map<Character, TrieNode> next;

    public TrieNode(){
      path = 0;
      end = 0;
      next = new HashMap<>();
    }
  }
  private TrieNode root;
  public Ç°×ºÊ÷(){
    root = new TrieNode();
  }

  public void insert(String word){
    if(word == null || word.equals(""))  return ;
    TrieNode node = root;
    for(int i = 0; i<word.length(); i++){
      char ch = word.charAt(i);
      if(!node.next.containsKey(ch)) {
        node.next.put(ch,new TrieNode());
      }
      node = node.next.get(ch);
      node.path++;
    }
    node.end++;
  }

  public boolean search(String word){
    if(word == null || word.equals("")) return false;
    TrieNode node = root;
    for(int i = 0; i<word.length(); i++){
      char ch = word.charAt(i);
      if(!node.next.containsKey(ch) ||  node.next.get(ch).path ==0) return false;
      node = node.next.get(ch);
    }
    if(node.end == 0) return false;
    return true;
  }
  public int startsWith(String word){
    if(word == null || word.equals("")) return 0;
    TrieNode node = root;
    for(int i = 0; i<word.length(); i++){
      char ch = word.charAt(i);
      if(!node.next.containsKey(ch) || node.next.get(ch).path ==0) return 0;
      node = node.next.get(ch);
    }
    return node.path;
  }
  public void delete(String word){
    if(word == null || word.equals("") || !search(word)) return ;
    TrieNode node = root;
    for (int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if(node.next.get(ch).path == 0)
      return;
      node.next.get(ch).path--;
      node = node.next.get(ch);
    }
    node.end--;
  }
}
