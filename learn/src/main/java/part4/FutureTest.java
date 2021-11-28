package part4;

import java.util.concurrent.*;

public class FutureTest implements Callable<Integer> {
    private int SumCount=0;
    private int CalNum=0;

    private void setNum(int tnum)
    {
        CalNum=tnum;
    }
    private int  sum(int num)
    {
        return  fibo(num);
    }

    private int fibo(int a) {
        if ( a < 2) {
            return 1;
        }
        return fibo(a-1) + fibo(a-2);
    }

    @Override
    public Integer call() throws Exception {
        return sum(CalNum);
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        FutureTest futureTest = new FutureTest();
        futureTest.setNum(30);
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future =  executorService.submit(futureTest);

        int result = future.get(); //这是得到的返回值
        future.isDone();

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }


}
