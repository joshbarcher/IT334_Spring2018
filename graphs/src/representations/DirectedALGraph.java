package representations;

import java.util.*;

public class DirectedALGraph<T>
{
    private static final double LOAD_FACTOR = 0.6;
    private Node<T>[] adjacencyList;
    private int vertexSize;
    private int edgeSize;

    //Pair(in-degree, out-degree)
    private Map<T, Pair<Integer, Integer>> degrees = new HashMap<>();

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

    public void addVertices(T... vertices)
    {
        for (T vertex : vertices)
        {
            addVertex(vertex);
        }
    }

    public void addEdges(Pair<T, T>... edges)
    {
        for (Pair<T, T> edge : edges)
        {
            addEdge(edge.getFirst(), edge.getSecond());
        }
    }

    //returns the position of the vertex where it should be (it may
    //not be present)
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

    public boolean addUndirectedEdge(T first, T second)
    {
        return addUndirectedEdge(first, second, 0);
    }

    public boolean addUndirectedEdge(T first, T second, int weight)
    {
        return addEdge(first, second, weight) && addEdge(second, first, weight);
    }

    public boolean addEdge(T from, T to)
    {
        return addEdge(from, to, 0);
    }

    //add a directed edge (from, to) to the graph, returning true if the edge is
    //not already present
    public boolean addEdge(T from, T to, int weight)
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
            //adding the edge!
            current.next = new Node<>(to, null, weight);
            incrementDegrees(from, to);
            edgeSize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    private void incrementDegrees(T from, T to)
    {
        //make sure that from exists in the map
        ensureDegreeExists(from);
        ensureDegreeExists(to);

        //update the in-degree of to
        Pair<Integer, Integer> toDegree = degrees.get(to);
        toDegree.setFirst(toDegree.getFirst() + 1);

        //update the out-degree of from
        Pair<Integer, Integer> fromDegree = degrees.get(from);
        fromDegree.setSecond(fromDegree.getSecond() + 1);
    }

    private void ensureDegreeExists(T vertex)
    {
        if (!degrees.containsKey(vertex))
        {
            degrees.put(vertex, new Pair<>(0, 0));
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

    public boolean hasVertex(T vertex)
    {
        return adjacencyList[getPosition(vertex)] != null;
    }

    public boolean hasEdge(T from, T to)
    {
        int position = getPosition(from);
        Node<T> current = adjacencyList[position];

        //make sure the from vertex is in the graph
        if (current == null)
        {
            return false;
        }
        else
        {
            return findEdgeTo(current, to).next != null;
        }
    }

    public int inDegree(T vertex)
    {
        if (!hasVertex(vertex))
        {
            return -1;
        }

        return degrees.containsKey(vertex) ? degrees.get(vertex).getFirst() : 0;
    }

    public int outDegree(T vertex)
    {
        if (!hasVertex(vertex))
        {
            return -1;
        }

        return degrees.containsKey(vertex) ? degrees.get(vertex).getSecond() : 0;
    }

    public List<T> dfs(T start)
    {
        Set<T> seen = new HashSet<>();
        List<T> traversal = new ArrayList<>();

        //find the first vertex
        if (adjacencyList[getPosition(start)] != null)
        {
            //perform the dfs search
            dfs(start, seen, traversal);
        }

        return traversal;
    }

    private void dfs(T current, Set<T> seen, List<T> traversal)
    {
        //don't continue if we visited this node
        if (!seen.contains(current))
        {
            seen.add(current);
            traversal.add(current);

            //look at incident edges
            Node<T> currentNode = adjacencyList[getPosition(current)];
            while (currentNode.next != null)
            {
                //there is an edge here, search in this direction
                dfs(currentNode.next.data, seen, traversal);
                currentNode = currentNode.next;
            }
        }
    }

    //returns a map of vertices to distance values, all of which represent
    //the shortest path from source to the vertex
    public Map<T, Integer> dijkstras(T source)
    {
        if (!hasVertex(source))
        {
            throw new IllegalArgumentException("Bad source given");
        }

        //structures we need to solve the problem
        Map<T, Integer> results = new HashMap<>();
        Map<T, Integer> distances = new HashMap<>();
        PriorityQueue<Pair<T, Integer>> priorityQueue = new PriorityQueue<>(
                vertexSize, new VertexComparator());

        //add all vertices to the queue
        for (int i = 0; i < adjacencyList.length; i++)
        {
            if (adjacencyList[i] != null)
            {
                T vertex = adjacencyList[i].data;

                if (vertex.equals(source))
                {
                    priorityQueue.add(new Pair<>(vertex, 0));
                    distances.put(vertex, 0);
                }
                else
                {
                    priorityQueue.add(new Pair<>(vertex, -1));
                    distances.put(vertex, -1);
                }
            }
        }

        //loop while we have vertices that haven't been visited
        while (!priorityQueue.isEmpty())
        {
            //get a vertex and record it
            Pair<T, Integer> pair = priorityQueue.poll();
            T vertex = pair.getFirst();
            int shortestPath = pair.getSecond();
            results.put(vertex, shortestPath);

            //look at adjacent vertices and update the heap
            Node<T> current = adjacencyList[getPosition(pair.getFirst())];
            while (current.next != null)
            {
                T nextVertex = current.next.data;

                //make sure we haven't finalized the shortest path to this vertex
                if (!results.containsKey(nextVertex))
                {
                    //update the value if necessary
                    int candidateShortestPath = shortestPath + current.next.fromWeight;
                    if (distances.get(nextVertex) == -1 ||
                            candidateShortestPath < distances.get(nextVertex))
                    {
                        //remove the nextVertex from the heap and reinsert
                        //with a new distance value
                        priorityQueue.remove(new Pair<>(nextVertex, null));
                        priorityQueue.add(new Pair<>(nextVertex, candidateShortestPath));
                        distances.put(nextVertex, candidateShortestPath);
                    }
                }

                current = current.next;
            }
        }

        return results;
    }

    public String toString()
    {
        StringBuilder vertexBuilder = new StringBuilder();
        StringBuilder edgeBuilder = new StringBuilder();

        //vertex set and edge set
        vertexBuilder.append("V = {");
        edgeBuilder.append("E = {");
        int vertexCount = 0;
        int edgeCount = 0;
        for (int i = 0; i < adjacencyList.length; i++)
        {
            Node<T> current = adjacencyList[i];
            if (current != null)
            {
                Object from = current.data;
                vertexCount++;
                vertexBuilder.append(vertexCount != 1 ? ", " : "");
                vertexBuilder.append(from);

                //mark all edges
                while (current.next != null)
                {
                    Object to = current.next.data;
                    edgeCount++;
                    edgeBuilder.append(edgeCount != 1 ? ", " : "");
                    edgeBuilder.append("(" + from + ", " + to + ")");

                    //move to next node
                    current = current.next;
                }
            }
        }
        vertexBuilder.append("}");
        edgeBuilder.append("}");

        return vertexBuilder.toString() + "\n" + edgeBuilder.toString();
    }

    private class Node<T>
    {
        private T data;
        private Node<T> next;
        private int fromWeight;

        public Node(T data, Node<T> next)
        {
            this.data = data;
            this.next = next;
        }

        public Node(T data, Node<T> next, int weight)
        {
            this(data, next);

            this.fromWeight = weight;
        }
    }

    private class VertexComparator implements Comparator<Pair<T, Integer>>
    {
        @Override
        public int compare(Pair<T, Integer> firstPair, Pair<T, Integer> secondPair)
        {
            //sort based on "infinity"
            if (firstPair.getSecond() == -1 && secondPair.getSecond() == -1)
            {
                return 0;
            }
            else if (firstPair.getSecond() == -1) //first is infinity only
            {
                return 1;
            }
            else if (secondPair.getSecond() == -1) //second is infinity only
            {
                return -1;
            }

            //otherwise both elements are not infinity
            return firstPair.getSecond() - secondPair.getSecond();
        }
    }
}
