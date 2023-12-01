package linkedlist;

import java.util.List;

public interface ICustomLinkedList<T> {

    void add(T data);
    void remove(T data);
    List<T> getAll();
    boolean contains(T data);
}