package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class CallableImpl implements Callable {

    private CountDownLatch latch;

    public CallableImpl(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "开始工作...");
        //doSomething
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "线程已完成工作，正在count-1...");
        latch.countDown();
        return Thread.currentThread().getName() + "工作全部完成";
    }
}
