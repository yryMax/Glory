package CodeForce.P652B;

import java.util.*;

public class ZSort_MySolution {
    public static void main(String[] args) {
        Scanner scannerTerminal = new Scanner(System.in);
        int numberElements = Integer.parseInt(scannerTerminal.nextLine());
        int[] elements = new int[numberElements];
        for (int index = 0; index < numberElements; index++)
            elements[index] = scannerTerminal.nextInt();
        Arrays.sort(elements);
        int head = 0, tail = numberElements - 1;
        while (head <= tail)
            System.out.print((head != tail) ? elements[head++] + " " + elements[tail--] + " " : elements[head++]);
    }
}
