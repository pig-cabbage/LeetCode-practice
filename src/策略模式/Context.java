package 策略模式;

public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void operate() {
        this.strategy.operate();
    }


//    public static void main(String[] args) {
//        Context context;
//        context = new Context(new 算法一());
//        context.operate();
//        context = new Context(new 算法二());
//        context.operate();
//    }
}
