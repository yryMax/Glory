package AtCoder.ABC.ABC194.ProblemE;

import java.util.Scanner;

public class MexMin {
    static int M;
    static int N;
    static int[] numbers;
    static int[] count;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        M = in.nextInt();
        numbers = new int[N + 1];
        count = new int[N + 1];
        for (int i = 1; i <= N; i++)
            numbers[i] = in.nextInt();
        for (int i = 1; i <= M; i++)
            count[numbers[i]]++;
        int targetNum = -1;
        for (int i = 0; i <= N; i++)
            if (count[i] == 0) {
                targetNum = i;
                break;
            }
        int ans = targetNum;
        for (int offset = 1; offset <= N - M; offset++) {
            int thisNum = numbers[M + offset];
            count[thisNum]++;
            int lastNum = numbers[offset];
            count[lastNum]--;
            if (count[lastNum] == 0 && lastNum < targetNum) {
                targetNum = lastNum;
            } else if (thisNum == targetNum) {
                for (int i = thisNum; i <= N; i++)
                    if (count[i] == 0) {
                        targetNum = i;
                        break;
                    }
            }
            ans = Math.min(ans, targetNum);
        }
        System.out.println(ans);
    }
}
