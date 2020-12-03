package javalanguage.ThreadDemos;

public class ThreadPriority {
    public static void main(String[] args) {
        Runnable run = ()->{
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+" 执行。");
            }
        };
        Thread threadA = new Thread(run,"ThreadA");
        Thread threadB = new Thread(run,"ThreadB");
        Thread threadC = new Thread(run,"ThreadC");

        threadA.setPriority(Thread.MAX_PRIORITY);
        threadB.setPriority(Thread.MIN_PRIORITY);

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
