package leetcode;

/**
 * @Description:
 * @Author: Naomi
 * @Date: 2021/4/1 1:17
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 * for (int i = 0; i < n; i++) {
 * print("foo");
 * }
 * }
 * <p>
 * public void bar() {
 * for (int i = 0; i < n; i++) {
 * print("bar");
 * }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 */
public class FooBar {
    public static void main(String[] args) {
        FooBar fooBar = new FooBar(5);
        new Thread(() -> {
            fooBar.foo();
        }).start();
        new Thread(() -> {
            fooBar.bar();
        }).start();
    }

    private int n;

    private int flag = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public FooBar(int n) {
        this.n = n;
    }

    public void foo() {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag != 1) {
                    condition.await();
                }
                System.out.print("foo");
                flag = 2;
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public void bar() {
        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (flag != 2) {
                    condition.await();
                }
                System.out.println("bar");
                flag = 1;
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
