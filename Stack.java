package br.mack.labirinto.ds;

public class Stack<T> {
    private final T[] data;
    private int top;

    @SuppressWarnings("unchecked")// olhar esse nome que esta em ingles
    public Stack(int capacity) { // olhar esse nome que esta em ingles
        data = (T[]) new Object[capacity];// olhar esse nome que esta em ingles
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
            throw new IllegalStateException("Stack is full");// olhar esse nome que esta em ingles
        }
        data[++top] = item;
    }
    public T pop() {
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");// olhar esse nome que esta em ingles
        }
        T val = data[top];
        data[top] = null;
        top --;
        return val;// olhar esse nome que esta em ingles
    }
    public T peek() {// olhar esse nome que esta em ingles
        if(isEmpty()) {
            throw new IllegalStateException("Stack is empty");// olhar esse nome que esta em ingles
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

