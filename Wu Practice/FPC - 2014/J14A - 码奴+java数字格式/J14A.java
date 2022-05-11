import java.text.DecimalFormat;
import java.util.*;

public class J14A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine()); // number of events
        // 0.23 EUR per day
        // 10.00 EUR for not return
        DecimalFormat df = new DecimalFormat("0.00");
        HashSet<String> memberNames = new HashSet<>();
        HashSet<String> borrowedBooks = new HashSet<>(); // check double loan and return
        ArrayList<Member> members = new ArrayList<>();
        Stack<String> returnedBooks = new Stack<>(); // the employee picks from here
        while (n-- > 0) {
            String[] info = sc.nextLine().replace("- ","").split(" ");
            // 5 3 books become available
            // 0 1  2      3        4
            if (info.length == 5 && info[4].equals("available")) {
                int amount = Integer.parseInt(info[1]);
                if (returnedBooks.size() < amount) {
                    System.out.println("CORRUPT");
                    System.exit(0);
                }
                while (amount -- > 0) {
                    String avai = returnedBooks.pop();
                    borrowedBooks.remove(avai);
                }
                continue;
            }
            if (!memberNames.contains(info[1])) {
                Member newMember = new Member(info[1], new HashMap<String, Integer>(), 0);
                memberNames.add(info[1]);
                members.add(newMember);
            }
            // 1 Sophie borrows Romeo_and_Juliet
            // 0    1      2           3
            Member currentMember = getMember(info[1],members);
            String action = info[2];
            switch(action) {
                case "borrows" -> {
                    String toBorrow = info[3];
                    if (borrowedBooks.contains(toBorrow)) {
                        System.out.println("CORRUPT");
                        System.exit(0);
                    }
                    borrowedBooks.add(toBorrow); // add to all books borrowed
                    currentMember.loaned.put(toBorrow,Integer.parseInt(info[0]));
                }
                case "returns" -> {
                    String toReturn = info[3];
                    if (!borrowedBooks.contains(toReturn) || currentMember.loaned.get(toReturn) == null) { // nothing to return
                        System.out.println("CORRUPT");
                        System.exit(0);
                    }
                    // not free the book here
                    // wait employee
                    returnedBooks.push(toReturn);
                    int days = Integer.parseInt(info[0]) - currentMember.loaned.get(toReturn) -3;
                    days = days >= 0 ? days : 0;
                    double fee = days * 0.23;
                    currentMember.toPay += fee;
                    currentMember.loaned.remove(toReturn);
                }
            }
        }
        List<String> output = new ArrayList<>();
        for (Member m : members) {
            m.toPay += 10d * m.loaned.keySet().size();
            String ans = m.name + " E" + df.format(round2D(m.toPay));
            output.add(ans);
        }
        Collections.sort(output, String.CASE_INSENSITIVE_ORDER);
        for (String s : output) {
            System.out.println(s);
        }
    }

    public static Member getMember(String name, ArrayList<Member> members) {
        for (Member m : members) {
            if(m.name.equals(name)) return m;
        }
        return null;
    }

    public static double round2D(double d) {
        return Math.round(d*100)/100d;
    }
}

class Member {
    public String name;
    public HashMap<String, Integer> loaned;
    public double toPay;

    public Member(String name,HashMap<String, Integer> loaned, double toPay){
        this.name = name;
        this.loaned = loaned;
        this.toPay = toPay;
    }
}