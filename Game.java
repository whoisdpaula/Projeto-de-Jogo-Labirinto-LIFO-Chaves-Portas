package br.mack.labirinto.app;

import br.mack.labirinto.core.Board;
import br.mack.labirinto.core.Position;
import br.mack.labirinto.io.CLI;
import br.mack.labirinto.io.MapLoader;
import br.mack.labirinto.model.ScoreEntry;
import br.mack.labirinto.model.Inventory;
import br.mack.labirinto.model.ScoreBoard;

import java.util.Scanner;

public class Game {
    public void run(String[]args){
        String mapPath = null;
        long seed = System.currentTimeMillis();

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o seu nome de Jogador: ");
        String playerName = sc.nextLine().trim();
        if(playerName.isEmpty()){
            playerName = "Jogador";
        }

        for (String arg : args) {
            if (arg.startsWith("--map=")) {
                mapPath = arg.substring("--map=".length());

            }else if (arg.startsWith("--seed=")) {
                seed = Long.parseLong(arg.substring("--seed=".length()));


            }else if (mapPath == null){
                mapPath = arg;

            }
        }
        if (mapPath == null){
            System.out.println("Voce deve informar o mapa");
            System.exit(1);
        }
        try{
            Board board = MapLoader.load(mapPath);
            Inventory inventory = new Inventory(board.getKeycapacity());
            CLI cli = new CLI();
            Position playerPos = board.getStart();
            int score = 0;



            boolean running = true;
            while (running) {
                board.render(playerPos,inventory);
                System.out.println("Score: " + score);


                char cmd = cli.readCommand();
                Position newPos = null;

                switch (cmd) {
                    case 'W': newPos = playerPos.move(-1,0); break;
                    case 'S': newPos = playerPos.move(1,0); break;
                    case 'A' :  newPos = playerPos.move(0,-1); break;
                    case 'D' :  newPos = playerPos.move(0,1); break;
                    case 'Q' : running = false ; continue;
                    default:
                        System.out.println("Erro ao executar comando");
                        continue;

                }
                if (!board.inBounds(newPos)) {
                    System.out.println("Voce nao encontrado");
                    continue;
                }
                char cell = board.getCell(newPos);
                if(cell == '#'){
                    System.out.println("Bateu na parede");
                    continue;
                }
                score -=1;

                if(cell == '.'){
                    playerPos = newPos;

                }else if(cell == '$'){
                    int v = (int) ((newPos.row + newPos .col + seed )%41 )+ 25;
                    score+=v;
                    System.out.println("Encontrou tesouro"+v+"pontos");
                    board.setCell(newPos,'.');
                    playerPos =   newPos;

                }else if(cell == 'T'){
                    score -= 20;
                    System.out.println("caiu na armadilha");
                    board.setCell(newPos,'.');
                    playerPos =   newPos;
                }else if(Character.isLowerCase(cell)){
                    boolean ok = inventory.pushKey(cell);
                    if(ok){
                        System.out.println("encontrou a chave"+cell+"'");
                        board.setCell(newPos,'.');
                        playerPos =   newPos;
                    }else{
                        System.out.println("Inventario cheio!Não pode pegar chave.");

                    }


                }else if(cell == 'E'){
                    score+=100;
                    while(!inventory.isEmpty()){
                        inventory.popKey();
                        score +=5;
                    }
                    running = false ;

                }

                else if (Character.isUpperCase(cell)){
                    if(inventory.canOpenDoor(cell)){
                        char popped = inventory.popKey();
                        System.out.println("Voce abriu a porta" + cell + "com a chave" + Character.toUpperCase(popped) + "+15 pontos");
                        board.setCell(newPos,'.');
                        playerPos =   newPos;
                        score +=15;
                    }else{
                        System.out.println("Voce nao pode abrir a porta"+ cell  + "chave no topo diferente");
                    }
                }
                else{
                    playerPos =   newPos;
                    }

                }

                System.out.println("\n------------------------------");
                System.out.println("\uD83C\uDF89VOCE CHEGOU NA SAIDA\uD83C\uDF89");
                System.out.println("\n------------------------------");
                System.out.println("\uD83C\uDFC6O JOGO ESTA ENCERRADO\uD83C\uDFC6");
                System.out.println("PONTUACAO FINAL:" + score );
                System.out.println("\n------------------------------");

                ScoreBoard sb = new ScoreBoard(100);
                sb.loadFromFile("ranking.csv");
                sb.add(new ScoreEntry(playerName,score));
                sb.sortByScoreDescending();
                sb.saveToFile("ranking.csv");

                ScoreEntry[] top = sb.top(10);

                System.out.println("\nRANKING DO TOP 10º JOGADORES:");
                System.out.println("\n------------------------------");
                for(int i = 0; i < top.length; i++){
                    ScoreEntry e = top[i];
                    System.out.printf("%2dº %-10s %5d pontos\n" , i + 1, e.getPlayerName(),e.getScore());
                    System.out.println("\n------------------------------");


                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
