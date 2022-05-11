package AtCoder.ABC153D;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CaracalVSMonster {

    private static long dfs(long current) {
        if (current == 0) return 0;
        else return 2 * dfs(current / 2) + 1;
    }


    public static void main(String[] args) {
        long H = new Scanner(System.in).nextLong();
        System.out.println(dfs(H));
    }
}
