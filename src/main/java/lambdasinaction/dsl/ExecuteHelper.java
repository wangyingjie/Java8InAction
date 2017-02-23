package lambdasinaction.dsl;

/**
 * Java8 简洁的匿名内部类使用Demo
 * Created by wangyingjie1 on 2017/2/23.
 */
public class ExecuteHelper {

    public static void execute(Execute execute) {
        execute.doExecute();
    }

    private interface Execute {
        void doExecute();
    }

    //测试匿名内部类的使用方式
    public static void main(String[] args) {
        ExecuteHelper.execute(() -> Study.print());
    }

    public static class Study {
        public static void print() {
            System.out.println("Hello world*******");
        }
    }
}
