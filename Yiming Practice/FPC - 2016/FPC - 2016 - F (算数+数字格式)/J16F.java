import java.text.DecimalFormat;
import java.util.Scanner;

public class J16F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long in = sc.nextLong();
        DecimalFormat df = new DecimalFormat("0.0");
        double r = Math.sqrt(in/Math.PI);
        double om = 2d*r*Math.PI;

        double ans = round(om);
        if (ans == 0) {
            ans = 0.1d;
        }
        System.out.println(df.format(ans));
    }

    public static double round(double d) {
        return  Math.ceil(d*10d)/10d;
    }


}
