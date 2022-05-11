package AcWing.P106;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RunningMedian {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberData = scanner.nextInt();
        while (numberData-- > 0) {
            int serial = scanner.nextInt();
            int number = scanner.nextInt();
            System.out.println(serial + " " + (number + 1) / 2);
            PriorityQueue<Integer> maxes = new PriorityQueue<>(Comparator.comparingLong(num-> -num));
            PriorityQueue<Integer> mines = new PriorityQueue<>();
            long count = 0;
            for (int i = 0; i < number; i++) {
                int num = scanner.nextInt();
                if (i % 2 == 0) {
                    if (maxes.isEmpty() || mines.peek() <= num)
                        mines.add(num);
                    else {
                        maxes.add(num);
                        mines.add(maxes.poll());
                    }
                    System.out.print(mines.peek() + " ");
                    count++;
                    if (count % 10 == 0) System.out.print("\n");
                } else {
                    if (num < mines.peek()) maxes.add(num);
                    else {
                        mines.add(num);
                        maxes.add(mines.poll());
                    }
                }
            }
            if (count % 10 != 0) System.out.print("\n");
        }
    }
}


