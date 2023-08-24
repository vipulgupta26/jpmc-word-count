package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WordCountRunnable implements Runnable{
    public static final String FILE_NAME = "words.txt";
    @Override
    public void run() {
        File file = getFile(FILE_NAME);
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            ConcurrentMap<String,Integer> wordMap = new ConcurrentHashMap<>();
            while((line = br.readLine()) != null){
                String[] words = line.split(" ");
                for(String word : words){
                    wordMap.compute(word, (k,v) -> (v == null) ? 1 : v + 1);
                }

            }
            for(Map.Entry<String,Integer> entry : wordMap.entrySet()){
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if( br != null)
                try {
                    br.close();
                }catch(IOException e){e.printStackTrace();}
        }

    }

    public File getFile(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
