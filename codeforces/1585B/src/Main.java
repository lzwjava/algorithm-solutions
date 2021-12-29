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
            int c = 0;
            while (true) {
                int x = a.get(n - 1);
                if (x == mx) {
                    break;
                }
                List<Integer> left = new ArrayList<>();
                List<Integer> right = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    int v = a.get(i);
                    if (v <= x) {
                        left.add(v);
                    } else {
                        right.add(v);
                    }
                }
                left.addAll(right);
                a = left;
                c++;
            }
            System.out.println(c);
        }
    }

}