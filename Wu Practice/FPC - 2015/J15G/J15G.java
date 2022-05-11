import java.util.Scanner;

// elevator simplified version
// no intersecting segments
// key(#...#)
// lock(#...#)
// key segment must come before the lock segment
// otherwise it would not go inside

public class J15G {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of locks
        int w = sc.nextInt(); // width
        int h = sc.nextInt(); //
        sc.nextLine(); // skip the new line character after nextInt
        String[][] locks = new String[n][h]; // store the locks
        for (int i = 0; i < n; i++){
            for (int j = 0; j < h; j++) {
                locks[i][j] = sc.nextLine();
                int first = Integer.MAX_VALUE;
                for (int k = 0; k < w ; k++) {
                    if (locks[i][j].charAt(k) == '#') {
                        first = k;
                        break;
                    }
                }
                locks[i][j] = Integer.toString(first);
            }
        }
        String[] key = new String[h];
        int[] keyEnd = new int[h];
        // keyEnd[0][0] = last index of # of first line
        for (int i = 0; i < h; i++) {
            key[i] = sc.nextLine();
            int last = -1;
            for (int j = w-1; j >= 0 ;j--){
                if (key[i].charAt(j) == '#') {
                    last = j;
                    break;
                }
            }
            keyEnd[i] = last;
        }
        int ans = 0;
        for (int i = 0; i<n; i++) {
            boolean flag = true;
            for (int j = 0; j<h ;j++) {
                if (!(keyEnd[j] < Integer.parseInt(locks[i][j]))) {
                    flag = !flag;
                    break;
                }
            }
            if (flag) ans++;
        }
        System.out.println(ans);

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < h ; j++) {
//                System.out.println(locks[i][j]);
//            }
//        }
//        for (int i = 0; i < h; i++) {
//            System.out.println(keyEnd[i]);
//        }
    }
}
