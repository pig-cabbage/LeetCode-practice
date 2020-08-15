import jdk.internal.org.objectweb.asm.Handle;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 暴力解法 {
     public static class Point {
         int x;
         int y;
         public Point(int newx,int newy){
             x=newx;
             y=newy;
         }

 }
    //计算1-n有多少个自守数
    //自守数是指一个数的平方的尾数等于该数自身的自然数。
    public  int CalcAutomorphicNumbers( )
    {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int result=0;
        for(int i=0;i<=n;i++){
            String str=String.valueOf(i);
            String bigStr=String.valueOf(i*i);
            if(str.equals(bigStr.substring(bigStr.length()-str.length(),bigStr.length()))){
                result+=1;
            }

        }
        System.out.println(result);
        return result;
    }
    //对于给定的n个位于同一二维平面上的点，求最多能有多少个点位于同一直线上
    public int maxPoints (Point[] points) {
        // write code here
        int len=points.length;
        int maxValue=1;
        int index=0;
        Point max=new Point(0,0);
        for(int a=0;a<len-1;a++) {
            Map<Integer,Integer>tempy=new HashMap<>();
            Map<Integer,Integer>tempx=new HashMap<>();
            Map<Double,Integer>temp=new HashMap<>();
            for (int i = a+1; i < len; i++) {
                if((points[i].x==points[a].x&&points[i].y==points[a].y)) {
                        continue;
                }
                if (points[i].x == points[a].x) {
                    if (tempx.containsKey(points[i].x)) {
                        if (tempx.get(points[i].x) + 1 > maxValue) {
                            maxValue = tempx.get(points[i].x) + 1;
                            max=points[i];
                            index=i;
                        }
                        tempx.put(points[i].x, tempx.get(points[i].x) + 1);
                    } else {
                        tempx.put(points[i].x, 2);
                        if(2>maxValue){
                            max=points[i];
                            maxValue=2;
                            index=i;
                        }
                    }
                }
                if (points[i].y == points[a].y) {
                    if (tempy.containsKey(points[i].y)) {
                        if (tempy.get(tempy.get(points[i].y)) + 1 > maxValue) {
                            maxValue = tempy.get(points[i].y) + 1;
                            max=points[i];
                            index=i;
                        }
                        tempy.put(points[i].y, tempx.get(points[i].y) + 1);
                    } else {
                        tempy.put(points[i].y, 2);
                        if(2>maxValue){
                            maxValue=2;
                            max=points[i];
                            index=i;
                        }
                    }
                }
                if (points[i].x != points[a].x && points[i].y != points[a].y) {
                    double xielv = (double) (points[a].y - points[i].y) / (points[a].x - points[i].x);
                        System.out.println(xielv);
                        if (temp.containsKey(xielv)) {
                            if (temp.get(xielv) + 1 > maxValue) {
                                maxValue = temp.get(xielv) + 1;
                                max=points[i];
                                index=i;
                            }
                            temp.put(xielv, temp.get(xielv) + 1);
                        } else {
                            temp.put(xielv, 2);
                            if(2>maxValue){
                                max=points[i];
                                maxValue=2;
                                index=i;
                            }
                        }
                }
            }
            for(int i=0;i<len;i++){
                if(points[i].x==max.x&&points[i].y==max.y&&i!=index){
                    maxValue+=1;
                }
            }
        }

        return maxValue;

    }
    public static void main(String[] args) {
        暴力解法 temp=new 暴力解法();
        Point a=new Point(0,0);
        Point b=new Point(-1,-1);
        Point c=new Point(2,2);

        Point[]k=new Point[3];
        k[0]=a;
        k[1]=b;
        k[2]=c;
        System.out.println(temp.maxPoints(k));

    }
}
