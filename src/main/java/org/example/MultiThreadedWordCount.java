package org.example;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedWordCount {
    public static final int N_THREADS = 3;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        if(args.length < 1){
            System.out.println("Usage : WordCount <filename>");
            System.exit(0);
        }
        executor.execute(new WordCountRunnable(args[0]));
        executor.shutdown();
    }


}
