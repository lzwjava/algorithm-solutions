import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int[] p = new int[3];
            p[0] = in.nextInt();
            p[1] = in.nextInt();
            p[2] = in.nextInt();
            Arrays.sort(p);
            int a = 0, b = 0, c = 0;
            boolean ok = true;
            if (p[1] == p[2]) {
                c = p[2];
                b = p[0];
                a = b;
            } else {
                ok = false;
            }
            if (ok) {
                System.out.println("YES");
                System.out.println(String.format("%d %d %d", a, b, c));
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
