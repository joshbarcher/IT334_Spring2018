package heaps;

import java.util.Random;

public class TestHeap
{
    public static void main(String[] args)
    {
        IPriorityQueue<Integer> numbersHeap = new BinaryHeap<>();
        int[] numbers = {42, 16, 312, 4, -7};

        //insert the elements
        for (int number : numbers)
        {
            numbersHeap.insert(number);
        }

        System.out.println(numbersHeap);

        numbersHeap = new BinaryHeap<>();

        int[] elements = {9, 5, 2, 1, 3, 8, 9, 8, 4, 7};

        Random random = new Random();
        for (int i = 0; i < 10000; i++)
        {
            numbersHeap.insert(random.nextInt(10000));
        }

        while (!numbersHeap.isEmpty())
        {
            System.out.println(numbersHeap.deleteMin());
        }
    }
}
