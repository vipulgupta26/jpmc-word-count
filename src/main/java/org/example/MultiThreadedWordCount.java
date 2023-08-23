package org.example;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedWordCount {
    public static final int N_THREADS = 3;
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(N_THREADS);
        executor.execute(new WordCountRunnable());
        executor.shutdown();
    }


}
