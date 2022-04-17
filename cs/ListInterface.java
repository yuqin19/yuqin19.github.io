package prj5;

import java.util.Iterator;

public interface ListInterface<T> {

    void add(T data);

    int size();

    void clear();

    T get(int index);

    Object[] toArray();

    boolean isEmpty();

    Node<T> remove(int index);

    boolean contains(T data);

    Iterator<T> iterator();

}
