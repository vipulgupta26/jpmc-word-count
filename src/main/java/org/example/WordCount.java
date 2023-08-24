package org.example;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class WordCount {
    public static final String FILE_NAME = "words.txt";
    public static void main(String[] args) {
        WordCount wc = new WordCount();
        File file = wc.getFile(FILE_NAME);
        String line = null;
        BufferedReader br =null;
        try {
            br = new BufferedReader(new FileReader(file));
            Map<String,Integer> wordMap = new HashMap<>();
            while((line = br.readLine()) != null){
                String[] words = line.split(" ");
                for(String word : words){
                    if(wordMap.containsKey(word)){
                        wordMap.put(word, wordMap.get(word) + 1);
                    }else{
                        wordMap.put(word, 1);
                    }
                }

            }
            for(Map.Entry<String,Integer> entry : wordMap.entrySet()){
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        } catch (IOException  e) {
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