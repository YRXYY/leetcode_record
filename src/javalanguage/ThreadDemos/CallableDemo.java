package javalanguage.ThreadDemos;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 弥补Runnable无返回值的缺点
 */
public class CallableDemo implements Callable<String> {
    @Override
    public String call() {
        return "This is a CallableDemo.";
    }


    public static void main(String[] args) throws Exception{
        //用FutureTask封装Callable
        FutureTask<String> task = new FutureTask<>(new CallableDemo());
        //再传给Thread执行
        new Thread(task).start();
        //need to throw the Exception
        System.out.println(task.get());
    }
}
