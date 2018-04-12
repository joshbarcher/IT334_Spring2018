package heaps;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinaryHeap<T extends Comparable<T>> implements IPriorityQueue<T>
{
    private T[] heap;
    private int nextIndex = 1;
    private int size = 0;

    public BinaryHeap()
    {
        heap = (T[]) new Comparable[10];
    }

    public BinaryHeap(T[] elements)
    {
        //make room for all elements
        heap = (T[]) new Comparable[elements.length + 1];

        for (int i = 0; i < elements.length; i++)
        {
            heap[i + 1] = elements[i];
        }

        size = elements.length;

        //build heap routine
        for (int i = size / 2; i >= 1; i--)
        {
            sink(i);
        }
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
        size++;
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
        if (isEmpty())
        {
            throw new NoSuchElementException("The heap is empty!");
        }

        //move the last element in the heap (size) to the first spot
        T nextElement = heap[1];
        swap(1, size);

        //remove the last elements
        heap[size] = null;

        //next index to add an element goes down
        nextIndex--;
        size--;

        //percolate down
        sink(1);

        return nextElement;
    }

    private void sink(int index)
    {
        //continue while we have a left child in the current node
        while (index <= size / 2)
        {
            //get appropriate child
            int left = 2 * index;
            int right = 2 * index + 1;

            int indexToCheck = left;

            //if we have a right child to choose from and the right smaller
            if (right <= size && heap[right].compareTo(heap[left]) < 0)
            {
                indexToCheck = right;
            }

            //if the child is smaller than swap
            if (heap[indexToCheck].compareTo(heap[index]) < 0)
            {
                swap(index, indexToCheck);
                index = indexToCheck;
            }
            else //otherwise the heap is finished, break
            {
                break;
            }
        }
    }

    @Override
    public T peek()
    {
        return heap[1];
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean isEmpty()
    {
        return size == 0;
    }

    @Override
    public boolean contains(T element)
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
