package gotnetwork;

/**
 *
 * @author tokta
 */
public class GraphMatrix {

    private int[][] edges; // can be anything, but int vertices handy //can be double if there are double weigths
    private int numV;
    private int numE;

    public GraphMatrix(int V) {
        edges = new int[V][V];
        this.numV = V;

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
    }

    public int[][] getEdges() {
        return edges;
    }

    public int getNumV() {
        return numV;
    }

    public int getNumE() {
        return numE;
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
        edges[to][from] = weight;
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    public int degree(int v) {
        int degree = 0;

        for (int i = 0; i < numV; i++) {
            degree += edges[v][i];
        }
        return degree;
    }

    public Integer[] neighborsArray(int from) {
        Integer[] ar = new Integer[neighborsSize(from)];

        int index = 0;
        for (int i = 0; i < numV; i++) {
            if (edges[from][i] > 0) {
                ar[index++] = i;
            }
        }
        return ar;
    }

    private int neighborsSize(int from) {
        int size = 0;

        for (int i = 0; i < numV; i++) {
            if (edges[from][i] > 0) {
                size++;
            }
        }
        return size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                s.append(edges[i][j]).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
