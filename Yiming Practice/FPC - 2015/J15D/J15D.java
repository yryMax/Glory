//
// Understanding the question is much harder than solving the question.
// StackOverflow told me wrong code, which took me 2 hour to debug my code. fuck
//
import java.text.DecimalFormat;
import java.util.Scanner;

public class J15D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double[] boxes = new double[n];
        for (int i = 0; i < n; i++) {
            boxes[i] = sc.nextDouble();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        int choosed = sc.nextInt()-1;
        int operations = sc.nextInt();
        double[] result = new double[2];
//        boolean BD = false;
        result[0] = boxes[choosed];
        result[1] = -1;
        sc.nextLine();
        while(operations-- > 0) {
            String line = sc.nextLine();
            if (line.charAt(0) == 'O') {
                String[] s = line.split(" ");
                int index = Integer.parseInt(s[1]) - 1;
//                if (index == choosed) continue;
                boxes[index] = -1;
            } else {
                char tmp = line.charAt(2);
                if (tmp == 'D') {
                    int counter = 0; // total boxes
                    double total = 0; // total remaining money
                    for(int i = 0;i<boxes.length;i++) {
                        if (boxes[i] != -1 && i != choosed) {
                            counter++;
                            total += boxes[i];
                        }
                    }
                    double avg = 0.9 * (total/(counter));
                    result[1] = avg;
                    break;
                } else {
                    continue;
                }
            }
        }

        result[0] = round2D(result[0]);
        System.out.println(df.format(result[0]));
        if (result[1] == -1) {
            result[1] = result[0];
        }
        result[1] = round2D(result[1]);
        System.out.println(df.format(result[1]));
    }
    public static double round2D(double a){
        return Math.round(a*100)/100d;
    }
}
