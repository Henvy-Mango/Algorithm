package plus.naomi.juc;

/**
 * @Description:
 * @Author: Naomi
 * @Date: 2021/4/1 12:24
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 假设有这么一个类：
 * <p>
 * class ZeroEvenOdd {
 * public ZeroEvenOdd(int n) { ... }      // 构造函数
 * public void zero(printNumber) { ... }  // 仅打印出 0
 * public void even(printNumber) { ... }  // 仅打印出 偶数
 * public void odd(printNumber) { ... }   // 仅打印出 奇数
 * }
 * 相同的一个 ZeroEvenOdd 类实例将会传递给三个不同的线程：
 * <p>
 * 线程 A 将调用 zero()，它只输出 0 。
 * 线程 B 将调用 even()，它只输出偶数。
 * 线程 C 将调用 odd()，它只输出奇数。
 * 每个线程都有一个 printNumber 方法来输出一个整数。请修改给出的代码以输出整数序列 010203040506... ，其中序列的长度必须为 2n。
 */
public class FooZeroEvenOdd {
    private final Lock lock = new ReentrantLock();
    private final Condition c0 = lock.newCondition();
    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private int n;
    private volatile int flag = 0;
    private volatile int count = 1;
    public FooZeroEvenOdd(int n) {
        this.n = n;
    }

    public static void main(String[] args) {
        FooZeroEvenOdd fooZeroEvenOdd = new FooZeroEvenOdd(5);

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                fooZeroEvenOdd.zero();
            }
        }, "Lock-zero").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                fooZeroEvenOdd.even();
            }
        }, "Lock-even").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                fooZeroEvenOdd.odd();
            }
        }, "Lock-odd").start();


    }

    public void zero() {
        lock.lock();
        try {
            while (count <= n) {
                if (flag != 0) {
                    c0.await();
                }
                System.out.print(0 + " ");
                if (count % 2 == 0) {
                    flag = 2;
                    c2.signal();
                } else {
                    flag = 1;
                    c1.signal();
                }
                // 保证最后一次可以使当前线程等待
                c0.await();
            }
            // 防止最后一次其他线程等待
            c1.signal();
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 偶数
    public void even() {
        lock.lock();
        try {
            while (count <= n) {
                if (flag != 2) {
                    c2.await();
                } else {
                    System.out.print(count++ + " ");
                    flag = 0;
                    c0.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 奇数
    public void odd() {
        lock.lock();
        try {
            while (count <= n) {
                if (flag != 1) {
                    c1.await();
                } else {
                    System.out.print(count++ + " ");
                    flag = 0;
                    c0.signal();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
