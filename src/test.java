import java.util.*;

public class test {









        int count=0;
        public int countSubString(String s){
            boolean [][]dp=new boolean[s.length()][s.length()];
            dp[0][0]=true;
            count=1;
            for (int i=1;i<s.length();i++){
                dp[i][i]=true;
                count++;
                if(s.charAt(i-1)==s.charAt(i)){
                    dp[i-1][i]=true;
                    count++;
                }
            }
            for(int i=3;i<s.length();i++){
                for(int j=0;i+j-1<s.length();j++){
                    int k=i+j-1;
                    if(dp[j+1][k-1] && s.charAt(j)==s.charAt(k)){
                        dp[j][k]=true;
                        count++;
                    }
                }
            }
            return count;
        }

        public boolean canGet(int [] zu){
            int len=zu.length;
            if(len==0)
                return false;
            boolean [] dp=new boolean[len];
            dp[len-1]=true;

                for(int i=len-2;i>=0;i--){
                    for(int j=1;j<=zu[i];j++){
                        if(i+j<len&&dp[i+j]==true){
                            dp[i]=true;
                            break;
                        }
                    }
                }
                return dp[0];
            }


        public List<List<Integer>> removeCHongfu(int [][] input){
            int len=input.length;
            List<List<Integer>>result=new ArrayList<>();
            if(len>=1){
                Arrays.sort(input,(v1,v2)->v1[0]-v2[0]);
                int min=input[0][0];
                int max=input[0][1];
                for(int i=1;i<len;i++){
                    if(input[i][0]<max){
                        min=Math.min(min,input[i][0]);
                        max=Math.max(max,input[i][1]);
                    }else{
                        List<Integer>temp=new ArrayList<>();
                        temp.add(min);
                        temp.add(max);
                        result.add(temp);
                        min=input[i][0];
                        max=input[i][1];

                    }
                }

            }
            return result;


        }
        public int getMax(int[][] input){
            Arrays.sort(input, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return (o1[0]-o2[0])*(o1[0]-o2[0])*(o1[1]-o2[1]);
                }
            });
            System.out.println(Arrays.toString(input[0]));
            int[]dp=new int[input.length];
            dp[input.length-1]=1;
            int result=1;
            for (int i=input.length-2;i>=0;i--){
                int temp=0;
                for(int j=i+1;j<input.length;j++){
                    if(input[i][0]<input[j][0] && input[i][1]<input[j][1] && dp[j]>temp){
                        temp=dp[j];

                    }
                }
                dp[i]=temp+1;
                result=Math.max(result,dp[i]);
            }
            return result;

        }

        public int findIndex(int[]input,int target){
            for(int i=0;i<input.length;i++){
                if(input[i]==target)
                    return i;
            }
            return -1;

        }




    public void binSort(int []input,int start,int end){
            if(start<end){

                int len=end-start+1;
                binSort(input,start,start+len/3);
                binSort(input,start+len/3+1,start+len*2/3);
                binSort(input,start+len*2/3+1,end);

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
        inner l=new inner(10);

    }
    public static class inner{
            public int kk;
            public inner(int l){
                kk=l;

            }
    }
    public  List<String>result=new ArrayList<>();

    public  void calculate(char[] inputs,boolean[]flag,int length,StringBuffer temp){
        if(temp.length()==length){

            result.add(temp.toString());

            return;
        }else{
            for(int i=0;i<inputs.length;i++){

                if(flag[i]==false){
                    temp.append(inputs[i]);
                    flag[i]=true;
                    calculate(inputs,flag,length,temp);
                    flag[i]=false;
                    temp.deleteCharAt(temp.length()-1);
                }else{
                    continue;
                }
            }
            return ;
        }
    }






}
