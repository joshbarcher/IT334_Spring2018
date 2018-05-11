package divide_and_conquer;

import java.util.Arrays;
import java.util.Random;

public class QuickSort
{
    public static void main(String[] args)
    {
        int[] data = generateRandomArray(10000, 1, 10000);
        quickSort(data);
        System.out.println(Arrays.toString(data));
        System.out.println("Sorted? " + !detectAdjacentInversions(data));
    }

    //sorts our array
    public static void quickSort(int[] array)
    {
        quickSort(array, 0, array.length - 1);
    }

    //sorts a sub-array from low to high
    private static void quickSort(int[] array, int low, int high)
    {
        if (low >= high)
        {
            return;
        }

        //pick my pivot
        int pivot = array[low];

        //partition my data
        int i = low, j = high;
        while (i <= j)
        {
            //look for an element larger than the pivot
            while (array[i] < pivot)
            {
                i++;
            }

            //look for an element smaller than the pivot
            while (array[j] > pivot)
            {
                j--;
            }

            //if the pointers haven't "crossed" one another
            if (i <= j)
            {
                swap(array, i, j);

                //move the pointers forward
                i++;
                j--;
            }
        }

        //recursively sort the left and right sub-array
        if (low < j)
        {
            quickSort(array, low, j);
        }

        if (high > i)
        {
            quickSort(array, i, high);
        }
    }

    private static void swap(int[] array, int first, int second)
    {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static boolean detectAdjacentInversions(int[] array)
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            if (array[i] > array[i + 1])
            {
                return true;
            }
        }

        return false;
    }

    private static int[] generateRandomArray(int size, int low, int high)
    {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++)
        {
            array[i] = low + random.nextInt(high - low + 1);
        }

        return array;
    }
}
