package CodeForce.P1619D;

import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NewYearProblem {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int numTest = in.nextInt();
        Main:
        while (numTest-- > 0) {
            int numShop = in.nextInt();
            int numFriend = in.nextInt();
            PriorityQueue<Happiness> happinesses = new PriorityQueue<>();
            for (int i = 0; i < numShop; i++) {
                for (int j = 0; j < numFriend; j++) {
                    happinesses.add(new Happiness(in.nextInt(), i, j));
                }
            }
            boolean twiceVisit = false;
            int numServed = 0;
            boolean[] served = new boolean[numFriend];
            int[] visited = new int[numShop];
            Happiness happiness = null;
            while (!twiceVisit || !(numServed == numFriend)) {
                happiness = happinesses.poll();
                if (++visited[happiness.shop] == 2) twiceVisit = true;
                if (!served[happiness.friend]) {
                    served[happiness.friend] = true;
                    numServed++;
                }
            }
            out.println(happiness.value);
            out.flush();

        }
    }
}

class Happiness implements Comparable<Happiness> {
    int value;
    int shop;
    int friend;

    public Happiness(int value, int shop, int friend) {
        this.value = value;
        this.shop = shop;
        this.friend = friend;
    }

    @Override
    public int compareTo(Happiness happiness) {
        return -value + happiness.value;
    }
}


