import java.io.PrintWriter;
import java.util.*;

public class J21F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l =  Integer.parseInt(sc.nextLine()); // number
        String[] cs1 = new String[l];
        String[] cs2 = new String[l];
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> mapTwo = new HashMap<>();
        HashMap<String, String> diff = new HashMap<>();


        for (int i = 0; i < l; i++) {
            cs1[i] = sc.nextLine().strip().replaceAll("( )+"," ");
        }
        for (int i = 0; i < l; i++) {
            cs2[i] = sc.nextLine().strip().replaceAll("( )+"," ");
        }
        for (int i = 0; i < l; i++) {
            String[] line1 = cs1[i].strip().split(" ");
            String[] line2 = cs2[i].strip().split(" ");
            if (line1.length != line2.length) {
                System.out.println(-1);
                System.exit(0);
            }
            int len = line1.length;
            for (int j = 0; j < len; j++) {
                if (map.containsKey(line1[j])) {
                    String correct = map.get(line1[j]);
                    if (!line2[j].equals(correct)) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                } else if (mapTwo.containsKey(line2[j])) {
                    String correct2 = mapTwo.get(line2[j]);
                    if (!line1[j].equals(correct2)) {
                        System.out.println(-1);
                        System.exit(0);
                    }
                } else {
                    if (!line1[j].equals(line2[j])) diff.put(line1[j],line2[j]);
                    map.put(line1[j], line2[j]);
                    mapTwo.put(line2[j], line1[j]);
                }
            }
        }

        PrintWriter pw = new PrintWriter(System.out);
        ArrayList<String> out = new ArrayList<>();
        for (String s : diff.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(s + " " + diff.get(s));
            out.add(sb.toString());
        }
        Collections.sort(out,new sComparator());
        pw.println(out.size());
        for (int i = 0; i < out.size(); i++) {
            pw.println(out.get(i));
        }
        pw.close();
    }
}


class sComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }

}