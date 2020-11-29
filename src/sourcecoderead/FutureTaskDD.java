package sourcecoderead;

/**
 * https://blog.csdn.net/qq_39654841/article/details/90631795?biz_id=102&utm_term=futuretask&utm_medium=distribute.pc_search_result.none-task-blog-2~all~sobaiduweb~default-0-90631795&spm=1018.2118.3001.4187
 */

/**
 * java中一般通过继承Thread类或者Runnable接口创建多线程。但这两种创建方式不能获得执行后的结果
 * 所以jdk1.5后提供了Callable和Future接口
 * Future代表异步计算的结果，是接口
 * FutureTask是Future的实现类，当中维护了一个Callable变量和state
 *      其state是volatile修饰，用来表示FutureTask内部的任务执行状态
 *      FutureTask有两个构造函数，一个是把传入的Callable保存起来，
 *     另一个是把Runnable变量+result转为Callable。即适配器模式
 *
 */
public class FutureTaskDD {

    //Callable
    //Runnable
    //Future
    //java.util.concurrent.FutureTaskDD

}
