package br.mack.labirinto.io;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.core.Position;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class MapLoader {
    public static Board load(String mapPath) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(mapPath));
        int L = 0, C = 0, CAP = 0;
        char[][] grid = null;
        Position start = null;
        Position exit = null;
        int rowIdx = 0;

        for (String line : lines) {
            line = line.trim();
            if(line.isEmpty()||line.startsWith(";")|| line.toLowerCase().contains("labirinto")){
                continue;
            }
            if(grid==null){
                String[]parts = line.split("\\s+");
                if(parts.length<3){
                    throw new IllegalArgumentException("Cabecalho invalido");
                }
                L = Integer.parseInt(parts[0].trim());
                C = Integer.parseInt(parts[1].trim());
                CAP = Integer.parseInt(parts[2].trim());
                grid = new char[L][C];
            }else{
                if(rowIdx>= L) break;
                if (line.length()!=C){
                    throw new IllegalArgumentException("Linha de mapa com largura errada"+line+"'");

                }
                for(int j=0;j<C;j++){
                    char ch = line.charAt(j);
                    grid[rowIdx][j] = ch;


                    if(ch=='S'){
                        start = new Position(rowIdx,j);
                        grid[rowIdx][j] = '.';

                    }else if(ch=='E'){
                        exit = new Position(rowIdx,j);
                    }
                }
                rowIdx++;
            }
        }
        if(grid == null|| start == null || exit == null ){
            throw new IllegalArgumentException("mapa nao tem inicio ou saida correta");
        }
        return new Board(grid,L,C,CAP,start,exit);
    }
}
