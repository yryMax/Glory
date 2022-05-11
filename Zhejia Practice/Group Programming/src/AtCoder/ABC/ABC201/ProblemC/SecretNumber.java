package AtCoder.ABC.ABC201.ProblemC;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SecretNumber {
    static char[] facts;
    static Set<Integer> mustHave = new HashSet<>();
    static Set<Integer> couldHave = new HashSet<>();

    public static void main(String[] args) {
        facts = new Scanner(System.in).nextLine().toCharArray();
        for (int i = 0; i < 10; i++)
            if (facts[i] == 'o') mustHave.add(i);
            else if (facts[i] == '?') couldHave.add(i);
        long cnt = 0;
        if (mustHave.size() > 4) {
            System.out.println(0);
            return;
        }
        for (int d1 = 0; d1 <= 9; d1++)
            for (int d2 = 0; d2 <= 9; d2++)
                for (int d3 = 0; d3 <= 9; d3++)
                    Next:
                    for (int d4 = 0; d4 <= 9; d4++) {
                        if (!mustHave.contains(d1) && !couldHave.contains(d1) ||
                            !mustHave.contains(d2) && !couldHave.contains(d2) ||
                            !mustHave.contains(d3) && !couldHave.contains(d3) ||
                            !mustHave.contains(d4) && !couldHave.contains(d4)) continue;
                        for (Integer digit : mustHave) {
                            if (digit != d1 && digit != d2 && digit != d3 && digit != d4) continue Next;
                        }

                        cnt++;
                    }
        System.out.println(cnt);

    }
}
