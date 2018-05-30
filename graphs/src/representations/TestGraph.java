package representations;

import java.util.List;
import java.util.Map;

public class TestGraph
{
    public static void main(String[] args)
    {
        //create the graph
        DirectedALGraph<String> letterGraph = new DirectedALGraph<>();

        //add vertices
        letterGraph.addVertices("a", "b", "c", "d", "e", "f", "g", "h");

        //add edges
        letterGraph.addUndirectedEdge("a", "b", 7);
        letterGraph.addUndirectedEdge("a", "h", 8);
        letterGraph.addUndirectedEdge("a", "g", 10);
        letterGraph.addUndirectedEdge("a", "f", 3);
        letterGraph.addUndirectedEdge("a", "e", 2);
        letterGraph.addUndirectedEdge("f", "e", 4);
        letterGraph.addUndirectedEdge("b", "c", 4);
        letterGraph.addUndirectedEdge("e", "b", 1);
        letterGraph.addUndirectedEdge("e", "c", 5);
        letterGraph.addUndirectedEdge("e", "d", 6);
        letterGraph.addUndirectedEdge("c", "d", 2);
        letterGraph.addUndirectedEdge("b", "g", 9);
        letterGraph.addUndirectedEdge("g", "d", 1);
        letterGraph.addUndirectedEdge("g", "h", 7);
        letterGraph.addUndirectedEdge("h", "d", 5);

        Map<String, String> mst = letterGraph.prims("c");
        for (String vertex : mst.keySet())
        {
            System.out.println(vertex + " --- " + mst.get(vertex));
        }
    }

    private static void testShortestPath()
    {
        //create the graph
        DirectedALGraph<String> letterGraph = new DirectedALGraph<>();

        //add vertices
        letterGraph.addVertices("a", "b", "c", "d");

        //add edges
        letterGraph.addUndirectedEdge("a", "b", 1);
        letterGraph.addUndirectedEdge("b", "c", 1);
        letterGraph.addUndirectedEdge("a", "c", 6);
        letterGraph.addUndirectedEdge("c", "d", 3);

        Map<String, Integer> shortestPaths = letterGraph.dijkstras("a");
        for (String vertex : shortestPaths.keySet())
        {
            System.out.println(vertex + ": " + shortestPaths.get(vertex));
        }
    }

    private static void testGraph()
    {
        //create the graph
        DirectedALGraph<String> letterGraph = new DirectedALGraph<>();

        //add a few vertices
        letterGraph.addVertices("a", "e", "i", "o", "u");
        letterGraph.addVertices("x", "y", "z");

        //add a few edges
        letterGraph.addEdges(new Pair<>("a", "e"), new Pair<>("a", "i"), new Pair<>("a", "o"),
                new Pair<>("o", "x"), new Pair<>("o", "a"), new Pair<>("i", "z"),
                new Pair<>("a", "x"));

        //test vertex contains
        System.out.println(letterGraph.hasVertex("a"));
        System.out.println(letterGraph.hasVertex("e"));
        System.out.println(letterGraph.hasVertex("i"));
        System.out.println(letterGraph.hasVertex("m"));
        System.out.println(letterGraph.hasVertex("n"));
        System.out.println();

        //test edge contains
        System.out.println(letterGraph.hasEdge("a", "e"));
        System.out.println(letterGraph.hasEdge("a", "o"));
        System.out.println(letterGraph.hasEdge("o", "x"));
        System.out.println(letterGraph.hasEdge("a", "x"));
        System.out.println(letterGraph.hasEdge("j", "x"));

        //test degree of vertices
        System.out.println(letterGraph.inDegree("x"));
        System.out.println(letterGraph.inDegree("a"));
        System.out.println(letterGraph.inDegree("u"));
        System.out.println(letterGraph.inDegree("aaa"));
        System.out.println();

        //test the dfs traversal
        List<String> dfs = letterGraph.dfs("a");
        for (String vertex : dfs)
        {
            System.out.println(vertex);
        }
    }
}
