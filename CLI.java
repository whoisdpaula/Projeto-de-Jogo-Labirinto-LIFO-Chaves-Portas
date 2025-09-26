package br.mack.labirinto.io;
import java.util.Scanner;

public class CLI {
    private Scanner scanner = new Scanner(System.in);
    public char readCommand(){
        System.out.println("MOVIMENTOS: W (⬆) A (⬅) S (⬇) D (➡) ");
        String line = scanner.nextLine().trim().toUpperCase();
        if(line.isEmpty())return ' ';
        char c = line.charAt(0);
        return c;
    }
}
