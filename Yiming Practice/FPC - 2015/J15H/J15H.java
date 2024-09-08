import java.util.Scanner;

public class J15H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] infos = sc.nextLine().split(" ");
        int ansp2;
        if (infos[0].equals("?") || infos[1].equals("?")) {
            // calculate one of a and b
            if (infos[0].equals("?")) {
                int b = Integer.parseInt(infos[1]);
                int c = Integer.parseInt(infos[2]);
                ansp2 = c*c - b*b;
            } else {
                int a = Integer.parseInt(infos[0]);
                int c = Integer.parseInt(infos[2]);
                ansp2 = c*c - a*a;
            }
        } else {
            int a = Integer.parseInt(infos[0]);
            int b = Integer.parseInt(infos[1]);
            ansp2 = a*a + b*b;
        }
        // now simplify it
        int pro = 1;
        boolean loop = true;
        while (loop) {
            for ( int i = (int) Math.ceil(Math.sqrt(ansp2));i >= 1; i--) {
                if (i == 1) {
                    loop = false;
                    break;
                }
                if (ansp2 % (i*i) == 0) {
                    pro *= i;
                    ansp2 /= (i*i);
                }
            }
        }
        if (ansp2 == 1) {
            System.out.println(pro);
        } else {
            if (pro != 1) {
                System.out.print(pro + " \\cdot ");
            }
            System.out.println("\\sqrt{" + ansp2 + "}");
        }
    }

}
