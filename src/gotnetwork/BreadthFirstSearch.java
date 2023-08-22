package gotnetwork;

import java.util.LinkedList;

/**
 *
 * @author tokta
 */
public class BreadthFirstSearch {

    int from;
    boolean[] marked;
    int[] edgeTo;
    int[] distTo;

    public BreadthFirstSearch(GraphMatrix g, int from) {
        marked = new boolean[g.getNumV()];
        edgeTo = new int[g.getNumV()];  // warning this is initalized to 0 
        distTo = new int[g.getNumV()];
        this.from = from;
        bfs(g, from);  // method bfs is not recursive
    }

    private void bfs(GraphMatrix g, int source) {  // this is not recursive
        marked[source] = true;
        Integer[] a = (Integer[]) g.neighborsArray(source);

        if (a.length == 0) {
            return;
        }

        LinkedList<Integer> q = new LinkedList<>();  // this is to work as a queue
        q.addLast(source);

        while (!q.isEmpty()) {
            source = q.removeFirst();
            a = (Integer[]) g.neighborsArray(source);

            for (int i = 0; i < a.length; i++) {
                int w = a[i];

                if (!marked[w]) {
                    //System.out.println(w + ".");
                    q.addLast(w);
                    marked[w] = true;
                    edgeTo[w] = source;
                    distTo[w] = distTo[source] + 1;
                }
            }
        }
    }

    public boolean hasPathTo(int w) { //source vertex: from
        return marked[w];
    }

    public int distTo(int w) { //source vertex: from
        return distTo[w];
    }

    private Integer[] pathTo(int w) {  // a path to w from "from"
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
