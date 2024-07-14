import java.util.*;

public class PrimMST {

    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static class Graph {
        private final int vertices;
        private final List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            adjacencyList.get(source).add(edge);
            adjacencyList.get(destination).add(new Edge(destination, source, weight)); // For undirected graph
        }

        public int getVertices() {
            return vertices;
        }

        public List<Edge> getEdges(int vertex) {
            return adjacencyList.get(vertex);
        }
    }

    public List<Edge> primMST(Graph graph) {
        boolean[] visited = new boolean[graph.getVertices()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();

        addEdges(graph, pq, 0, visited);

        while (!pq.isEmpty() && mst.size() < graph.getVertices() - 1) {
            Edge edge = pq.poll();
            if (visited[edge.destination]) {
                continue;
            }
            mst.add(edge);
            addEdges(graph, pq, edge.destination, visited);
        }
        return mst;
    }

    private void addEdges(Graph graph, PriorityQueue<Edge> pq, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (Edge edge : graph.getEdges(vertex)) {
            if (!visited[edge.destination]) {
                pq.add(edge);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        Graph graph = new Graph(vertices);

        System.out.println("Enter each edge as source destination weight:");
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        PrimMST prim = new PrimMST();
        List<Edge> mst = prim.primMST(graph);

        System.out.println("Edges in the Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println("Edge: " + edge.source + " - " + edge.destination + " weight: " + edge.weight);
        }

        scanner.close();
    }
}

