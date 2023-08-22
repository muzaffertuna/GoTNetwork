package gotnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tokta
 */
public class SeriesCharacter {

    private static final LinearProbingHash<String> hash = new LinearProbingHash<>(107);
    private static final GraphMatrix graph = new GraphMatrix(107);
    private static final boolean[] marked = new boolean[107];
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {

        readGraphFromFile("gotEdges.txt");

        String menu;

        OUTER:
        do {
            System.out.println("\nWhich task you want to do?\n"
                    + "\n"
                    + "1) ReadGraphFromFile():\n"
                    + "2) IsThereAPath():\n"
                    + "3) AllPathsShorterThanEqualTo():\n"
                    + "4) ShortestPathLengthFromTo():\n"
                    + "5) NoOfPathsFromTo(): \n"
                    + "6) BFSfromTo():\n"
                    + "7) DFSfromTo():\n"
                    + "8) NoOfVerticesInComponent():\n");

            System.out.println("Please enter a number(1-8): ");
            menu = input.next();
            System.out.println("");

            switch (menu) {
                case "1":
                    System.out.println("Your choice ReadGraphFromFile()");
                    System.out.println("Graph reading from file.");
                    break;
                case "2":
                    System.out.println("Your choice IsThereAPath()");
                    System.out.println("enter name1(from): ");
                    String name1 = input.next();

                    System.out.println("enter name2(to): ");
                    String name2 = input.next();

                    System.out.println("isThereAPath(" + name1 + ", " + name2 + ") = " + isThereAPath(name1, name2));
                    System.out.println("");
                    break;
                case "3":
                    System.out.println("Your choice AllPathsShorterThanEqualTo()");
                    System.out.println("This method was to much hard please try others...");
                    System.out.println("");
                    break;
                case "4":
                    System.out.println("Your choice ShortestPathLengthFromTo()");
                    System.out.println("enter name1(from): ");
                    name1 = input.next();

                    System.out.println("enter name2(to): ");
                    name2 = input.next();

                    ShortestPathLengthFromTo(name1, name2);
                    System.out.println("");
                    break;
                case "5":
                    System.out.println("Your choice NoOfPathsFromTo()");
                    System.out.println("enter name1(from): ");
                    name1 = input.next();

                    System.out.println("enter name2(to): ");
                    name2 = input.next();

                    System.out.println("NoOfPathsFromTo(" + name1 + ", " + name2 + ") = " + NoOfPathsFromTo(name1, name2));
                    System.out.println("");
                    break;
                case "6":
                    System.out.println("enter name1(from): ");
                    name1 = input.next();

                    System.out.println("enter name2(to): ");
                    name2 = input.next();

                    BFSfromTo(name1, name2);
                    System.out.println("");
                    break;
                case "7":
                    System.out.println("enter name1(from): ");
                    name1 = input.next();

                    System.out.println("enter name2(to): ");
                    name2 = input.next();

                    DFSfromTo(name1, name2);
                    System.out.println("");
                    break;
                case "8":
                    System.out.println("enter name1: ");
                    name1 = input.next();

                    NoOfVerticesInComponent(name1);
                    System.out.println("");
                    break;
                default:
                    System.out.println("Exiting the program...");
                    break OUTER;
            }
            System.out.println("---------------------------------------------------"
                    + "-------------------------------------------------------------");

        } while (!menu.equals("9"));

//        System.out.println(isThereAPath("Daario", "Shireen"));
//        System.out.println(isThereAPath("Amory", "Karl"));
//        System.out.println("");
//
//        ShortestPathLengthFromTo("Dalla", "Shireen");
//        ShortestPathLengthFromTo("Cressen", "Kallllr");
//        System.out.println("");
//
//        System.out.println("NoOfPathsFromTo = " + NoOfPathsFromTo("Luwin", "Bran"));
//        System.out.println("NoOfPathsFromTo = " + NoOfPathsFromTo("Theon", "Robb"));
//        System.out.println("NoOfPathsFromTo = " + NoOfPathsFromTo("Theeeeeeeon", "Robb"));
//        System.out.println("");
//
//        BFSfromTo("Amory", "Karl");
//        BFSfromTo("Daario", "Shireen");
//        BFSfromTo("Orell", "Irri");
//        BFSfromTo("Theeeeeeeon", "Robb");
//        System.out.println("");
//
//        DFSfromTo("Amory", "Karl");
//        DFSfromTo("Daario", "Shireen");
//        DFSfromTo("Orell", "Irri");
//        DFSfromTo("Theeeeeeeon", "Robb");
//        System.out.println("");
//
//        NoOfVerticesInComponent("Bran");
//        NoOfVerticesInComponent("Lothar");
//        int weight = graph.edges[hash.findIndex("Ygritte")][hash.findIndex("Rattleshirt")];
//        System.out.println(weight);
//
//        System.out.println(hash.findIndex("Rayyleshi"));
//        System.out.println(hash);
//        System.out.println(hash.findIndex("Aemon") + "   " + hash.findIndex("Samwell") + "\n");
//        System.out.println(graph);
    }

    public static void readGraphFromFile(String f) throws FileNotFoundException {

        try {
            File file = new File(f);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String singleLine = scan.nextLine();
                String[] lineArray = singleLine.split(",");

                String source = lineArray[0];
                String target = lineArray[1];
                int weight = Integer.parseInt(lineArray[2]);

                hash.insert(source);
                hash.insert(target);

                graph.addEdge(hash.findIndex(source), hash.findIndex(target), weight);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    public static boolean isThereAPath(String name1, String name2) {

        int from = hash.findIndex(name1);
        int to = hash.findIndex(name2);

        if (from == -1 || to == -1) {
            return false;
        } else {
            BreadthFirstSearch p = new BreadthFirstSearch(graph, from);
            return p.hasPathTo(to);
        }
    }

    public static void ShortestPathLengthFromTo(String name1, String name2) {

        int from = hash.findIndex(name1);
        int to = hash.findIndex(name2);

        if (from == -1 || to == -1) {
            System.out.println("Infinity");
        } else {
            GraphDijkstra t = new GraphDijkstra(graph.getNumV());
            t.dijkstra(graph.getEdges(), from, to);
        }
    }

    public static int NoOfPathsFromTo(String name1, String name2) {

        int from = hash.findIndex(name1);
        int to = hash.findIndex(name2);

        if (from == -1 || to == -1) {
            return -1;
        }

        return NoOfPathsFromTo(from, to);
    }

    private static int NoOfPathsFromTo(int from, int to) { // recursive
        if (from == to) {
            return 1;
        }
        marked[from] = true;

        Integer[] a = (Integer[]) graph.neighborsArray(from);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            if (!marked[a[i]]) {
                list.add(a[i]);
            }
        }

        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            count = count + NoOfPathsFromTo(list.get(i), to);
        }
        return count;
    }

    public static void BFSfromTo(String name1, String name2) {

        int from = hash.findIndex(name1);
        int to = hash.findIndex(name2);

        if (from == -1 || to == -1) {
            System.out.println("There is no such Vertex.");
        } else {
            BreadthFirstSearch p = new BreadthFirstSearch(graph, from);
            p.printPathTo(to, hash);
        }
    }

    public static void DFSfromTo(String name1, String name2) {

        int from = hash.findIndex(name1);
        int to = hash.findIndex(name2);

        if (from == -1 || to == -1) {
            System.out.println("There is no such Vertex.");
        } else {
            DepthFirstPaths p = new DepthFirstPaths(graph, from);
            p.printPathTo(to, hash);
        }
    }

    public static void NoOfVerticesInComponent(String name1) {
        int vertex = hash.findIndex(name1);

        if (vertex == -1) {
            System.out.println("There is no such Vertex.");
        } else {
            ConnectedComponents components = new ConnectedComponents(graph);
            int[] comp = components.getId();

            int count = 0;
            for (int i = 0; i < comp.length; i++) {
                if (comp[vertex] == comp[i]) {
                    count++;
                }
            }
            System.out.println("NoOfVerticesInComponent(" + name1 + ") = " + count);
        }
    }

}
