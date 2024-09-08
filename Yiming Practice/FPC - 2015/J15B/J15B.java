import java.util.HashMap;
import java.util.Scanner;

public class J15B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eng = sc.nextLine().replaceAll("[^a-zA-Z ]","").strip().toLowerCase();
        String dut = sc.nextLine().replaceAll("[^a-zA-Z ]","").strip().toLowerCase();
        String dutd = sc.nextLine();
        String engd = sc.nextLine();
        String[] engArr = eng.split(" ");
        String[] dutArr = dut.split(" ");

        if (engArr.length != dutArr.length) {
            System.out.println("VALID");
            System.exit(0);
        }
        String[] dutDArr = dutd.split(" ");
        String[] engDArr = engd.split(" ");
        HashMap<String, String> dict = new HashMap<>();
        for (int i = 0; i < dutDArr.length; i++) {
            dict.put(engDArr[i], dutDArr[i]);
        }
        StringBuilder sb = new StringBuilder();
//        System.out.println(engArr[0]);
//        System.out.println(dict.get(engArr[0]));
        for (int i = 0; i < engArr.length; i++) {
            if (i == engArr.length - 1) {
                sb.append(dict.get(engArr[i]));
            } else {
                sb.append(dict.get(engArr[i]) + " ");
            }
        }

        if (sb.toString().equals(dut)) {
            System.out.println("STONECOAL");
        } else {
            System.out.println("VALID");
        }
    }
}
