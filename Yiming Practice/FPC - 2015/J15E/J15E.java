//
//    This problem is as water as myself.
//
import java.util.Scanner;

public class J15E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int weight = sc.nextInt(); // final weight
        int count = sc.nextInt();  // counter for grades and weights input
        double sumg = 0; // total grades
        double sumw = 0; // total weights
        for (int i = 0; i < count; i++) {
            double grade = sc.nextDouble();
            double tmpw = sc.nextDouble();
            if (grade < 5.8) {
                System.out.println("IMPOSSIBLE");
                System.exit(0);
            }
            sumg += grade*tmpw;
            sumw += tmpw;
        }
        double goalSum = 8.0 * (sumw + weight);
        double need = (goalSum - sumg)/weight;
        if ( need > 10) {
            System.out.println("IMPOSSIBLE");
        } else if ( need < 5.8) {
            System.out.println(5.8);
        } else {
            System.out.println(round(need));
        }
//        System.out.println(need);
    }

    public static double round(double d) {
        return Math.ceil(d*10)/10.0;
    }
}
