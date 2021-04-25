package thread;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author Bryce
 * @date 2021/4/10
 */
public class AtomicIntegerArrayDemo {

    static AtomicIntegerArray arr = new AtomicIntegerArray(10);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for(int i = 0; i<10;i++) {
            threads[i] = new Thread(() -> {
                for(int k = 0;k<100000;k++) {
                    arr.getAndIncrement(k % arr.length());
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(arr);
    }
}
