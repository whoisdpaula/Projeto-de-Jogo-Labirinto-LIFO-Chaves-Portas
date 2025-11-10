package br.mack.labirinto.ds;

public class SinglyLinkedList <T>{
    private Node<T> head;

    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null){
            head = newNode;
        }else{
            Node<T> cur = head;
            while (cur.next != null){
                cur = cur.next;
            }
            cur.next = newNode;
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<T> cur = head;
        while (cur != null){
            sb.append(cur.data);
            sb.append(System.lineSeparator());
            cur = cur.next;
        }
        return sb.toString();
    }
}
