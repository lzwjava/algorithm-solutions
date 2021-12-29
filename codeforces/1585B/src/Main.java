import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            List<Integer> a = new ArrayList<>();
            int mx = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                a.add(v);
                mx = Integer.max(mx, v);
            }
            int p = n - 1;
            int c = 0;
            while (true) {
                int x = a.get(p);
                if (x == mx) {
                    break;
                }
                int i;
                for (i = p - 1; i >= 0; i--) {
                    if (a.get(i) > x) {
                        break;
                    }
                }
                c++;
                p = i;
            }
            System.out.println(c);
        }
    }

}