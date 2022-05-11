package AcWing.P112;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class RadarInstallation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PrintWriter printWriter = new PrintWriter(System.out);
        int numIsland = scanner.nextInt();
        int distance = scanner.nextInt();

        double[][] ranges = new double[numIsland][2];
        for (int i = 0; i < numIsland; i++) {
            int x_coordinate = scanner.nextInt();
            int y_coordinate = scanner.nextInt();

            if (y_coordinate > distance) {
                printWriter.println(-1);
                printWriter.flush();
                return;
            }
            ranges[i][0] = x_coordinate - Math.sqrt(distance * distance - y_coordinate * y_coordinate);
            ranges[i][1] = x_coordinate + Math.sqrt(distance * distance - y_coordinate * y_coordinate);
        }

        Arrays.sort(ranges, Comparator.comparingDouble((double[] row) -> row[1]));
        int result = 1;
        double prevLast = ranges[0][1];
        for (int i = 1; i < numIsland; i++) {
            if (ranges[i][0] > prevLast) {
                result++;
                prevLast = ranges[i][1];
            }
        }
        printWriter.println(result);
        printWriter.flush();
    }
}
