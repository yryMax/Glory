package CodeForce.P1131C;

import java.util.Arrays;
import java.util.Scanner;

public class Birthday_MySolution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfChildren = Integer.parseInt(scanner.nextLine());
        int[] childrenHeights = new int[numberOfChildren];
        for (int i = 0; i < numberOfChildren; i++)
            childrenHeights[i] = scanner.nextInt();

        findComfortHeights(childrenHeights);
    }

    private static void findComfortHeights(int[] childrenHeights) {
        Arrays.sort(childrenHeights);
        System.out.print(childrenHeights[childrenHeights.length - 1] + " ");
        int index = childrenHeights.length - 2;
        while (index >= 0) {
            System.out.print(childrenHeights[index] + " ");
            index -= 2;
        }
        index = (index == -2) ? 1 : 0;
        while (index < childrenHeights.length - 1) {
            System.out.print(childrenHeights[index] + " ");
            index += 2;
        }
    }
}
