package com.company;

public class λ���� {

  //����һ��ʮ������M���Լ���Ҫת���Ľ�����N����ʮ������Mת��ΪN������
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
    λ���� s= new λ����();
    System.out.println(s.solve(10, 3));
  }
}
