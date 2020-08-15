import java.util.concurrent.atomic.LongAccumulator;

public class Ô­×ÓÀà {
    public static void main(String []args){
        LongAccumulator test=new LongAccumulator(((left, right) -> left+right*2),666);
        test.accumulate(2);
        System.out.println(test);
    }
}
