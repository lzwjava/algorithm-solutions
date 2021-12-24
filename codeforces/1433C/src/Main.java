import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    boolean allEqual(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            if (a[i] != a[0]) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            boolean eq = allEqual(a);
            if (eq) {
                out.append("-1\n");
            } else {
                int i;
                for (i = 0; i < n; i++) {
                    if (i - 1 >= 0 && a[i - 1] < a[i]) {
                        break;
                    }
                    if (i + 1 < n && a[i + 1] < a[i]) {
                        break;
                    }
                }
                out.append(String.format("%d\n", i));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}