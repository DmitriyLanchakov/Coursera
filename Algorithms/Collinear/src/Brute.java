import java.util.Arrays;

/**
 * Created by den on 12.02.15.
 */
public class Brute {

    private static Point[] readPoints(String fileName) {
        In in = new In(fileName);
        int pointQuant = in.readInt();
        Point[] points = new Point[pointQuant];
        for (int i = 0; i < pointQuant; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }
        return points;
    }

    private static void check4PointLine(Point[] points) {
        for (int first = 0; first < points.length - 3; first++)
            for (int second = first + 1; second < points.length - 2; second++)
                for (int third = second + 1; third < points.length - 1; third++)
                    for (int fourth = third + 1; fourth < points.length; fourth++) {
                        if (points[first].SLOPE_ORDER.compare(points[second], points[third]) == 0 &&
                                points[first].SLOPE_ORDER.compare(points[second], points[fourth]) == 0 &&
                                points[first].SLOPE_ORDER.compare(points[third], points[fourth]) == 0) {
                            Point[] result = new Point[4];
                            result[0] = points[first];
                            result[1] = points[second];
                            result[2] = points[third];
                            result[3] = points[fourth];
                            Arrays.sort(result);
                            result[0].drawTo(result[3]);
                            for (int i = 0; i < result.length; i++) {
                                if (i == 0)
                                    System.out.print(result[i].toString() + " ->");
                                else if (i !=0 && i < result.length-1)
                                    System.out.print(" " + result[i].toString() + " ->");
                                else if (i == result.length-1)
                                    System.out.print(" " + result[i].toString());
                            }
                            System.out.println();
                        }
                    }
    }

    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        String fileName = args[0];
        Point[] points = Brute.readPoints(fileName);
        Brute.check4PointLine(points);

    }
}
