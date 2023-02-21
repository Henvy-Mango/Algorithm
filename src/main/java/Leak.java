import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author Naomi
 * @description
 * @date 2021/10/22 15:45
 */
public class Leak {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 99999;

    public Leak() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000L);
        Leak leak = new Leak();
        for (int i = 0; i < 10000; i++) {
            leak.push("object" + i);
        }

        System.out.println("添加完毕");

        Thread.sleep(10000L);

        for (int i = 0; i < 10000; i++) {
            System.out.println(leak.pop());
        }

        Thread.sleep(10000L);
        System.out.println("释放完毕");
    }
}
