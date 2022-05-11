package AcWing.P131;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class LargestRectangleInAHistogram {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            int numberRectangle = scanner.nextInt();
            if (numberRectangle == 0) break;
            Long[][] heights = new Long[numberRectangle + 1][2];
            heights[0][0] = (long) 0;
            heights[0][1] = (long) 0;
            for (int i = 1; i <= numberRectangle; i++) {
                heights[i][0] = scanner.nextLong();
                heights[i][1] = (long) i;
            }
            System.out.println(findLargestRectangle(heights, numberRectangle));
        }
    }

    private static long findLargestRectangle(Long[][] heights, int numberRectangle) {
        long[] widths = new long[heights.length];
        Arrays.fill(widths, 1);
        Stack<Long[]> stack = new Stack<>();
        long max = 0;
        stack.push(heights[0]);
        for (int i = 1; i <= numberRectangle; i++) {
            int width = 0;
            while (stack.size() > 1 && stack.peek()[0] >= heights[i][0]) {
                Long[] element = stack.pop();
                width += widths[element[1].intValue()];
                max = Math.max(max, element[0] * width);
                widths[i] += widths[element[1].intValue()];
            }
            stack.push(heights[i]);
        }

        widths[0] = 0;
        int width = 0;
        while (stack.size() > 1) {
            Long[] element = stack.pop();
            width += widths[element[1].intValue()];
            max = Math.max(max, element[0] * width);
        }
        return max;
    }
}
