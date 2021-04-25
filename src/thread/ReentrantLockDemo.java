package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class ReentrantLockDemo {

    static int i = 0;

    public ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockDemo reentrantLockDemo = new ReentrantLockDemo();

        new Thread(reentrantLockDemo::add).start();
        new Thread(reentrantLockDemo::add).start();
        System.out.println(i);
        Thread.sleep(2000);
        System.out.println(i);
    }

    public void add() {
        try {
            lock.lock();
            for(int j = 0; j < 10000; j++) {
                i++;
            }
        } finally { //固定使用格式
            lock.unlock();
        }
    }


}
