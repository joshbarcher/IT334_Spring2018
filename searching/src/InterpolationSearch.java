public class InterpolationSearch
{
    public static void main(String[] args)
    {
        int[] values = {-4, 0, 2, 16, 22, 40, 91, 118};

        //verify values in bs()
        for (int i = 0; i < values.length; i++)
        {
            System.out.println(values[i] + ": " + interpolationSearch(values, values[i]));
        }
        System.out.println();

        //verify bad values
        System.out.println("33: " + interpolationSearch(values, 33));
        System.out.println("-10: " + interpolationSearch(values, -10));
        System.out.println("200: " + interpolationSearch(values, 200));
        System.out.println();
    }

    public static int interpolationSearch(int[] values, int search)
    {
        int low = 0;
        int high = values.length - 1;

        while (values[low] <= search && values[high] >= search)
        {
            //interpolation search
            //(assumes the values in the array increase linearly)
            int mid = low + ((search - values[low]) *
                    (high - low)) / (values[high] - values[low]);

            if (values[mid] == search)
            {
                return mid;
            }
            else if (values[mid] < search)
            {
                //look to the right
                low = mid + 1;
            }
            else //if (values[mid] > search)
            {
                //look to the left
                high = mid - 1;
            }
        }

        if (values[low] == search)
        {
            return low;
        }
        else
        {
            return -1;
        }
    }
}
