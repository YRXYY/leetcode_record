package javalanguage.ThreadDemos;

public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            System.out.println("*** 需要睡眠");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("被打扰了，很烦");
                return;
            }
            System.out.println("***睡眠完毕");
        });
        thread.start();
        Thread.sleep(1000);
        if(!thread.isInterrupted()){
            thread.interrupt();
        }
    }

}
