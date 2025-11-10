package br.mack.labirinto.model;

public class ScoreEntry {
    private String playerName;
    private int score;
    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }
    public String getPlayerName() {
        return playerName;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int sc) {
        this.score = sc;
    }
    @Override
    public String toString() {
        return "(" + playerName + ", " + score + ")";
    }
}
