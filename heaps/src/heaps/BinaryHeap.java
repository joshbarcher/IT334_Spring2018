package heaps;

import java.util.Arrays;
import java.util.Iterator;

public class BinaryHeap<T extends Comparable<T>> implements IPriorityQueue<T>
{
    private T[] heap;
    private int nextIndex = 1;

    public BinaryHeap()
    {
        heap = (T[]) new Comparable[10];
    }

    @Override
    public void insert(T element)
    {
        //resize?
        if (nextIndex == heap.length)
        {
            resize();
        }

        //insert the new element
        heap[nextIndex] = element;
        swim(nextIndex);
        nextIndex++;
    }

    private void resize()
    {
        //create a new heap that is twice the size
        T[] oldHeap = heap;
        heap = (T[]) new Comparable[oldHeap.length * 2];

        //copy over my elements
        for (int i = 1; i < oldHeap.length; i++)
        {
            heap[i] = oldHeap[i];
        }
    }

    private void swim(int index)
    {
        while (index > 1)
        {
            //identify our parent
            int parentIndex = index / 2;

            //if the child is smaller than the parent, switch them
            if (heap[index].compareTo(heap[parentIndex]) < 0)
            {
                swap(index, parentIndex);

                //set the child index to the parent index
                index = parentIndex;
            }
            else
            {
                break;
            }
        }
    }

    private void swap(int first, int second)
    {
        T temp = heap[first];
        heap[first] = heap[second];
        heap[second] = temp;
    }

    @Override
    public T deleteMin()
    {
        return null;
    }

    @Override
    public T peek()
    {
        return null;
    }

    @Override
    public boolean contains(T element)
    {
        return false;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void clear()
    {

    }

    @Override
    public Iterator<T> iterator()
    {
        return null;
    }

    public String toString()
    {
        return "Heap elements: " + Arrays.toString(heap);
    }
}
