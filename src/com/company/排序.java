package com.company;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ���� {
    //ѡ������
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
    //ð������
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
    //��������
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
    //��������
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
    //�鲢����
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
    //������
    public static void sort(int[] arr) {
        //1.�����󶥶�
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //�ӵ�һ����Ҷ�ӽ��������ϣ�������������ṹ
            adjustHeap(arr, i, arr.length);
        }
//        System.out.print(Arrays.toString(arr));
        //2.�����ѽṹ+�����Ѷ�Ԫ����ĩβԪ��
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);//���Ѷ�Ԫ����ĩβԪ�ؽ��н���
            adjustHeap(arr, 0, j);//���¶Զѽ��е���
            System.out.print(Arrays.toString(arr));
        }

    }

    /**
     * �����󶥶ѣ����ǵ������̣������ڴ󶥶��ѹ����Ļ����ϣ�
     *
     * @param arr
     * @param i
     * @param length
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//��ȡ����ǰԪ��i
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {//��i�������ӽ�㿪ʼ��Ҳ����2i+1����ʼ
            if (k + 1 < length && arr[k] < arr[k + 1]) {//������ӽ��С�����ӽ�㣬kָ�����ӽ��
                k++;
            }
            if (arr[k] > temp) {//����ӽڵ���ڸ��ڵ㣬���ӽڵ�ֵ�������ڵ㣨���ý��н�����
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//��tempֵ�ŵ����յ�λ��
    }

    /**
     * ����Ԫ��
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
