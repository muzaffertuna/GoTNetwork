package gotnetwork;

/**
 *
 * @author tokta
 */
public class DepthFirstPaths {

    int from;
    boolean[] marked;
    int[] edgeTo;

    public DepthFirstPaths(GraphMatrix g, int from) {
        marked = new boolean[g.getNumV()];
        edgeTo = new int[g.getNumV()];
        this.from = from;
        dfs(g, from);
    }

    //recursive
    private void dfs(GraphMatrix g, int source) {
        marked[source] = true;

        Integer[] a = (Integer[]) g.neighborsArray(source);

        for (int i = 0; i < a.length; i++) {
            int neighbor = a[i];

            if (!marked[neighbor]) {
                //System.out.println("..." + neighbor);
                dfs(g, neighbor);
                edgeTo[neighbor] = source;
            }
        }
    }

    public boolean hasPathTo(int w) {
        return (marked[w]);
    }

    private Integer[] pathTo(int w) {
        int k = edgeTo[w];

        java.util.Stack<Integer> st = new java.util.Stack<>();
        st.push(k);

        while (k != this.from) {
            k = edgeTo[k];
            st.push(k);
        }
        //st.push(from);

        Integer[] path = new Integer[st.size()];

        for (int i = 0; i < path.length; i++) {
            path[i] = st.pop();
        }
        return path;
    }

    public void printPathTo(int w, LinearProbingHash hash) {
        Integer[] path = pathTo(w);

        for (int i = 0; i < path.length; i++) {
            System.out.print("->" + hash.getTable()[path[i]]);
        }
        System.out.println("->" + hash.getTable()[w]);
    }
}
