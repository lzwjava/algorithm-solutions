import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        out = new PrintWriter(System.out);
    }

    void close() {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.close();
    }

    boolean check(int[] h, int n, int x) {
        int[] gives = new int[n];
        for (int i = n - 1; i >= 2; i--) {
            if (h[i] + gives[i] < x) {
                return false;
            }
            int m = h[i] + gives[i] - x;
            if (m >= h[i]) {
                m = h[i];
            }
            int d = m / 3;

            h[i] -= 3 * d;
            gives[i - 1] += d;
            gives[i - 2] += 2 * d;
        }
        for (int i = 0; i < 2; i++) {
            if (h[i] + gives[i] < x) {
                return false;
            }
        }
        return true;
    }

    void solve() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int[] h = new int[n];
            for (int i = 0; i < n; i++) {
                h[i] = in.nextInt();
            }
            int left = Integer.MAX_VALUE, right = 0;
            for (int i = 0; i < n; i++) {
                if (h[i] < left) {
                    left = h[i];
                }
                if (h[i] > right) {
                    right = h[i];
                }
            }
            while (left + 1 < right) {
                int mid = (left + right) / 2;
                int[] th = h.clone();
                if (check(th, n, mid)) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            if (check(h, n, right)) {
                out.append(String.format("%d\n", right));
            } else {
                out.append(String.format("%d\n", left));
            }

        }
    }

}