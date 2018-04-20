package other_sorts;

import java.util.Arrays;
import java.util.Random;

public class ShellSort
{
    public static void main(String[] args)
    {
        //generate a random array and sort it
        int[] array = generateRandomArray(1000, 1, 1000);
        System.out.println(Arrays.toString(array));
        shellSort(array);

        //print the results and verify it is sorted
        System.out.println(Arrays.toString(array));
        System.out.println("Sorted? " + !detectInversions(array));
    }

    private static void shellSort(int[] the_array)
    {
        //delta is the gap for each iteration of insertion sort on a sub-array
        for (int delta = the_array.length / 2; delta > 0; delta /= 2)
        {
            //perform insertion sort on this sub-array
            for (int j = delta; j < the_array.length; j++)
            {
                for (int k = j; k >= delta; k -= delta)
                {
                    if (the_array[k] < the_array[k - delta])
                    {
                        swap(the_array, k, k - delta);
                    }
                    else
                    {
                        break;
                    }
                }
            }
        }
    }

    private static void swap(int[] array, int first, int second)
    {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private static boolean detectInversions(int[] array)
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
