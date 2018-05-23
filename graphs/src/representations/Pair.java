package representations;

import java.util.Objects;

public class Pair<K, V>
{
    private K first;
    private V second;

    public Pair()
    {
        //do nothing
    }

    public Pair(K first, V second)
    {
        this.first = first;
        this.second = second;
    }

    public K getFirst()
    {
        return first;
    }

    public V getSecond()
    {
        return second;
    }

    public void setFirst(K first)
    {
        this.first = first;
    }

    public void setSecond(V second)
    {
        this.second = second;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(first);
    }

    @Override
    public String toString()
    {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
