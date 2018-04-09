package heaps;

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
    }
}
