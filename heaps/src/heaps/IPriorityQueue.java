package heaps;

import java.util.Iterator;

public interface IPriorityQueue<T extends Comparable<T>> extends Iterable<T>
{
    //ADT methods
    public void insert(T element);
    public T deleteMin();
    public T peek();

    //other methods
    public boolean contains(T element);
    public int size();
    public boolean isEmpty();
    public void clear();
    public Iterator<T> iterator();
}
