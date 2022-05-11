package AcWing.P98;

import java.util.Scanner;

/** 0x02 Recursion; Page 18; POJ 3889
 * @author Zhejia Hu
 */
public class FractalCity {
    public static void main(String[] args) {
        Scanner scannerTerminal = new Scanner(System.in);
        int numberTestCases = scannerTerminal.nextInt();
        while (numberTestCases-- > 0) {
            int currentCityLevel = scannerTerminal.nextInt();
            long cityIndex1 = scannerTerminal.nextLong();
            long cityIndex2 = scannerTerminal.nextLong();
            Coordinate coordinate1 = solution(currentCityLevel, cityIndex1 - 1);
            Coordinate coordinate2 = solution(currentCityLevel, cityIndex2 - 1);
            System.out.println(Math.round(Math.hypot(10 * (coordinate1.x_coordinate - coordinate2.x_coordinate), 10 * (coordinate1.y_coordinate - coordinate2.y_coordinate))));
        }
    }

    public static Coordinate solution(int currentCityLevel, long targetHouseIndex) {
        if (currentCityLevel == 0)
            return new Coordinate(0, 0);
        else {
            long currentCityLength = 1L << (currentCityLevel);
            int previousCityLevel = currentCityLevel - 1;
            long previousCitySize = 1L << 2 * previousCityLevel;
            long descendentHouseIndex = targetHouseIndex % previousCitySize;
            Coordinate descendentCoordinate = solution(previousCityLevel, descendentHouseIndex);
            assert descendentCoordinate != null;
            int currentHouseQuadrant = (int) (targetHouseIndex / previousCitySize);
            return switch (currentHouseQuadrant) {
                case 0 -> new Coordinate(descendentCoordinate.y_coordinate, descendentCoordinate.x_coordinate);
                case 1 -> new Coordinate(descendentCoordinate.x_coordinate, descendentCoordinate.y_coordinate + currentCityLength / 2);
                case 2 -> new Coordinate(descendentCoordinate.x_coordinate + currentCityLength / 2, descendentCoordinate.y_coordinate + currentCityLength / 2);
                case 3 -> new Coordinate(2L * (currentCityLength / 2) - descendentCoordinate.y_coordinate - 1, currentCityLength / 2 - descendentCoordinate.x_coordinate - 1);
                default -> null;
            };
        }
    }
}

class Coordinate {
    long x_coordinate;
    long y_coordinate;

    public Coordinate(long x_coordinate, long y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Coordinate otherCoordinate)
            return x_coordinate == otherCoordinate.x_coordinate && y_coordinate == otherCoordinate.y_coordinate;
        else return false;
    }

    @Override
    public String toString() {
        return "(" + x_coordinate + ", " + y_coordinate + ")";
    }
}