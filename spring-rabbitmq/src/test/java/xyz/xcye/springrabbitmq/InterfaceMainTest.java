package xyz.xcye.springrabbitmq;

/**
 * @author Administrator 程钦义
 * @blog https://www.xcye.xyz
 * @date 2022/01/13 20:44
 **/

public class InterfaceMainTest {
    public static void main(String[] args) {
        InterFaceTest interFaceTest = name -> {
            return "chuchen";
        };

        System.out.println(interFaceTest);
        interFaceTest.getYear();

    }
}
