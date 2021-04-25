package thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class KafkaStart {

    private static volatile boolean isStart = false;

    public synchronized void start() {
        if(isStart) {
            throw new RuntimeException();
        }
        System.out.println("启动");
        isStart = true;
    }

    public static void main(String[] args) {

        KafkaStart kafkaStart = new KafkaStart();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>(100));

        for(int i = 0; i<100; i++) {
            threadPoolExecutor.submit(kafkaStart::start);
        }

    }

}
