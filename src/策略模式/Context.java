package ����ģʽ;

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
//        context = new Context(new �㷨һ());
//        context.operate();
//        context = new Context(new �㷨��());
//        context.operate();
//    }
}
