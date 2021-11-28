package part4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private int SumCount=0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void  sum(int num)
    {
        lock.lock();
        SumCount= fibo(num);
        condition.signal();
        lock.unlock();

    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    private int getResult() {
        int result=0;
        lock.lock();
        if(SumCount==0)
        {
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result= this.SumCount;
        lock.unlock();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        LockTest lockTest = new LockTest();
        Thread thread = new Thread(() -> {
            lockTest.sum(30);
        });
        thread.start();

        int result = lockTest.getResult(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
