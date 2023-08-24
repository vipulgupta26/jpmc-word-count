package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WordCountRunnable implements Runnable{
    private final String args;

    public WordCountRunnable(String args){
        this.args = args;
    }
    @Override
    public void run() {
        File file = new File(args);
        String line = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            ConcurrentMap<String,Integer> wordMap = new ConcurrentHashMap<>();
            while((line = br.readLine()) != null){
                String[] words = line.split(" ");
                for(String word : words){
                    wordMap.compute(word.toLowerCase(), (k,v) -> (v == null) ? 1 : v + 1);
                }

            }
            Gson gson = new Gson();
            String json = gson.toJson(wordMap);
            System.out.println(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if( br != null)
                try {
                    br.close();
                }catch(IOException e){e.printStackTrace();}
        }

    }


}
