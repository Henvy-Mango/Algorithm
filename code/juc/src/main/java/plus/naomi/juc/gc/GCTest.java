package plus.naomi.juc.gc;

/**
 * @author Naomi
 * @date 2022/8/23 15:49
 */
public class GCTest {
    public static void main(String[] args) {
        // JVM参数 -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime
        byte[] allocation1, allocation2;
        allocation1 = new byte[(int) (30900 * 1024 / 0.52)];
        allocation2 = new byte[1500 * 1024];
        System.gc();
    }
}
