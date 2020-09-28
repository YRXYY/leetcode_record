package Pattern;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 策略模式
 */
public class Strategy {
    ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) {
        /**
         * ReentrantLock的公平与非公平锁就是策略模式？？但是那个比较复杂，不纯粹
         * 但面试时往这方面说就能引出自己擅长的
         */
    }
}
