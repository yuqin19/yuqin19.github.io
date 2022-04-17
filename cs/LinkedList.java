package prj5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ListInterface<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of range.");
        }

        Node<T> tempNode = head;
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.next;
        }
        return tempNode.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can not be null.");
        } else {
            Node<T> node = new Node<T>(data);
            if (head == null) {
                head = node;
            } else {
                getTailNode().next = node;
            }
            size++;
        }
    }

    private Node<T> getTailNode() {
        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    public Node<T> remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Out of range.");
        }

        Node<T> node = head;
        Node<T> deleteNode = null;
        if (index == 0) {
            deleteNode = head;
            head = head.next;
        } else {
            for (int i = 0; i < index - 1; i++) {
                node = node.next;
            }
            deleteNode = node.next;
            node.next = deleteNode.next;
        }
        size--;
        return deleteNode;
    }

    public boolean contains(T data) {
        if (isEmpty()) {
            return false;
        }

        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> tmpNode = head;
        int index = 0;
        while (tmpNode != null) {
            array[index++] = tmpNode.data;
            tmpNode = tmpNode.next;
        }
        return array;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> nextNode;
        private int index;

        public LinkedListIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (index == 0) {
                nextNode = head;
            } else {
                nextNode = nextNode.next;
            }
            index++;
            return nextNode.data;
        }

        public void remove() {
            if (LinkedList.this.size() == 0) {
                throw new IllegalStateException();
            }

            if (index == 0) {
                LinkedList.this.remove(0);
                return;
            }
            LinkedList.this.remove(index - 1);
            index--;

        }
    }

}
