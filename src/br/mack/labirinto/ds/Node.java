package br.mack.labirinto.ds;

public class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
    }
}
