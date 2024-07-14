import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TravellingSalesman {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        int numCities = scanner.nextInt();

        int[][] graph = new int[numCities][numCities];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        TravellingSalesman tsp = new TravellingSalesman();
        List<Integer> tour = tsp.findTour(graph);
        System.out.println("Tour: " + tour);
        System.out.println("Tour Cost: " + tsp.calculateTourCost(graph, tour));

        scanner.close();
    }

    public List<Integer> findTour(int[][] graph) {
        int numCities = graph.length;
        boolean[] visited = new boolean[numCities];
        List<Integer> tour = new ArrayList<>();
        int currentCity = 0;

        tour.add(currentCity);
        visited[currentCity] = true;

        for (int i = 1; i < numCities; i++) {
            int nextCity = findNearestNeighbor(graph, currentCity, visited);
            tour.add(nextCity);
            visited[nextCity] = true;
            currentCity = nextCity;
        }

        tour.add(tour.get(0));  // Returning to the starting city
        return tour;
    }

    private int findNearestNeighbor(int[][] graph, int currentCity, boolean[] visited) {
        int nearestCity = -1;
        int minDistance = Integer.MAX_VALUE;

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i] && graph[currentCity][i] < minDistance) {
                nearestCity = i;
                minDistance = graph[currentCity][i];
            }
        }

        return nearestCity;
    }

    public int calculateTourCost(int[][] graph, List<Integer> tour) {
        int cost = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            cost += graph[tour.get(i)][tour.get(i + 1)];
        }
        return cost;
    }
}
