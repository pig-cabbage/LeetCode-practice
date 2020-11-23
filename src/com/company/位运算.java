package com.company;

public class 位运算 {

  //给定一个十进制数M，以及需要转换的进制数N。将十进制数M转化为N进制数
  public String solve (int M, int N) {
    // write code here
    boolean flag = M >= 0;
    M = Math.abs(M);
    StringBuilder result = new StringBuilder();
    while(M > 0){
      if(M % N >= 10){
        result.append((char)(M % N - 10 + 'A'));
      }else {
        result.append(M % N);
      }
      M = M/N;
    }
    if(!flag){
      return "-"+result.reverse().toString();
    }
    return result.reverse().toString();
  }

  public static void main(String[] args){
    byte a= 10;
    int b =a;
    位运算 s= new 位运算();
    System.out.println(s.solve(10, 3));
  }
}
