package javalanguage.interrupt;

/**
 *  给受阻塞进程抛出中断信号，使受阻线程退出阻塞（wait，sleep，join）
 *  就是中断线程等待，让线程提前结束等待，开始运行
 */

public class Interrupt {

    public static void main(String[] args) {

        Thread t1 = new Thread(){
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                try{
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }catch (Exception e) {
                    System.out.println("maybe wrong");
                }
                System.out.println(System.currentTimeMillis()-time);
                System.out.println(12221212);//会继续执行
            }
        };
        t1.start();
        t1.interrupt();


    }

}
