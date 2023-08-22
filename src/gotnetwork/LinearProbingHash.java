package gotnetwork;

/**
 *
 * @author tokta
 */
public class LinearProbingHash<Key> {

    private Key[] table;
    private int M;
    private int N;  // number of full elements

    public LinearProbingHash(int M) {
        table = (Key[]) new Object[M];
        this.M = M;
    }

    public Key[] getTable() {
        return table;
    }

    private int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M); // will result in a positive integer.(all 1 except the sign bit.)
    }

    public boolean insert(Key key) {
        int ix = hash(key);
        //System.out.print(" hash : " + ix);
        // must check for a whole cycle there can be infinite loop
        if (N == M) {
            //System.out.println(" : The table is full! \n");
            return false;
        }

        while (table[ix] != null) {
            if (table[ix].equals(key)) {
                return false;
            }
            ix = (ix + 1) % M;
        }
        // if table[ix]==null this means it is empty. Insert.
        table[ix] = key;
        N++;
        return true;
    }

    public boolean insert2(Key key) {
        int i;
        int h = hash(key);

        System.out.print(" hash: " + h);
        for (i = h; table[i] != null; i = (i + 1) % M) {
            if (table[i].equals(key)) {
                return true;
            }
            if (i + 1 == h) {
                return false; // table is full
            }
        }
        table[i] = key;
        N++; // increase number of stored items
        return true;
    }

    // contains method returns true if hash map contains the Key
    public boolean contains(Key key) {
        int ix = hash(key);
        System.out.print(" hash : " + ix);
        int startIx = ix;

        while (table[ix] != null && (ix + 1 != startIx)) {
            if (table[ix].equals(key)) {
                return true; // found
            }            // if (ix + 1 == startIx) return false; // starting point
            ix = (ix + 1) % M; // cycle through
            // System.out.print(" ix : " + ix);
        }
        return false;
    }

    public int findIndex(Key key) {
        int ix = hash(key);
        //System.out.print(" hash : " + ix);
        int startIx = ix;

        while (table[ix] != null && (ix + 1 != startIx)) {
            if (table[ix].equals(key)) {
                return ix; // found
            }            // if (ix + 1 == startIx) return false; // starting point
            ix = (ix + 1) % M; // cycle through
            // System.out.print(" ix : " + ix);
        }
        return -1;
    }

    @Override
    public String toString() {
        String s = "[";

        for (int i = 0; i < M; i++) {
            s += table[i] + ",";
        }
        return s + "]";
    }
}
