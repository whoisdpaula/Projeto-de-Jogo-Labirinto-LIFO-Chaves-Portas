package br.mack.labirinto.model;

import br.mack.labirinto.util.Sorts;
import java.io.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ScoreBoard {
    private ScoreEntry[]entries;
    private int size;

    public ScoreBoard(int capacity) {
        entries = new ScoreEntry[capacity];
        size = 0;
    }
    public void add(ScoreEntry e) {
        if(size < entries.length){
            entries[size++]=e;

        }else{
            System.out.println("Ranking lotado");

        }
    }
    public void sortByScoreDescending() {
        Sorts.quickSort(entries,0,size - 1,(a,b)->Integer.compare(b.getScore(),a.getScore()));

    }
    public ScoreEntry[] top(int n) {
        int m = Math.min(n,size);
        ScoreEntry[] result = new ScoreEntry[m];
        for(int i = 0; i < m; i++){
            result[i] = entries[i];
        }
        return result;
    }
    public String toCsvString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            sb.append(entries[i].getPlayerName());
            sb.append(',');
            sb.append(entries[i].getScore());
            sb.append(',');

        }
        return sb.toString();
    }

    public void loadFromFile(String path){
        try(BufferedReader reader = new  BufferedReader(new FileReader(path))){
            String line;
            while((line = reader.readLine())!=null){
                String[]parts = line.split(",");
                if(parts.length==2){
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    add(new ScoreEntry(name,score));

                }

            }
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo"+e.getMessage());
        }
    }
    public void saveToFile(String path){
        try(PrintWriter writer = new PrintWriter(new FileWriter(path))){
            for(int i = 0; i < size; i++){
                ScoreEntry e = entries[i];
                writer.println(e.getPlayerName()+","+e.getScore());
            }
        }catch ( IOException e){
            System.out.println("Erro ao salvar o arquivo"+e.getMessage());
        }
    }

}


