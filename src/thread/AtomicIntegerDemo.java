package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Bryce
 * @date 2021/4/10
 */
public class AtomicIntegerDemo {

    static AtomicInteger x = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> new AtomicIntegerDemo().add()).start();
        new Thread(() -> new AtomicIntegerDemo().add()).start();
        Thread.sleep(2000);
        System.out.println(x);
    }

    private void add() {
        for(int i = 0; i<10000; i++) {
            x.incrementAndGet();
        }
    }
}
