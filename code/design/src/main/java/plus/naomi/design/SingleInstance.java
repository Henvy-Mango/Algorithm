package plus.naomi.design;

// 使用枚举类的单例模式，线程安全，反射安全，上面的模式全部反射不安全
enum EnumSingle {
    INSTANCE;

    public EnumSingle getInstance() {
        return INSTANCE;
    }
}

/**
 * @Description:
 * @Author: Naomi
 * @Date: 2021/4/5 23:21
 */

// 单例模式
public class SingleInstance {
}

// 饿汉式，线程不安全
class HungrySingle {
    private final static HungrySingle hungrySingle = new HungrySingle();

    // 构造器私有
    private HungrySingle() {
    }

    private static HungrySingle getInstance() {
        return hungrySingle;
    }
}

// 懒汉式 Synchronized，线程安全
class LazySingleSynchronized {

    // volatile 防止指令重排
    private volatile static LazySingleSynchronized lazySingleSynchronized = null;

    /**
     * 1、保证可见性      强制线程每次读取该值的时候都去主存中取值
     * 2、不保证原子性     线程A在执行任务的时候，不能被打扰的，也不能被分割。要么同时成功，要么同时失败。
     * 3、防止指令重排
     */

    /**
     * 1. 分配内存空间
     * 2、执行构造方法，初始化对象
     * 3、把这个对象指向这个空间
     * <p>
     * 123
     * 132 A线程
     * B线程拿到一个未初始化的对象 // 此时lazyMan还没有完成构造
     */

    // 构造器私有
    private LazySingleSynchronized() {
    }

    private static LazySingleSynchronized getInstance() {
        // 双重检测锁模式的懒汉式单例
        if (lazySingleSynchronized == null) {
            synchronized (LazySingleSynchronized.class) {
                if (lazySingleSynchronized == null) {
                    lazySingleSynchronized = new LazySingleSynchronized();  // 不是一个原子性操作
                }
            }
        }
        return lazySingleSynchronized;

    }
}

// 静态内部类 实现单例模式，线程安全
class InnerClassSingle {

    // 构造器私有
    private InnerClassSingle() {
    }

    public static InnerClassSingle getInstance() {
        return InnerClass.innerClassSingle;
    }

    // 静态内部类
    static class InnerClass {
        private static final InnerClassSingle innerClassSingle = new InnerClassSingle();
    }
}
