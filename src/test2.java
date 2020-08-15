import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

public class test2  {
    private Map<Integer,Integer>temp=new TreeMap<>();
    private int maxvalue=Integer.MIN_VALUE;
    private Queue<Integer>queue=new LinkedList<>();
    public test2(){

    }
    public int max_value() {
        if(queue.size()==0){
            return -1;
        }
        return this.maxvalue;
    }

    public void push_back(int value) {
        if(value>this.maxvalue){
            this.maxvalue=value;

        }
        if(temp.containsKey(value)){
            temp.put(value,temp.get(value)+1);
        }else{
            temp.put(value,1);
        }
        queue.add(value);

    }

    public int pop_front() {
        if(queue.size()==0){
            return -1;
        }
        int oo=queue.peek();
        if(oo==this.maxvalue){

            if(temp.get(oo)==1){
                this.maxvalue=new ArrayList<Map.Entry<Integer,Integer>>(temp.entrySet()).get(1).getKey();
            }
        }
        if(temp.get(oo)==1){
            temp.remove(oo);
        }else{
            temp.put(oo,temp.get(oo)-1);
        }

        return queue.poll();
    }
    public static void main(String []args){


    }



}
