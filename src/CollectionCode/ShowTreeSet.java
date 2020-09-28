package CollectionCode;

import sun.nio.ch.DirectBuffer;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ShowTreeSet {

    /**
     *基于红黑树（自平衡的排序二叉树实现）
     */
    public static void showTrSet(){

        HashMap<Integer,Integer> map = new HashMap<>();
        TreeSet set = new TreeSet();

        /**
         * 读写分离
         * ReentrantLock
         * 可能会读到旧值
         */
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        /**
         * 有坑：add方法是在原方法加
         */
        list.subList(0,1);


        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<> ();






    }
}
