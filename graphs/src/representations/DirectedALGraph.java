package representations;

import java.util.NoSuchElementException;

public class DirectedALGraph<T>
{
    private static final double LOAD_FACTOR = 0.6;
    private Node<T>[] adjacencyList;
    private int vertexSize;
    private int edgeSize;

    public DirectedALGraph()
    {
        this.adjacencyList = (Node<T>[]) new Node[10];
    }

    //will add a vertex if not already present and return true if added
    public boolean addVertex(T vertex)
    {
        if ((vertexSize * 1.0) / adjacencyList.length > LOAD_FACTOR)
        {
            rehash();
        }

        int index = getPosition(vertex);
        if (adjacencyList[index] == null)
        {
            adjacencyList[index] = new Node<>(vertex, null);
            vertexSize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    private int getPosition(T vertex)
    {
        int hash = vertex.hashCode();
        int index = hash % adjacencyList.length;

        //find an empty spot or the vertex
        while (adjacencyList[index] != null &&
                !adjacencyList[index].data.equals(vertex))
        {
            index = (index + 1) % adjacencyList.length;
        }

        return index;
    }

    //add a directed edge (from, to) to the graph, returning true if the edge is
    //not already present
    public boolean addEdge(T from, T to)
    {
        //do both vertices exist
        int fromPos = getPosition(from);
        int toPos = getPosition(to);

        if (adjacencyList[fromPos] == null || adjacencyList[toPos] == null)
        {
            throw new NoSuchElementException("One of your vertices is missing...");
        }

        //if the edge is not already present, add the edge
        Node<T> current = adjacencyList[fromPos];
        current = findEdgeTo(current, to);

        //edge not present
        if (current.next == null)
        {
            current.next = new Node<>(to, null);
            edgeSize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    //returns the node before the position where "to" should be located
    private Node<T> findEdgeTo(Node<T> current, T to)
    {
        while (current.next != null)
        {
            //does the next node contain "to"
            if (current.next.data.equals(to))
            {
                return current;
            }

            //move to the next node
            current = current.next;
        }

        //the edge is not present
        return current;
    }

    private void rehash()
    {
        //implement at a later point...
    }

    public int vertexSize()
    {
        return vertexSize;
    }

    public int edgeSize()
    {
        return edgeSize;
    }

    private class Node<T>
    {
        private T data;
        private Node<T> next;

        public Node(T data, Node<T> next)
        {
            this.data = data;
            this.next = next;
        }
    }
}
