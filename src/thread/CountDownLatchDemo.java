package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class CountDownLatchDemo {

    //CountDownLatch会在等待指定事件后执行
    private static final int threadCount = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        System.out.println("threadCount-before: "+countDownLatch.getCount());
        for(int i = 0; i<threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();//对传入的count做-1操作
                }
            });
        }
        countDownLatch.await(120, TimeUnit.MILLISECONDS);
        System.out.println("结束");
        System.out.println("threadCount-after: "+countDownLatch.getCount());
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(50);
        System.out.println(threadNum);
        Thread.sleep(50);
    }
}
