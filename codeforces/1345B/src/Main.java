import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            //2,5,8,11
            int m = 25820;
            int[] a = new int[m];
            a[0] = 2;
            for (int i = 1; i < m; i++) {
                a[i] = a[i - 1] + 3;
            }
            int[] s = new int[m];
            int p = 0;
            for (int i = 0; i < m; i++) {
                p += a[i];
                s[i] = p;
            }
            int idx = Arrays.binarySearch(s, n);
            if (idx < 0) {
                idx = -(idx + 1);
            }
            int c = 0;
            while (n >= 2) {
                while (s[idx] > n) {
                    idx--;
                }
                while (s[idx] <= n) {
                    n -= s[idx];
                    c++;
                }
            }
            out.append(String.format("%d\n", c));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}