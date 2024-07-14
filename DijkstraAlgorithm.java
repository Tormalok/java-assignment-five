import java.util.*;

public class DijkstraAlgorithm {

    static class Node implements Comparable<Node> {
        public int vertex;
        public int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        PriorityQueue<Node> pq = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !visited[v] && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new Node(v, dist[v]));
                }
            }
        }

        printSolution(dist, src);
    }

    public static void printSolution(int[] dist, int src) {
        System.out.println("Vertex\t\tDistance from Source " + src);
        for (int i = 0; i < dist.length; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of vertices in the graph:");
        int V = scanner.nextInt();

        int[][] graph = new int[V][V];

        System.out.println("Enter the adjacency matrix of the graph (enter 0 if there is no edge):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the source vertex:");
        int src = scanner.nextInt();

        dijkstra(graph, src);

        scanner.close();
    }
}

