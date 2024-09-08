// slave coding
import java.util.Scanner;

public class J14E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = sc.nextInt();
        int h = sc.nextInt();
        sc.nextLine();
        char[][] mb = new char[h][w];
        for (int i = 0; i < h; i++) {
            mb[i] = sc.nextLine().toCharArray();
        }
        int ww = sc.nextInt();
        int hh = sc.nextInt();
        sc.nextLine();
        char[][] cpu = new char[hh][ww];
        for (int i = 0; i < hh; i++) {
            cpu[i] = sc.nextLine().toCharArray();
        }
        int counter = 0;
        if (ww > w || hh > h) {
            System.out.println(0);
        } else {
            for (int i = 0; i <= w-ww; i++) {
                for (int j = 0; j <= h-hh; j++) {
                    boolean check = true;
                    for (int k = 0;check && k < ww; k++) {
                        for (int l = 0;check && l < hh; l++) {
                            if (cpu[l][k] != '*' && cpu[l][k] != mb[l+j][k+i]) {
                                check = !check;
                            }
                        }
                    }
                    if (check) counter++;
                }
            }
        }
        System.out.println(counter);
    }
}
