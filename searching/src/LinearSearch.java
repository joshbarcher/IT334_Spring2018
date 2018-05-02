public class LinearSearch
{
    public static void main(String[] args)
    {
        int[] values = {-4, 0, 2, 16, 22, 40, 91, 118};

        //verify values in bs()
        for (int i = 0; i < values.length; i++)
        {
            System.out.println(values[i] + ": " + linearSearch(values, values[i]));
        }
        System.out.println();

        //verify bad values
        System.out.println("33: " + linearSearch(values, 33));
        System.out.println("-10: " + linearSearch(values, -10));
        System.out.println("200: " + linearSearch(values, 200));
        System.out.println();
    }

    public static int linearSearch(int[] values, int search)
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] == search)
            {
                return i;
            }
        }

        return -1;
    }

    public static int linearSearch(Object[] values, Object search)
    {
        for (int i = 0; i < values.length; i++)
        {
            if (values[i].equals(search))
            {
                return i;
            }
        }

        return -1;
    }
}
