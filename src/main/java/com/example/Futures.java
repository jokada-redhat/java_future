package com.example;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Futures {

    public static void main(String[] args) {
        // 1. ExecutorService の準備
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 2｡ 実行したい処理を作成
        Callable<String> test1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "test";
            }
        };

        // 3. ExecutorService に処理を依頼
        Future<String> future = executor.submit(test1);

        // 4. 終了を待機
        try {
            String result = future.get();
            System.out.println(result);
        } catch (CancellationException e) {
            System.err.println("Failed. 計算が取り消された");
        } catch (ExecutionException e) {
            System.err.println("Failed. 計算で例外がスローされた");
        } catch (InterruptedException e) {
            System.err.println("Failed. 待機中に現在のスレッドで割込みが発生した");
        }

        // 1. ExecutorService の終了
        executor.shutdown();
    }

}