package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class WordCount {

    public static void main(String[] args) {
        WordCount wc = new WordCount();
        if(args.length < 1){
            System.out.println("Usage : WordCount <filename>");
            System.exit(0);
        }
        File file = new File(args[0]);
        String line = null;
        BufferedReader br =null;
        try {
            br = new BufferedReader(new FileReader(file));
            Map<String,Integer> wordMap = new HashMap<>();
            while((line = br.readLine()) != null){
                String[] words = line.split(" ");
                for(String word : words){
                    if(wordMap.containsKey(word)){
                        wordMap.put(word.toLowerCase(), wordMap.get(word) + 1);
                    }else{
                        wordMap.put(word.toLowerCase(), 1);
                    }
                }

            }

            Gson gson = new Gson();
            String json = gson.toJson(wordMap);
            System.out.println(json);
        } catch (IOException  e) {
            throw new RuntimeException(e);
        }finally{
            if( br != null)
                try {
                    br.close();
                }catch(IOException e){e.printStackTrace();}
        }

    }

}