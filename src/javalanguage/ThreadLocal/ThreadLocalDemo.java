package javalanguage.ThreadLocal;

public class ThreadLocalDemo {
    public static void main(String[] args) {

    }
}

class Thread1 implements Runnable {

    //ժ��Դ�룬���ڲ�ά����һ��ThreadLocalMap����
    //ThreadLocal.ThreadLocalMap threadLocals = null;

    @Override
    public void run() {

    }
}

class ThreadLocal<T>{
//    public void set(T value) {
//        Thread t = Thread.currentThread();
//        ThreadLocalMap map = getMap(t);
//        if (map != null) {
//            map.set(this, value);
//        } else {
//            createMap(t, value);
//        }
//    }
}