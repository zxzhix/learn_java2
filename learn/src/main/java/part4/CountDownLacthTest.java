package part4;

import java.util.concurrent.CountDownLatch;

public class CountDownLacthTest {

    private int SumCount=0;

    private void  sum(int num) throws InterruptedException {

        SumCount= fibo(num);
        System.out.println(1);
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    private int getResult() {
        int result= this.SumCount;
        System.out.println(2);
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CountDownLacthTest countDownLacthTest = new CountDownLacthTest();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //使用CountDownLatch ，要保证先调用子线程
        Thread thread = new Thread(() -> {
            try {
                countDownLacthTest.sum(30);
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //待子线程执行完毕后，再执行主线程
        countDownLatch.await();
        int result = countDownLacthTest.getResult(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
