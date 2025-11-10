package br.mack.labirinto;

import br.mack.labirinto.app.Game;
import java.io.File;

public class Main2 {
    public static void main(String[] args) {
        String mapPath = "maps/map2.txt";
        File file = new File(mapPath);
        if (!file.exists()) {
            System.out.println("Arquivo não encontrado: " + mapPath);
            return;
        }
        System.out.println("Mapa encontrado: " + mapPath);
        Game game = new Game();
        game.run(new String[]{"--map=" + mapPath});
    }
}
