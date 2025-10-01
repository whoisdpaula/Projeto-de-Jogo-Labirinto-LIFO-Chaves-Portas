package br.mack.labirinto.ds;

public class Stack<T> {
    private final T[] data;
    private int top;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) { 
        data = (T[]) new Object[capacity];
        top = -1;
    }
    public boolean isEmpty() {
        return top == - 1;
    }
    public boolean isFull() {
        return top == data.length - 1;
    }
    public void push(T item) {
        if(isFull()) {
            throw new IllegalStateException("Esta cheio");
        }
        data[++top] = item;
    }
    public T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("Esta vazio");
        }
        T val = data[top];
        data[top] = null;
        top --;
        return val;
    }
    public T peek() {
        if(isEmpty()) {
            throw new IllegalStateException("Esta vazio");
        }
        return data[top];
    }
    public int size() {
        return top + 1;
    }
    public int capacity() {
        return data.length;
    }

}


