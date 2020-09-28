package EssentialWord;

/**
 * 线程的有序性
 * 指令可能不会按照顺序执行
 * 在此例中，一般不会    a==0&&b==0     ,至多一个为0
 *
 * 但是单线程的两条语句，未必是按顺序执行
 * as-if-serial：看起来是序列化
 * 但CPU为了提高效率，可能会重排序
 *若不想要重排序，需要用《内存屏障》禁止重排序。   内存屏障是特殊指令，看到这种指令，前面的必须执行完，后面才能执行
 * 所有实现JVM规范的虚拟机，必须实现四个屏障（java级别的屏障）
 * LoadLoadBarrier  LoadStore   StoreLoad   SS      （load就是读，store就是写）
 ******LoadLoad屏障就是，前面是一个读指令，后面是一个读指令，这两个指令不能交换位置
 * volatile修饰的，读写都不可以重排序
 *
 * 不同硬件实现内存屏障的方式不同，Java模型屏蔽了这种底层差异，由JVM来为不同平台生成相应机器码
 * java内存屏障主要有Load和Store两类
 * 对于Load Barrier：在读指令前插入读屏障，可以让高速缓存中的数据失效，重新加载主存中的数据
 * Store Barrier：写指令后插入屏障，让写入缓存的最新数据写入主内存。（否则让CPU决定什么时候写回主内存，不是即时的）
 *  LoadLoad：       load1  LoadLoad  load2      保证load1的数据加载先于load2（及其后续load）
 *  SS:             store1  SS  store2
 *  LoadStore       load1   LS  store2          保证load1加载的数据先于store2（+后续）
 *
 * volatile实现细节：（JVM层面）
 *   ⬇---StoreStoreBarrier---                volatile读
 *   ⬇   volatile写                       ---LoadLoadBarrier---
 *   ⬇---StoreLoadBarrier---              ---LoadStoreBarrier---
 *
 *问题：
 *      DCL单例要不要加volatile？
 *
 */
public class Volatile {

    static int x, y, a, b;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread() {
                public void run() {
                    a = 1;
                    x = 2;
                }
            };

            Thread two = new Thread() {
                public void run() {
                    b = 1;
                    y = 0;
                }
            };
            one.start();two.start();
            one.join();two.join();
            String result = i + " " + x + " " + y + " " + a + " " + b;
            if (x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }

}
