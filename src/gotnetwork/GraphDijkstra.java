package gotnetwork;

/**
 *
 * @author tokta
 */
public class GraphDijkstra {

    private int V;

    public GraphDijkstra(int V) {
        this.V = V;
    }

    // A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree
    private int minDistance(int dist[], Boolean sptSet[]) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }

        return min_index;
    }

    // A utility function to print the constructed distance array
    private void printSolution(int dist[], int src, int target) {
        System.out.println("Source \t\t Target \t Distance from Source to Target");

        if (dist[target] == Integer.MAX_VALUE) {
            System.out.println("Infinity");
        } else {
            System.out.println(src + " \t\t " + target + " \t\t " + dist[target]);
        }

    }

    // Function that implements Dijkstra's single source shortest path algorithm 
    // for a graph represented using adjacency matrix representation
    public void dijkstra(int graph[][], int src, int target) {
        int dist[] = new int[V]; // The output array. dist[i] will hold the shortest distance from src to i

        // sptSet[i] will be true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed. 
            // u is always equal to src in first iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < V; v++) // Update dist[v] only if is not in sptSet, there is an
            // edge from u to v, and total weight of path from src to
            // v through u is smaller than current value of dist[v]
            {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // print the constructed distance array
        printSolution(dist, src, target);
    }

}
