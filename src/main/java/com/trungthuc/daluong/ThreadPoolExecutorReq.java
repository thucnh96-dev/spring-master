package com.trungthuc.daluong;

import lombok.SneakyThrows;

import java.util.concurrent.*;

public class ThreadPoolExecutorReq {

    @SneakyThrows
    public static void main(String[] args) {

         int poolSize =5;       // số thread sẽ sử dụng
         int maxPoolSize = 10;   // số lượng pool tối đa
         int maxsizeQueue = 200; // số lượng hàng đợi
         int threadAlive = 10;   // thời gian sống của 1 thread nếu không làm gì

        // ví dụ: Thread cài đặt xử lý threadPoolExecutor
      //  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(poolSize,maxPoolSize, threadAlive,TimeUnit.SECONDS,new ArrayBlockingQueue<>(maxsizeQueue));

       // ví dụ : sử dụn ExecutorService . sử dụng 5 Thread chia nhau công việc.
        ExecutorService executorService =  Executors.newFixedThreadPool(poolSize);

        for ( int i =0 ; i < 1000 ; i++ ){
         //  threadPoolExecutor.execute( new RequestHandler("REQUEST () -> :"+i));
            executorService.execute( new RequestHandler("REQUEST () -> :"+i));
           Thread.sleep(50);
       }
        executorService.shutdown(); // trong quá trình xử lý không nhận thêm nhiệm vụ
       while (!executorService.isTerminated()){
               // chờ xử lý số request bên trong.

       }

    }

    int cacuLater(int var){
        int sum = 0;
        int run = 2;
        while ( var > run ) {
             while ( var % run == 0 ){
                 sum += run;
                 var /=run;
             }
            run ++;
        }
        return sum;
    }


    boolean isPrime(int n) {

        Boolean result = null;

        if (n <= 1) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0){
                result = false;
                break;
            }
            else {
                result = true;
            }
        }
      return result;

    }
}
