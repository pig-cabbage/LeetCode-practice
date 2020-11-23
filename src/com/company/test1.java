package com.company;


import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class test1 {
    private static void readFile(String filePath) {
        File file = new File(filePath);
        String result;
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
    public String bigAdd(String a, String b)  {
        try {
            // code
            return "dsad";
        } catch (Exception e) {
            throw e;
        }
    }
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }
    public String bigAdd(String a, String b, int c){
        return "dsada";
    }

    public static  void main(String[] args) throws CloneNotSupportedException {
        Set<Integer> a = new HashSet<>();
        Set<String> b = new HashSet<>();
        Set result = union(a,b);
//        Arrays.sort(temp, Comparator.comparingInt((Á´±í a) -> a.s ));
//         Á´±í s = new Á´±í();
//        System.out.println(s.clone());
     }


}
