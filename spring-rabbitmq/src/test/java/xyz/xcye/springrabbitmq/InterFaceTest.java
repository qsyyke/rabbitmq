package xyz.xcye.springrabbitmq;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 20:38
 **/

@FunctionalInterface
public interface InterFaceTest {
    String getName(String message);

    static void get() {};

    default void getYear() {
        System.out.println("这是默认的方法");
    };
}
