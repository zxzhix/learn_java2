package part4;

public class JoinTest {

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
        return result;Condition
    }

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        JoinTest joinTest = new JoinTest();

        Thread thread = new Thread(() -> {
            try {
                joinTest.sum(30);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        //先在主线程中加入子线程，待子线程执行完毕后再继续执行主线程下面的逻辑
        thread.join();
        int result = joinTest.getResult(); //这是得到的返回值

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

        // 然后退出main线程
    }
}
