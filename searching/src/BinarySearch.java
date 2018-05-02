public class BinarySearch
{
    public static void main(String[] args)
    {
        int[] values = {-4, 0, 2, 16, 22, 40, 91, 118};

        //verify values in bs()
        for (int i = 0; i < values.length; i++)
        {
            System.out.println(values[i] + ": " + binarySearch(values, values[i]));
        }
        System.out.println();

        //verify bad values
        System.out.println("33: " + binarySearch(values, 33));
        System.out.println("-10: " + binarySearch(values, -10));
        System.out.println("200: " + binarySearch(values, 200));
        System.out.println();
    }

    public static int binarySearch(int[] values, int search)
    {
        int low = 0;
        int high = values.length - 1;

        while (low <= high)
        {
            int mid = (high + low) / 2;

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

        return -1;
    }

    public static int binarySearch(Object[] values, Object search)
    {
        int low = 0;
        int high = values.length - 1;

        while (low <= high)
        {
            int mid = (high + low) / 2;
            int difference = ((Comparable)values[mid]).compareTo(search);

            if (difference == 0)
            {
                return mid;
            }
            else if (difference < 0)
            {
                //look to the right
                low = mid + 1;
            }
            else //if (difference > 0)
            {
                //look to the left
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int recBinarySearch(Object[] values, Object search)
    {
        return recBinarySearch(values, search, 0, values.length);
    }

    private static int recBinarySearch(Object[] values, Object search, int low, int high)
    {
        //base case, low passed high
        if (low > high)
        {
            return -1; //not found
        }

        int mid = (high + low) / 2;
        int difference = ((Comparable)values[mid]).compareTo(search);

        if (difference == 0)
        {
            return mid;
        }
        else if (difference < 0)
        {
            //look to the right
            return recBinarySearch(values, search, mid + 1, high);
        }
        else //if (difference > 0)
        {
            //look to the left
            return recBinarySearch(values, search, low, mid - 1);
        }
    }
}
