package CConcurrent;


import java.util.concurrent.locks.LockSupport;

/**
 * 用两个线程，一个输出字母，一个输出数组，交替输出
 */
public class LockSupportDemo {

    static Thread t1,t2;
    public static void main(String[] args) throws Exception {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        t1 = new Thread(()->{
            for(char c:a1){
                System.out.println(c);
                LockSupport.unpark(t2); //唤醒T2
                LockSupport.park(); //T1阻塞 （当前线程阻塞）
            }
        },"t1");

        t2 = new Thread(() -> {
            for(char c:a2){
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }
        },"t2");

        t1.start();
        t2.start();
    }

}
