package javalanguage.ThreadLocal;

public class ThreadLocalDemo {
    public static void main(String[] args) {

    }
}

class Thread1 implements Runnable {

    //摘自源码，其内部维护了一个ThreadLocalMap变量
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