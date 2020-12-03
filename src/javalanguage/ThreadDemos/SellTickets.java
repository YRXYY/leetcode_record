package javalanguage.ThreadDemos;

public class SellTickets implements Runnable{
    private int ticket = 100;

    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            if(ticket>0){
                try {
                    Thread.sleep(100);  //网络延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
                System.out.println(Thread.currentThread().getName()+" sell one,and left "+ticket);
            }
        }
    }

    public static void main(String[] args) {
        SellTickets s1 = new SellTickets();
        new Thread(s1,"A").start();
        //new Thread(s1).start();
        new Thread(s1,"B").start();
        //new Thread(s1).start();
        new Thread(s1,"C").start();
        //new Thread(s1).start();
    }

    /**
     * 生动解释，n个线程访问同一个资源
     * 此例中，s1是资源，三个Thread帮助其卖票
     *
     * 这就是一个代理模式，一个个new Thread就是代理s1执行
     * 用户自定义的线程主题类只是负责项目核心功能的实现，辅助实现交由Thread
     */

}
