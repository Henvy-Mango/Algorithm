package leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Naomi
 * @Date: 2021/4/1 0:22
 */

/**
 * 三个不同的线程 A、B、C 将会共用一个 Foo 实例。
 *
 * 一个将会调用 first() 方法
 * 一个将会调用 second() 方法
 * 还有一个将会调用 third() 方法
 * 请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。
 */
public class Foo {
    private int num = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition c1 = lock.newCondition();
    private final Condition c2 = lock.newCondition();
    private final Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        Foo foo = new Foo();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) foo.first();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) foo.second();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) foo.third();
        }, "C").start();
    }


    public void first() {
        lock.lock();
        try {
            while (num != 1) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> first");

            num = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void second() {
        lock.lock();
        try {
            while (num != 2) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> second");

            num = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void third() {
        lock.lock();
        try {
            while (num != 3) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> third");

            num = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
