package br.mack.labirinto.core;

public class RNG {
    private long seed;
    public RNG(long seed) {
        this.seed = seed;
    }
    public int nextInt(int bound) {
        seed = (seed * 6364136223846793005L + 1)&0xFFFFFFFFL;
        int value = (int) (Math.abs(seed) % bound);
        return value;
    }
}
