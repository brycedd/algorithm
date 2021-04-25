package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        CallableImpl callable = new CallableImpl(countDownLatch);
        CallableImpl callable2 = new CallableImpl(countDownLatch);
        FutureTask futureTask = new FutureTask<CallableImpl>(callable);
        FutureTask futureTask2 = new FutureTask<CallableImpl>(callable2);
        new Thread(futureTask).start();//任务执行完后count -1
        new Thread(futureTask2).start();//任务执行完后count -1
        /*if(!futureTask.isDone()) {
            try {
                System.out.println("开始等待====");
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        System.out.println("====开始等待====");
        System.out.println("开始等待时的count： "+countDownLatch.getCount());
        countDownLatch.await();
        System.out.println("结束等待时的count： "+countDownLatch.getCount());

        System.out.println("task: "+futureTask.get());
        System.out.println("task2: "+futureTask2.get());
        System.out.println("====全部完成====");
    }
}
