import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by den on 18.02.15.
 */
public class Fast {

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

    private static void sort(Point[] points) {
        for (int i = 0; i < points.length;i++) {
            Comparator<Point> compSlopes = points[i].SLOPE_ORDER;
            Arrays.sort(points, i+1, points.length, compSlopes);
            List<Point> temp = new ArrayList<Point>();
            for (int j = i+1; j < points.length-1;j++) {
                double x = compSlopes.compare(points[j], points[j+1]);
                if (x ==  0 && temp.isEmpty()) {
                    temp.add(points[j]);
                    temp.add(points[j + 1]);
                }
                else if (x == 0 && !temp.isEmpty())
                    temp.add(points[j+1]);
                else if (temp.size() >= 3 || (j == points.length-2 && temp.size() >=3)) {
                    temp.add(points[i]);
                    Collections.sort(temp);
                    temp.get(0).drawTo(temp.get(temp.size() - 1));
                    for (int k = 0; k < temp.size(); k++) {
                        if (k == 0)
                            System.out.print(temp.get(k).toString() + " ->");
                        else if (k !=0 && k < temp.size()-1)
                            System.out.print(" " + temp.get(k).toString() + " ->");
                        else if (k == temp.size()-1)
                            System.out.print(" " + temp.get(k).toString());
                    }
                    System.out.println();
                    temp.clear();
                   //
                   // continue;
                }
                else if (x != 0)
                    temp.clear();
//                if ((j == points.length-2 && temp.size() >=3)) {
//                    temp.add(points[i]);
//                    Collections.sort(temp);
//                    temp.get(0).drawTo(temp.get(temp.size() - 1));
//                    for (int k = 0; k < temp.size(); k++) {
//                        if (k == 0)
//                            System.out.print(temp.get(k).toString() + " ->");
//                        else if (k !=0 && k < temp.size()-1)
//                            System.out.print(" " + temp.get(k).toString() + " ->");
//                        else if (k == temp.size()-1)
//                            System.out.print(" " + temp.get(k).toString());
//                    }
//                    System.out.println();
//                    temp.clear();
//                }
            }
        }

    }


    public static void main(String[] args) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(0.01);
        String fileName = args[0];
        Point[] points = Fast.readPoints(fileName);
        sort(points);

    }
}
