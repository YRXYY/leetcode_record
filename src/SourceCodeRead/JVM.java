package SourceCodeRead;

import sun.nio.ch.DirectBuffer;

import java.nio.MappedByteBuffer;

public class JVM {

    /**
     * 堆外内存
     * 用DirectByteBuffer对象进行堆外内存的管理和使用，在对象创建的时候就发呢配堆外内存+
     * 在Cleaner内部通过一个列表，维护一个针对每个directBuffer的一个回收堆外内存的线程对象Runnable
     * 回收发生在Cleaner的clean()
     *
     * 优点：
     *      减少垃圾回收（不参与GC，即减少停顿）
     *      加快复制速度
     *          即提升IO效率，因为从堆内向磁盘/网卡读写数据时，数据会被先复制到堆外内存，然后堆外内存的数据再
     *          写到硬件。直接写入堆外可以避免堆内到堆外的一次数据拷贝。
     * 缺点：
     *      内存难以管理
     *
     * 回收：
     *      主动：从DirectByteBuffer中取出Cleaner 调用clean()
     *      基于GC：堆内的DirectByteBuffer被gc时，就会调用cleaner的clean()
     *          这个方法的问题是，如果有大量DirectButeBuffer对象进入old区，又一直没有CMS GC或者Full gc，物理内存可能会被消耗光，OOM
     *
     *
     *
     */
    void offHeapMemory(){

        //DirectBuffer

        //MappedByteBuffer directBuffer = new DirectByteBuffer();
    }

}
