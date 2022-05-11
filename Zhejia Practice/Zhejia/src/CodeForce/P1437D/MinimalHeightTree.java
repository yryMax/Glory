package CodeForce.P1437D;

import java.util.Scanner;

public class MinimalHeightTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTest = scanner.nextInt();
        while (numberTest-- > 0) {
            int numberVertices = scanner.nextInt();
            int[] vertices = new int[numberVertices];
            for (int i = 0; i < numberVertices; i++) vertices[i] = scanner.nextInt();
                calculateMinimalHeight(vertices);
        }
    }

    private static void calculateMinimalHeight(int[] vertices) {
        if (vertices.length == 1) {
            System.out.println(1);
            return;
        }

        int height = 1;
        int previousNodeNumbers = 1;
        int currentNodeNumbers = 1;
        for (int i = 2; i < vertices.length; i++) {
            if (vertices[i] < vertices[i - 1]) {
                previousNodeNumbers--;
            }
            if (previousNodeNumbers == 0) {
                height++;
                previousNodeNumbers = currentNodeNumbers;
                currentNodeNumbers = 0;
            }
            currentNodeNumbers++;
        }

        System.out.println(height);
    }
}
