package bst;

import interfaces.ICollection;
import interfaces.IMap;
import interfaces.ISet;

import java.util.Iterator;

public class TreeMap<K extends Comparable<K>, V> implements IMap<K, V>
{
    //store a tree internally to make this symbol table work
    private BinarySearchTree<KeyValuePair<K, V>> tree;

    public TreeMap()
    {
        tree = new BinarySearchTree<>();
    }

    @Override
    public void add(K key, V value)
    {
        tree.add(new KeyValuePair<>(key, value));
    }

    @Override
    public V get(K key)
    {
        KeyValuePair<K, V> result = tree.get(new KeyValuePair<>(key, null));

        return result.getValue();
    }

    @Override
    public boolean keyExists(K key)
    {
        return false;
    }

    @Override
    public boolean valueExists(V value)
    {
        return false;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void clear()
    {

    }

    @Override
    public Iterator<KeyValuePair<K, V>> iterator()
    {
        return null;
    }

    @Override
    public ISet<K> keyset()
    {
        return null;
    }

    @Override
    public ICollection<V> values()
    {
        return null;
    }
}
