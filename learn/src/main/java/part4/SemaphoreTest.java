package part4;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    private   int SumCount=0;

    Semaphore semaphore =new Semaphore(1);

    private void sum(int num) throws InterruptedException {
        semaphore.acquire();
        System.out.println("开始1");
        SumCount  =  fibo(num);
        System.out.println("开始2");
        semaphore.release();
    }

    private int getResult() throws InterruptedException {
        int result=0;
        System.out.println("开始3");

        semaphore.acquire();
        System.out.println("开始4");
        result= this.SumCount;
        semaphore.release();
        return result;
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        SemaphoreTest semaphoreTest = new SemaphoreTest();
        Thread thread = new Thread(() -> {
            try {
                semaphoreTest.sum(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread.sleep(2);
        int result = semaphoreTest.getResult(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
