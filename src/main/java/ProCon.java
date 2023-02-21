import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: Naomi
 * @Date: 2021/4/1 0:52
 */

// 生产者消费者问题
public class ProCon {
    public static void main(String[] args) {

        // 使用JUC下面的ReentrantLock可重入锁实现
        LockProCon lockProCon = new LockProCon();

        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                lockProCon.producer();
        }, "Lock-Producer").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                lockProCon.consumer();
        }, "Lock-Consumer").start();

        // 使用synchronized实现
        SyncProCon syncProCon = new SyncProCon();

        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                syncProCon.producer();
        }, "Sync-Producer").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++)
                syncProCon.consumer();
        }, "Sync-Consumer").start();

    }
}

// 使用JUC下面的ReentrantLock可重入锁实现
class LockProCon {
    private int flag = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    public void producer() {
        lock.lock();
        try {
            while (flag != 1) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> producer");

            flag = 2;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void consumer() {
        lock.lock();
        try {
            while (flag != 2) {
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> producer");

            flag = 1;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

// 使用synchronized实现
class SyncProCon {
    private int flag = 1;

    public synchronized void producer() {
        try {
            while (flag != 1) {
                wait();
            }
            System.out.println(Thread.currentThread().getName() + "=> producer");

            flag = 2;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void consumer() {
        try {
            while (flag != 2) {
                wait();
            }
            System.out.println(Thread.currentThread().getName() + "=> consumer");

            flag = 1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}