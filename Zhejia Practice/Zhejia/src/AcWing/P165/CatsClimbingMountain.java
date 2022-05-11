package AcWing.P165;

import java.io.PrintWriter;
import java.util.*;

public class CatsClimbingMountain {
    static List<Integer> cabs;
    static List<Integer> cats;
    static int numCat;
    static int maxWeight;
    static int optimal = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        numCat = in.nextInt();
        maxWeight = in.nextInt();
        cats = new ArrayList<>();
        for (int i = 0; i < numCat; i++)
            cats.add(in.nextInt());
        cats.sort(Integer::compare);
        Collections.reverse(cats);

        cabs = new ArrayList<>();

        depthFirstSearch(0, 0);
        out.println(optimal);
        out.flush();

    }

    private static void depthFirstSearch(int now, int cabCount) {
        if (cabCount >= optimal) return;
        if (now == numCat) {
            optimal = Math.min(optimal, cabCount);
            return;
        }

        for (int i = 0; i < cabCount; i++) {
            if (cats.get(now) + cabs.get(i) <= maxWeight) {
                cabs.set(i, cabs.get(i) + cats.get(now));
                depthFirstSearch(now + 1, cabCount);
                cabs.set(i, cabs.get(i) - cats.get(now));
            }
        }

        cabs.add(cats.get(now));
        depthFirstSearch(now + 1, cabCount + 1);
        cabs.remove(cabs.size() - 1);
    }
}
