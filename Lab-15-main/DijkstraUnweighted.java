import java.util.*;
//inspo: https://www.geeksforgeeks.org/shortest-path-unweighted-graph/
public class DijkstraUnweighted {
    private List<List<Integer>> graph;
    private int vertices;
    
    public DijkstraUnweighted(int vertices) {
        this.vertices = vertices;
        graph = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest) {
        graph.get(src).add(dest);
    }
    
    public int[] shortestPath(int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            // bfs simplification for djikstra cuz unweighted
            for (int v : graph.get(u)) {
                if (dist[v] == Integer.MAX_VALUE) { //only get unprocessed nodes that are one away
                    dist[v] = dist[u] + 1; 
                    queue.add(v);
                }
            }
        }
        
        return dist;
    }
    public static void main(String[] args) {
        DijkstraUnweighted g = new DijkstraUnweighted(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        
        int source = 0;
        int[] distances = g.shortestPath(source);
        
        System.out.println("distances from vertex " + source + ":");
        for (int i = 0; i < distances.length; i++) {
            System.out.println("to vertex " + i + ": " +(distances[i] == Integer.MAX_VALUE ? "Infinity" : distances[i]));
        }
    }
}