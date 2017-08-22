package lambdasinaction.optionals.refacter;


import java.util.Optional;

/**
 * @author: wangyingjie1
 * @version: 1.0
 * @createdate: 2017-08-22 16:48
 */
public class MobileTest {

    public static void main(String[] args) {
        ScreenResolution resolution = new ScreenResolution(750,1334);
        DisplayFeatures dfeatures = new DisplayFeatures("4.7", Optional.of(resolution));
        Mobile mobile = new Mobile(2015001, "Apple", "iPhone 6s", Optional.of(dfeatures));

        int width = mobile.getMobileScreenWidth(Optional.of(mobile));

        System.out.println(width);
    }

}