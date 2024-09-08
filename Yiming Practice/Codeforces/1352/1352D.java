import java.io.PrintWriter;
import java.util.Scanner;

//https://codeforces.com/contest/1352/problem/D
public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int cases = sc.nextInt();
        while (cases-- > 0) {
            int n = sc.nextInt();
            int[] size = new int[n];
            for (int i = 0; i < n; i++) {
                size[i] = sc.nextInt();
            }
            int left = 0;
            int right = n;
            int sizeA = 0;
            int sizeB = 0;
            int prev = 0;
            int counter = 0;
            boolean Alice = true;
            while (left < right) {
                int curSize = 0;
                while (Alice && curSize <= prev && left < right || prev == 0) {
                    sizeA += size[left];
                    curSize += size[left++];
                    if (curSize > prev || !(left < right)) {
                        Alice = !Alice;
                        counter++;
                        prev = curSize;
                    }
                }
                curSize = 0;
                while (!Alice && curSize <= prev && left < right) {
                    sizeB += size[right-1];
                    curSize += size[right-1];
                    right--;
                    if (curSize > prev || !(left < right)) {
                        Alice = !Alice;
                        counter++;
                        prev = curSize;
                    }
                }
            }
            pw.print(counter + " " + sizeA + " " + sizeB + "\n");
        }
        pw.close();
    }
}
