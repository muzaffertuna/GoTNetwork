package gotnetwork;

/**
 *
 * @author tokta
 */
public class ConnectedComponents {

    private int count;
    private boolean[] marked;
    private int[] id;

    public ConnectedComponents(GraphMatrix g) {
        count = 0;
        marked = new boolean[g.getNumV()];
        id = new int[g.getNumV()];

        for (int v = 0; v < g.getNumV(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    public int[] getId() {
        return id;
    }

    private void dfs(GraphMatrix g, int v) {
        marked[v] = true;
        id[v] = count;

        Integer[] a = (Integer[]) g.neighborsArray(v);

        for (int i = 0; i < a.length; i++) {
            int neighbor = a[i];

            if (!marked[neighbor]) {
                dfs(g, neighbor);
            }
        }
    }
}
