package br.mack.labirinto.model;

import br.mack.labirinto.ds.Stack;

public class Inventory {
    private Stack<Character> keyStack;
    public Inventory(int capacity) {
        keyStack = new Stack<>(capacity);
    }
    public boolean pushKey(char key){
        if(keyStack.isFull()){
            return false;
        }
        keyStack.push(key);
        return true;
    }
    public boolean canOpenDoor(char doorChar){
        if(keyStack.isEmpty()) return false;
        char top = keyStack.peek();
        return (Character.toUpperCase(top) == doorChar);
    }
    public char popKey(){
        return keyStack.pop();
    }
    public boolean isEmpty(){
        return keyStack.isEmpty();
    }
    public int size(){
        return keyStack.size();
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Stack<Character>temp = new Stack<>(keyStack.capacity());
        while(!keyStack.isEmpty()){
            char k = keyStack.pop();
            sb.append(k);
            temp.push(k);

        }
        return sb.toString()+"(total:"+keyStack.size()+")";
    }
}
