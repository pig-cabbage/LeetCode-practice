import java.util.*;

public class ManyThreads {

    private int j;


    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"inc"+j);
    }

    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"dec"+j);

    }
    private class Inc implements Runnable{
        @Override
        public void run(){
            for(int i=0;i<20;i++)
                inc();
        }
    }

    class Dec implements Runnable{
        @Override
        public void run(){
            for (int i=0;i<20;i++)
                dec();
        }
    }
    class Node{
        Node left;
        Node right;
        int value;
        Node(int val){
            value=val;
        }
    }

    class ListNode{
        ListNode next;
        int value;
        ListNode(int val){
            value=val;
        }

    }

    PriorityQueue<Integer>p=new PriorityQueue<>();






    public int[] calculateCishu(int[]input){
        Arrays.sort(input);
        int index=0;
        while(index<input.length){
            int temp=1;
            int tempIndex=index;
            while(tempIndex<input.length&&tempIndex+1<input.length&&input[tempIndex]==input[tempIndex+1]){
                temp+=1;
                tempIndex+=1;
            }
            input[index]=temp;
            index=tempIndex;
        }
        return input;
    }




    public void findTwo(int []input){
        int n=input.length;
        if(n==0) return ;
        int res=0;
        for(int i=0;i<n;i++){
            res=res^input[i];
        }
        int count=1;
        while((count&res)==0)
            count=count<<1;

        int res1=0;
        int res2=0;
        for(int i=0;i<n;i++){
            if((count&input[i])==0)
                res1=res1^input[i];
            else
                res2=res2^input[i];
        }
    }

    public int diedaiJiafa(int n){
        if(n==1)
            return 1;
        else
            return n+diedaiJiafa(n-1);
    }


    public boolean panduan(int []input){
        for(int i=0;i<input.length;i++){
            int res=input[i];
            if(res<0|| res==input.length) {
                i++;
                continue;
            }
            if(input[res]<0){
                input[res]--;
                input[i]=input.length;
                continue;
            }
            input[i]=input[res];
            input[res]=-1;
        }
        for(int i=0;i<input.length;i++){
            if(input[i]<-1)
                return true;
        }
        return false;

    }



    static class OOMObject{

    }


















    public static void main(String [] args){
        ManyThreads many=new ManyThreads();
//        Inc inc =many.new Inc();
//        Dec dec=many.new Dec();
//        for (int i = 0; i < 2; i++) {
//            Thread t = new Thread(inc);
//            t.start();
//            t = new Thread(dec);
//            t.start();
//        }
//        String[] k={"3","32","321"};
//        Arrays.sort(k);
//        System.out.println(Arrays.toString(k));
//        StringBuffer path=new StringBuffer("dsada");
//        System.out.println(path.indexOf("d"));
        int[]pre={1,2,4,7,3,5,6,8};
        int[]in={4,7,2,1,5,3,8,6};
//        many.createTree(pre,0,pre.length-1,in,0,in.length-1);
        Queue<Integer>q=new LinkedList<>();
        ArrayList<Integer>r=new ArrayList<>();
        Collections.sort(r);
        Map<String,Object>oo=new HashMap<>();

        Stack<Integer>l=new Stack<>();
        l.push(1);

        q.add(1);
        q.add(2);
        System.out.println();
        System.out.println(q.toString());


//        List<OOMObject>a=new ArrayList<>();
//        while(true){
//            a.add(new OOMObject());
//        }
//        int[]input={6,-3,-2,7,-15,1,2,2};
//
//        Arrays.sort(input);
//        int l=4000;
//
//        System.out.println(Integer.toBinaryString(4000));

    }

}
