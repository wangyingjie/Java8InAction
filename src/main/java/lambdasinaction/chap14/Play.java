package lambdasinaction.chap14;

/**
 * Created by wangyingjie1 on 2017/8/27.
 */
public class Play {

    private static Play ourInstance = new Play();

    public static Play getInstance() {
        return ourInstance;
    }

    private Play() {
        //
    }
}
