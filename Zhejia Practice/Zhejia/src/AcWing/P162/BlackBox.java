package AcWing.P162;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BlackBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sequenceLength = scanner.nextInt();
        int uSeqLength = scanner.nextInt();
        int[] sequence = new int[sequenceLength + 1];
        int[] uSequence = new int[uSeqLength + 1];

        for (int i = 1; i <= sequenceLength; i++) sequence[i] = scanner.nextInt();
        for (int i = 1; i <= uSeqLength; i++) uSequence[i] = scanner.nextInt();
        PriorityQueue<Integer> maxes = new PriorityQueue<>(Comparator.comparingInt(num -> -num));
        PriorityQueue<Integer> mines = new PriorityQueue<>(Comparator.comparingInt(num -> num));

        int over = 0;
        for (int time = 1; time <= uSeqLength; time++) {
            int temp = over;
            for (int index = temp + 1; index <= uSequence[time]; index++) {
                if (maxes.size() < time) {
                    if (mines.isEmpty()) maxes.add(sequence[index]);
                    else if (mines.peek() < sequence[index]) {
                        maxes.add(mines.poll());
                        mines.add(sequence[index]);
                    } else maxes.add(sequence[index]);
                }
                else if (sequence[index] < maxes.peek()) {
                    mines.add(maxes.poll());
                    maxes.add(sequence[index]);
                } else mines.add(sequence[index]);
                temp = index;
            }
            over = temp;
            if (maxes.size() < time) {
                int remaining = time - maxes.size();
                for (int i = 1; i <= remaining; i++) maxes.add(mines.poll());
            }
            System.out.println(maxes.peek());
        }

    }
}
