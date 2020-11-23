package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 排序 {
    //选择排序
    public void selectSort(int [] input){
        int len=input.length;
        for (int i=0;i<len-1;i++){
            int temp=input[i];
            for (int j=i+1; j<len;j++){
                if(input[j]<temp)
                    temp = input[j];


            }
            input[i]=temp;
        }
    }
    //冒泡排序
    public void bobbleSort(int [] input){
        int len=input.length;
        for (int i=len-1;i>=0;i--){
            boolean flag=false;
            for(int j=0;j<i;j++){
                if(input[j]>input[j+1]){
                    flag=true;
                    int temp=input[j];
                    input[j]=input[j+1];
                    input[j+1]=temp;
                }
            }
            if(!flag){
                break;
            }
        }
    }
    //插入排序
    public void chaRuPai(int[] input){
        for(int i = 1; i <input.length; i++){
            int temp = input[i];
            int index = i;
            for(int j = i -1; j>=0; j--){
                if(input[j] > temp){
                    temp = input[j];
                    index = j;
                }else
                    break;
            }
            input[index] = input[i];
            input[i] = temp;
        }
    }
    //快速排序
    public void quickSort(int [] input, int low,int high) {
        if (low >= high)
            return;
        else {
            int i = low + 1;
            int j = high;
            int temp = input[low];
            while (i < j) {
                while (temp <= input[j] && i < j) {
                    j--;
                }
                while (temp > input[i] && i < j) {
                    i++;
                }
                if (i < j) {
                    int t = input[i];
                    input[i] = input[j];
                    input[j] = t;
                }

            }
            input[low] = input[i];
            input[i] = temp;
            quickSort(input, low, i - 1);
            quickSort(input, i + 1, high);
        }
    }
    //归并排序
    public void binSort(int []input,int start,int end){
        if(start<end){

            int len=end-start+1;
//            binSort(input,start,start+len/3);
//            binSort(input,start+len/3+1,start+len*2/3);
//            binSort(input,start+len*2/3+1,end);

//                merge(input,start,mid,end);


        }

    }

    public void merge(int [] input,int a,int b,int c){
        System.out.println("sad");
        int i=a;
        int j=b+1;
        int []temp=new int[c-a+1];
        int index=0;
        while(i<=b && j<=c){
            if(input[i]<=input[j]){
                temp[index++]=input[i++];
            }else{
                temp[index++]=input[j++];
            }
        }
        while(i<=b){
            temp[index++]=input[i++];
        }
        while(j<=c){
            temp[index++]=input[j++];
        }
        index = 0;
        while ( a<=c ) {
            input[a ++] = temp[index ++];
        }
    }
    //堆排序
    public static void sort(int[] arr) {
        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
//        System.out.print(Arrays.toString(arr));
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(arr, 0, j);//重新对堆进行调整
            System.out.print(Arrays.toString(arr));
        }

    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//从i结点的左子结点开始，也就是2i+1处开始
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if (arr[k] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param a
     * @param b
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }
}
