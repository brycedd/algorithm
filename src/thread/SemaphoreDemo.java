package thread;

import java.util.concurrent.Semaphore;

/**
 * @author Bryce
 * @date 2021/4/9
 */
public class SemaphoreDemo {


    //限流为5
    private static final Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for(int i = 0; i<20;i++) {
            final int j = i;
            new Thread(() -> {
                try {
                    action(j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

    public static void action(int i) throws InterruptedException {
        semaphore.acquire();//许可
        System.out.println(i + "秒杀");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(i + "秒杀成功");
        semaphore.release();//释放许可

    }


}
