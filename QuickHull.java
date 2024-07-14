import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuickHull {

    public static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static List<Point> quickHull(List<Point> points) {
        List<Point> convexHull = new ArrayList<>();
        if (points.size() < 3) return convexHull;

        Point minX = points.get(0);
        Point maxX = points.get(0);
        for (Point p : points) {
            if (p.x < minX.x) minX = p;
            if (p.x > maxX.x) maxX = p;
        }

        convexHull.add(minX);
        convexHull.add(maxX);

        List<Point> leftSet = new ArrayList<>();
        List<Point> rightSet = new ArrayList<>();

        for (Point p : points) {
            if (p == minX || p == maxX) continue;
            if (pointLocation(minX, maxX, p) > 0) {
                leftSet.add(p);
            } else {
                rightSet.add(p);
            }
        }

        hullSet(minX, maxX, leftSet, convexHull);
        hullSet(maxX, minX, rightSet, convexHull);

        return convexHull;
    }

    private static void hullSet(Point A, Point B, List<Point> set, List<Point> hull) {
        int insertPosition = hull.indexOf(B);
        if (set.size() == 0) return;
        if (set.size() == 1) {
            Point p = set.get(0);
            set.remove(p);
            hull.add(insertPosition, p);
            return;
        }

        double dist = Double.NEGATIVE_INFINITY;
        int furthestPoint = -1;
        for (int i = 0; i < set.size(); i++) {
            Point p = set.get(i);
            double distance = distance(A, B, p);
            if (distance > dist) {
                dist = distance;
                furthestPoint = i;
            }
        }

        Point P = set.get(furthestPoint);
        set.remove(furthestPoint);
        hull.add(insertPosition, P);

        List<Point> leftSetAP = new ArrayList<>();
        for (Point m : set) {
            if (pointLocation(A, P, m) > 0) {
                leftSetAP.add(m);
            }
        }

        List<Point> leftSetPB = new ArrayList<>();
        for (Point m : set) {
            if (pointLocation(P, B, m) > 0) {
                leftSetPB.add(m);
            }
        }

        hullSet(A, P, leftSetAP, hull);
        hullSet(P, B, leftSetPB, hull);
    }

    private static double distance(Point A, Point B, Point C) {
        double ABx = B.x - A.x;
        double ABy = B.y - A.y;
        double num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
        return Math.abs(num);
    }

    private static int pointLocation(Point A, Point B, Point P) {
        double cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
        return (cp1 > 0) ? 1 : (cp1 < 0) ? -1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<>();

        System.out.println("Enter the number of points:");
        int numPoints = scanner.nextInt();

        System.out.println("Enter the points (x y):");
        for (int i = 0; i < numPoints; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points.add(new Point(x, y));
        }

        List<Point> hull = quickHull(points);

        System.out.println("Convex Hull:");
        for (Point p : hull) {
            System.out.println("(" + p.x + ", " + p.y + ")");
        }

        scanner.close();
    }
}


