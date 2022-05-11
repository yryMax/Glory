package AcWing.P129;

import java.util.*;

public class TrainStack_EasyMode {
    public static Deque<Integer> unstacked = new LinkedList<>();
    public static Stack<Integer> stacking = new Stack<>();
    public static int currentTrain = 1;
    public static int count = 20;

    static List<Integer> results = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int trainNumbers = scanner.nextInt();
        findTotalPossibilities(trainNumbers);
    }

    private static void findTotalPossibilities(int trainNumbers) {
        if (count == 0) return;
        if (unstacked.size() == trainNumbers) {
            count--;
            String sum = "";
            for (int num: unstacked) sum += num;
            System.out.println(sum);
        }
        if (!stacking.isEmpty()) {
            unstacked.addLast(stacking.pop());
            findTotalPossibilities(trainNumbers);
            stacking.push(unstacked.pollLast());
        }
        if (currentTrain <= trainNumbers) {
            stacking.push(currentTrain++);
            findTotalPossibilities(trainNumbers);
            currentTrain--;
            stacking.pop();
        }
    }
}
