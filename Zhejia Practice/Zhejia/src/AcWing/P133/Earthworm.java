package AcWing.P133;

import java.util.*;

public class Earthworm {
    static Comparator<Integer> reversedInteger = (Integer num1, Integer num2) -> - Integer.compare(num1, num2);
    static PriorityQueue<Integer> totalLength = new PriorityQueue<>(reversedInteger);
    static PriorityQueue<Integer> left = new PriorityQueue<>(reversedInteger);
    static PriorityQueue<Integer> right = new PriorityQueue<>(reversedInteger);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberWorm = scanner.nextInt();
        int timeRescue = scanner.nextInt();
        int delta = scanner.nextInt();
        int dividend = scanner.nextInt();
        int divisor = scanner.nextInt();
        int interval = scanner.nextInt();

        int[] worms = new int[numberWorm];
        for (int i = 0; i < numberWorm; i++) worms[i] = scanner.nextInt();
        for (int i = 0; i < numberWorm; i++) totalLength.add(worms[i]);

        StringBuilder result1 = new StringBuilder();
        if (timeRescue == 0)  {
            for (int i = 0; i < numberWorm; i++) result1.append(totalLength.poll()).append(" ");
            System.out.println();
            System.out.println(result1);
            return;
        }

        int countDelta = 0;
        for (int time = 1; time <= timeRescue; time++) {
            int max = getMaxLength();
            max += countDelta;

            int leftPart = (int) Math.floor((long) max * ((double) dividend) / divisor);
            int rightPart = max - leftPart;
            if (time % interval == 0) result1.append(max).append(" ");
            countDelta += delta;
            left.add(leftPart - countDelta);
            right.add(rightPart - countDelta);
        }

        StringBuilder result2 = new StringBuilder();
        for (int time = 1; time <= timeRescue + numberWorm; time++) {
            int max = getMaxLength();
            if (time % interval == 0) result2.append(max + countDelta).append(" ");
        }

        System.out.println(result1);
        System.out.println(result2);
    }

    public static int getMaxLength() {
        int out = Integer.MIN_VALUE;
        if (!totalLength.isEmpty()) out = Math.max(out, totalLength.peek());
        if (!left.isEmpty()) out = Math.max(out, left.peek());
        if (!right.isEmpty()) out = Math.max(out, right.peek());

        if (!totalLength.isEmpty() && out == totalLength.peek()) totalLength.poll();
        else if (!left.isEmpty() && out == left.peek()) left.poll();
        else if (!right.isEmpty() && out == right.peek()) right.poll();

        return out;
    }
}
