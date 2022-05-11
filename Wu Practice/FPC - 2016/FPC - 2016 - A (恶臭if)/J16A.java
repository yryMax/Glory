import java.util.Scanner;

public class J16A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = Integer.parseInt(sc.nextLine());
        String[] scores = sc.nextLine().split(" ");
        int[] wins = new int[2]; // 0 = guest 1 = host

        int amount = scores.length;

        for (int i = 0; i < amount; i++) {
            String[] match = scores[i].split("-");
            int left = Integer.parseInt(match[0]);
            int right = Integer.parseInt(match[1]);


            if ((left >= 11 || right >= 11) && i != amount-1) { // at least one winner
                if (left >= 10 && right >= 10) {
                    if (Math.abs(left - right) != 2) {
                        System.out.println("INCONSISTENT");
                        System.exit(0);
                    }
                }
                if (left > right) wins[0]++;
                else wins[1]++;
                continue;
            } else if (i == amount - 1) {
                if (Math.abs(left - right) > 2 && (left >= 11 || right >= 11)) {
                    System.out.println("INCONSISTENT");
                    System.exit(0);
                }
            } else {
                System.out.println("INCONSISTENT");
                System.exit(0);
            }



            // above checks legal or not

            if (wins[0] >= 3 || wins[1] >= 3) {
                System.out.println("INCONSISTENT");
                System.exit(0);
            }
            // below determines the turn
            int turn = left+right;
            if (turn >= 20) { // 1 point switch
                turn -= 20;
                if (i % 2 == 0) { // guest started
                    if (turn % 2 == 0) System.out.println("GUEST");
                    else System.out.println("HOST");
                } else { // host starts
                    if (turn % 2 == 0) System.out.println("HOST");
                    else System.out.println("GUEST");
                }
            } else { // two point switch
                if (i % 2 == 0) {
                    if (turn % 4 == 1 || turn % 4 == 2) System.out.println("GUEST");
                    else System.out.println("HOST");
                } else {
                    if (turn % 4 == 1 || turn % 4 == 2) System.out.println("HOST");
                    else System.out.println("GUEST");
                }
            }


        }

    }
}
