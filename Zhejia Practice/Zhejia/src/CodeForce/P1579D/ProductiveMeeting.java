package CodeForce.P1579D;

import java.util.*;

public class ProductiveMeeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numTest = scanner.nextInt();
        while (numTest-- > 0) {
            int num = scanner.nextInt();
            Integer[][] persons = new Integer[num][2];
            PriorityQueue<Integer[]> socialKing = new PriorityQueue<>(new Comparator<Integer[]>() {
                @Override
                public int compare(Integer[] arr1, Integer[] arr2) {
                    return -arr1[0] + arr2[0];
                }
            });
            
            for (int i = 0; i < num; i++) {
                persons[i][0] = scanner.nextInt();
                persons[i][1] = i + 1;
                if (persons[i][0] != 0)
                    socialKing.add(persons[i]);
            }
            long result = 0;
            List<Integer[]> results = new ArrayList<>();
            while (socialKing.size() > 1) {
                Integer[] person1 = socialKing.poll();
                Integer[] person2 = socialKing.poll();
                assert person2 != null;
                results.add(new Integer[] {Math.min(person1[1], person2[1]), Math.max(person1[1], person2[1])});
                result += 1;
                person1[0] -= 1;
                person2[0] -= 1;
                if (person1[0] > 0) socialKing.add(person1);
                if (person2[0] > 0) socialKing.add(person2);
            }
            System.out.println(result);
            for (Integer[] out: results)
                System.out.println(out[0] + " " + out[1]);

        }
    }
}
