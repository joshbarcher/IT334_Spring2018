package bst;

public class KeyValuePair<K extends Comparable<K>, V>
        implements Comparable<KeyValuePair<K, V>>
{
    private K key;
    private V value;

    public KeyValuePair(K key, V value)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(KeyValuePair<K, V> other)
    {
        return key.compareTo(other.key);
    }

    public K getKey()
    {
        return key;
    }

    public void setKey(K key)
    {
        this.key = key;
    }

    public V getValue()
    {
        return value;
    }

    public void setValue(V value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return "KeyValuePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
