import java.util.*;

public class J16H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<String> names = new ArrayList<>();
        HashMap<String,Integer> scores = new HashMap<>();
        while (n-- > 0) {
            String[] info = sc.nextLine().split(" ");
            String name = info[0];
            int score = Integer.parseInt(info[1]);
            if (!scores.keySet().contains(name)) {
                names.add(name);
                scores.put(name,score);
            } else {
                int old = scores.get(name);
                scores.put(name, old+score);
            }
        }
        Collections.sort(names, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < names.size(); i++) {
            System.out.println(names.get(i) + " " + scores.get(names.get(i)));
        }
    }
}
