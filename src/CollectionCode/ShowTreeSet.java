package CollectionCode;

import sun.nio.ch.DirectBuffer;

import java.util.HashMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class ShowTreeSet {

    /**
     *���ں��������ƽ������������ʵ�֣�
     */
    public static void showTrSet(){

        HashMap<Integer,Integer> map = new HashMap<>();
        TreeSet set = new TreeSet();

        /**
         * ��д����
         * ReentrantLock
         * ���ܻ������ֵ
         */
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        /**
         * �пӣ�add��������ԭ������
         */
        list.subList(0,1);


        ConcurrentHashMap<Integer,Integer> concurrentHashMap = new ConcurrentHashMap<> ();






    }
}
