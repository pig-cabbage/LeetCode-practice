import java.util.ArrayList;
import java.util.List;

public class ≈≈–Ú {


    //—°‘Ò≈≈–Ú
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
    //√∞≈›≈≈–Ú
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
    //øÏÀŸ≈≈–Ú
    public void quickSort(int [] input, int low,int high) {
        if (low >= high)
            return;
        else {
            int i = low;
            int j = high;
            int temp = input[low];
            while (i < j) {
                while (temp < input[j] && i < j) {
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
            quickSort(input, low, j - 1);
            quickSort(input, j + 1, high);
        }
    }
    //πÈ≤¢≈≈–Ú
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
}
