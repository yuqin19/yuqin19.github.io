package prj5;

public class Node<T> {
    T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getData() {
        return data;
    }
}