package javalanguage.ThreadLocal;

public class ThreadLocalDemo {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>() {

    };


    public static void main(String[] args) {
        new ThreadLocalDemo().test1();
    }

    void test1() {
        new Thread(new MyIntegerTask("dijia")).start();
        new Thread(new MyIntegerTask("sailuo")).start();
        new Thread(new MyStringTask("cook")).start();
        new Thread(new MyStringTask("water")).start();
    }

    class MyIntegerTask implements Runnable{
        private String name;

        public MyIntegerTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                if (null == ThreadLocalDemo.threadLocal.get()) {
                    ThreadLocalDemo.threadLocal.set(100);
                    System.out.println("Integer " + name + ThreadLocalDemo.threadLocal.get());
                } else {
                    Integer res = (Integer) ThreadLocalDemo.threadLocal.get();
                    ThreadLocalDemo.threadLocal.set(res+200);
                    System.out.println("Integer " + name + ThreadLocalDemo.threadLocal.get());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class MyStringTask implements Runnable {
        private String name;

        public MyStringTask(String name){
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                if (null == ThreadLocalDemo.threadLocal.get()) {
                    ThreadLocalDemo.threadLocal.set("asd");
                    System.out.println("String " + name + ThreadLocalDemo.threadLocal.get());
                } else {
                    String res = (String)ThreadLocalDemo.threadLocal.get();
                    ThreadLocalDemo.threadLocal.set(res+"qwe");
                    System.out.println("String " + name + ThreadLocalDemo.threadLocal.get());
                }
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}



