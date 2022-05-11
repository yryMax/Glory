package CodeForce.P1611F;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ATMAndStudents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int numStudent = scanner.nextInt();
            int initial = scanner.nextInt();
            int[] students = new int[numStudent + 1];
            int first = 0;
            for (int i = 1; i <= numStudent; i++) {
                students[i] = scanner.nextInt();
                if (first == 0 && students[i] >= -initial) first = i;
            }
            if (first == 0) {
                System.out.println(-1);
                continue;
            }
            int[] maxIndex = new int[]{first, first};
            int maxLength = 1;
            long sum = students[first];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(first);
            for (int i = first + 1; i <= numStudent; i++) {
                queue.offer(i);
                sum += students[i];
                if (sum >= -initial) {
                    if (i - queue.peek() + 1 > maxLength) {
                        maxLength = i - queue.peek() + 1;
                        maxIndex = new int[]{queue.peek(), i};
                    }
                } else {
                    while (queue.peek() != null && sum < -initial) {
                        sum -= students[queue.peek()];
                        queue.poll();
                    }
                }
            }
            System.out.println(maxIndex[0] + " " + maxIndex[1]);
        }
    }

}
