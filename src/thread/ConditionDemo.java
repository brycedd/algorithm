package thread;

import java.math.BigDecimal;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bryce
 * @date 2021/4/10
 */
public class ConditionDemo {

    static Lock lock = new ReentrantLock();
    static Condition add = lock.newCondition();
    static Condition  subtract = lock.newCondition();
    private static int i = 0;

    public static void main(String[] args) {
        new Thread(ConditionDemo::producer).start();
        new Thread(ConditionDemo::consumer).start();
    }

    public static void producer() {
        lock.lock();
        try {
            for(;;){
                i++;
                System.out.println("增加后库存：" + i);
                if(i>=30) {
                    System.out.println("等待中...");
                    Thread.sleep(1000);
                    add.await();
                    subtract.signal();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void consumer() {
        lock.lock();
        try {
            for(;;){
                i--;
                System.out.println("减少后库存：" + i);
                if(i<=0) {
                    System.out.println("唤醒生产者中...");
                    Thread.sleep(1000);
                    add.signal();
                    subtract.await();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
