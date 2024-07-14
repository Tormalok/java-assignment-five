import java.util.Arrays;

public class ClosestPair {

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }

    static double bruteForce(Point P[], int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j < n; ++j)
                if (distance(P[i], P[j]) < min)
                    min = distance(P[i], P[j]);
        return min;
    }

    static double stripClosest(Point strip[], int size, double d) {
        double min = d;

        Arrays.sort(strip, (a, b) -> a.y - b.y);

        for (int i = 0; i < size; ++i)
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j)
                if (distance(strip[i], strip[j]) < min)
                    min = distance(strip[i], strip[j]);

        return min;
    }

    static double closestUtil(Point P[], int n) {

        if (n <= 3)
            return bruteForce(P, n);

        int mid = n / 2;
        Point midPoint = P[mid];

        double dl = closestUtil(Arrays.copyOfRange(P, 0, mid), mid);
        double dr = closestUtil(Arrays.copyOfRange(P, mid, n), n - mid);

        double d = Math.min(dl, dr);

        Point strip[] = new Point[n];
        int j = 0;
        for (int i = 0; i < n; i++)
            if (Math.abs(P[i].x - midPoint.x) < d)
                strip[j++] = P[i];

        return Math.min(d, stripClosest(strip, j, d));
    }

    static double closest(Point P[], int n) {
        Arrays.sort(P, (a, b) -> a.x - b.x);
        return closestUtil(P, n);
    }

    // Driver code
    public static void main(String[] args) {
        Point P[] = {new Point(2, 3), new Point(12, 30),
                new Point(40, 50), new Point(5, 1),
                new Point(12, 10), new Point(3, 4)};
        int n = P.length;
        System.out.println("The smallest distance is " + closest(P, n));
    }
}
