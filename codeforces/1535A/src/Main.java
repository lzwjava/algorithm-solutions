import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int[] s = new int[4];
            for (int i = 0; i < 4; i++) {
                s[i] = in.nextInt();
            }
            int[] a = s.clone();
            Arrays.sort(a);
            int b, c;
            b = Integer.max(s[0], s[1]);
            c = Integer.max(s[2], s[3]);
            if (b + c == a[2] + a[3]) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
