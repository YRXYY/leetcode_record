package javalanguage.ThreadDemos;

/**
 * 多线程的启动永远只有一种方法:thread类中的start()方法
 * start()中的start0()就是利用JNI技术实现
 */

/**
 * 使用继承Thread类
 */
class Thread1 extends Thread{
    private String title;
    public Thread1(String title) {
        this.title = title;
    }

    /**
     * 多线程要执行的工作都在run中定义
     */
    @Override
    public void run() {
        for (int i = 0; i <10 ; i++) {
            System.out.println(i+title);
        }
    }
}

class Thread2 implements Runnable{
    @Override
    public void run() {
        System.out.println("hello world");
    }
}


public class Process1 {

    public static void main(String[] args) {
        new Thread1("hello").run();
        new Thread1("world").run();
        //A，B将顺序执行

        new Thread1("hello").start();
        new Thread1("world").start();
        //A，B将交替执行，而且不可控。


        //实现Runnable接口的启动方法，因为Runnable里只有run()方法，没有start()
        new Thread(new Thread2()).start();


        //函数式编程
        for(int j=0;j<3;j++){
            Runnable run1 = ()->{
                for (int i = 0; i <100 ; i++) {
                    System.out.println(i);
                }
            };
            new Thread(run1).start();
            //这样写也行
            new Thread(()->{
                System.out.println(1);
            }).start();
        }




    }

}
