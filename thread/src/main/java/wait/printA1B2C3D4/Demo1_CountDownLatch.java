package wait.printA1B2C3D4;

import java.util.concurrent.CountDownLatch;

/**
 * @author dihua.wu
 * @description
 * @create 2020/12/9
 */
public class Demo1_CountDownLatch {

    /**
     * 输出：1A2B3C4D5E6F7G
     * 输出：A1B2C3D4E5F6G7
     * 如何一定让A在1前
     *
     * CountDownLatch
     *
     * @param args
     */

    public static void main(String[] args) {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Object lockObject = new Object();

        char[] number = "1234567".toCharArray();
        char[] letter = "ABCDEFG".toCharArray();

        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockObject) {
                for (char c : number) {
                    System.out.print(c);

                    try {
                        lockObject.notify();
                        lockObject.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockObject.notify();
//                lockObject.notifyAll();
            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (lockObject) {
                for (char c : letter) {
                    System.out.print(c);
                    countDownLatch.countDown(); //准备唤醒await()
                    try {
                        lockObject.notify();
                        lockObject.wait();//准备释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lockObject.notify();
//                lockObject.notifyAll();
            }
        }, "thread2").start();
    }
}
