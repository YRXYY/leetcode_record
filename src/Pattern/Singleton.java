package Pattern;

import java.util.concurrent.atomic.AtomicLong;


/**
 * 第二个判断的地方：
 * 假定线程A,B
 * 线程A首先判定instance=null，准备进入同步代码块（但仍未进入），A暂时挂起，
 * 此时B获得时间片，（此时A并未创建instance），则B进入同步代码块，创建instance。
 * A得到时间片后，若不再判断instance，就会创建两个实例。
 *
 * 反过来看，判断instance必须在进入同步代码块后判断才算有效。第一次判断仅是为了提高效率。
 *
 * volatile关键字是为了提供instance对象的可见性
 * 扯到工作区、主内存，（对应此例就是A获取的instance失效，要获取最新的instance）
 * volatile--内存屏障，阻止了指令重排序
 *
 * 至于为什么不禁止指令重排序，双重检验就可能失效？
 * 指令重排序的存在，导致初始化Singleton和将对象地址赋值给instance字段的顺序不正确。
 * 在某个线程创建单例对象时，在构造函数被调用前，就为该对象分配了内存空间，并将对象的字段设置为默认值
 * 此时就已经可以将分配的内存地址赋值给instance字段了，然而该对象可能还没初始化完成。
 * 若紧接着另一个线程来调用getInstance()，获取到的就不是正确的单例对象，程序出错。
 * volatile就是禁止指令重排序，保证instance变量在被复制的时候对象已经被正确的初始化了。
 *
 *volatile：
 * 1. 多线程之间的可见性，每次读到volatile修饰的变量，都是最新值（不是的话会失效，重新读）
 * 2  由于禁止指令重排序，一定程度上降低了性能。
 * 3  实践角度，volatile的重要作用就是和CAS结合，保证了原子性。？？
 *
 * volatile保证下一个读取操作会在前一个写操作之后发生
 */
public class Singleton{
    private volatile static Singleton instance;
    private Singleton() {}
    public static Singleton getInstance() {
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

/**
 * 懒汉式单例，在第一次调用的时候实例化自己
 * 是线程不安全的，详见双重判断
 */
class Singleton_lazy{
    private Singleton_lazy() {}
    private static Singleton_lazy instance = null;
    //静态工厂方法
    //线程不安全
    public static Singleton_lazy getInstance(){
        if(instance == null){
            instance = new Singleton_lazy();
        }
        return instance;
    }
    //线程安全
    //public static synchronized Singleton_lazy getInstance(){}
}

/**
 * 静态内部类实现
 * 线程安全，而且是用到的时候才启动，性能也较好
 */
class Singleton_inner{
    private Singleton_inner(){}
    private static class InnerClass{
        private static final Singleton_inner instance = new Singleton_inner();
    }
    public static Singleton_inner getInstance(){
        return InnerClass.instance;
    }
}

/**
 * 饿汉式，线程安全，但是性能较低，不支持延迟加载？？
 * 即使没有用到，也加载了，浪费内存
 *
 * 但也有人反对：
 * 如果占用资源多，应该fast-fail。程序启动时初始化单例，尽早OOM，防止正常运行的时候OOM
 * 加载比较耗时，更应该在初始化时加载好了，而不是运行时占用正常请求的时长，导致请求缓慢
 * 如果没被用到，更应该直接删掉，不应该加进来。（？）
 */
class Singleton_hungry{
    private Singleton_hungry() {}
    private static Singleton_hungry instance = new Singleton_hungry();
    public Singleton_hungry getInstance(){
        return instance;
    }
}


/**
 * 最安全的实现方式：枚举类型
 * 通过java枚举类型本身的特性，保证了实例创建的线程安全性和实例的唯一性
 */
//此处本应该是public
//public
enum IdGeneratorEnum{
    instance;
    private AtomicLong id = new AtomicLong(0);
    public long getId(){
        return id.incrementAndGet();
    }
}

/**
 * 为什么最安全？
 * 饿汉式、懒汉式、双重检查、静态内部类都无法避免被反序列化和反射生成多个实例
 * 枚举方式不仅能避免多线程，还能防止反序列化和反射的破坏。
 */

