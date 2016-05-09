import java.util.*;

/**
 * input: 
 * Line 1: num test case T
 * Line 2: two ints, N M
 *              N = num nodes in graph, M = num edges
 * Lines M: three ints, x y z
 *              x y nodes where edge exists
 *              z length of edge
 * Last line (M+1): starting position on graph S
 *
 * this is NOT a directed graph
 */

public class Main {

    static class Vertex{
        boolean visit;
        int name;
        int dist;
        List adj;
        public Vertex(int name){
            this.name = name;
            this.dist = Integer.MAX_VALUE;//default to infinity
            this.adj = new ArrayList<>();
            this.visit = false;
        }
    }

    static class DistCompare implements Comparator<Vertex>{

        @Override
        public int compare(Vertex left, Vertex right) {
            return left.dist - right.dist;
        }
    }

    static class VertEdge{
        int dest;
        int length;
        public VertEdge(int dest, int length){
            this.dest = dest;
            this.length = length;
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int tests = in.nextInt();


        for(int test = 0; test < tests; test++){
            int nodes = in.nextInt();
            int edges = in.nextInt();
            Vertex[] vertices = new Vertex[nodes];
            ArrayList<VertEdge>[] adjacent = new ArrayList[nodes];//each node can have many edges

            for(int node = 0; node < nodes; node++){
                vertices[node] = new Vertex(node);//create each node
                adjacent[node] = new ArrayList<>();//create list for node's edges
            }
            /*edges connecting nodeA to nodeB, with length dist*/
            for(int edge = 1; edge <= edges; edge++){
                int nodeA = in.nextInt();
                int nodeB = in.nextInt();
                int dist = in.nextInt();
                vertices[nodeA-1].adj.add(new VertEdge(nodeB, dist));
                vertices[nodeB-1].adj.add(new VertEdge(nodeA, dist));
            }

            /*define priority queue*/
            DistCompare vertDist = new DistCompare();
            PriorityQueue<Vertex> priorityQueue = new PriorityQueue<Vertex>(nodes,vertDist);

            int start = in.nextInt();
            vertices[start].dist = 0;//set start point distance to 0

            /*priorityQueue.add(vertices[start]);//add it to the queue
            while(!priorityQueue.isEmpty()){
                int pos = priorityQueue.poll().name;
                vertices[pos].visit = true;



            }*/
            /*TODO: fix comparator, for.. array input?*/
            /*TODO: implement shortest span algo..*/
        }

    }
}
