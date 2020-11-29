package pattern;

/**
 * 生产者消费者模式
 * 能解决：
 *      生产和消费速度不匹配
 *      软件开发过程中解耦
 */

/**
 * 预先知识：
 *      wait(): Object里的方法，作用是将线程置于预执行队列，并在wait()处停止，等待唤醒通知
 *              只能在同步代码块或同步方法中执行（必在synchronized中）
 *              线程会释放锁，重新和其他线程竞争锁
 *    notify(): 也是只能在同步代码块中调用
 *              使停止的线程继续执行，如果有多个，线程规划器会随机挑选一个呈wait状态的线程
 *              notify调用后不会立即释放锁，而是退出同步代码块后
 *    notifyAll()就是唤醒所有等待线程
 */



import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 出现阻塞的情况：
 *      1. sleep()主动放弃CPU
 *      2. wait()等待通知
 *      3. 阻塞式IO
 *      4. synchronized同步代码块
 *  wait后进入阻塞队列，唤醒后进入就绪队列，等待CPU调度
 */
public class ProducerAndConsumer /*implements Callable */{
}

class Goods{
    private int id;
    private String name;

    public Goods(int id,String name){
        this.id = id;
        this.name = name;
    }
}

class Producer implements Runnable{
    private Goods goods;

    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (TestPC.queue){
                if(TestPC.queue.size()<TestPC.MAX_POOL){
                    goods = new Goods(1,"商品");
                    TestPC.queue.add(goods);
                    System.out.println(Thread.currentThread().getName()+"生产商品");
                }else{
                    try{
                        TestPC.queue.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Consumer implements Runnable{
    @Override
    public void run(){
        while(true){
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            synchronized (TestPC.queue){
                if(!TestPC.queue.isEmpty()){
                    TestPC.queue.poll();
                    System.out.println(Thread.currentThread().getName()+"消费商品");
                }else{
                    TestPC.queue.notify();
                }
            }
        }
    }
}

class TestPC{
    public static final int MAX_POOL=10;
    public static final int MAX_PRODUCER=5;
    public static final int MAX_CONSUMER=4;
    public static Queue<Goods> queue = new ArrayBlockingQueue<>(MAX_POOL);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        for(int i=0;i<MAX_PRODUCER;i++){
            Thread threadA = new Thread(producer,"生产者"+i);
            threadA.start();
        }
        for(int i=0;i<MAX_CONSUMER;i++){
            Thread threadB = new Thread(consumer,"消费者"+i);
            threadB.start();
        }

        for(;;){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("================");
            System.out.println("size:"+queue.size());
            System.out.println("================");
        }
    }
}


