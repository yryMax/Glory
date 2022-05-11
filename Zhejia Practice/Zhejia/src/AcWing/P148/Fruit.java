package AcWing.P148;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Fruit {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numFruit = in.nextInt();
        PriorityQueue<Integer> fruits = new PriorityQueue<>(numFruit);
        for (int i = 0; i < numFruit; i++) fruits.add(in.nextInt());
        if (numFruit == 1) {
            out.println(0);
            out.flush();
            return;
        }
        long result = 0;
        assert fruits.size() >= 2;
        while (fruits.size() > 1) {
            int weight1 = fruits.poll();
            int weight2 = fruits.poll();
            result += (weight1 + weight2);
            fruits.add(weight1 + weight2);
        }
        out.println(result);
        out.flush();
    }
}
