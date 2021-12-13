import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] b = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b);
        long v;
        if (b[0] != b[n - 1]) {
            int m1 = 0, m2 = 0;
            for (int i = 0; i < n; i++) {
                if (b[i] == b[0]) {
                    m1++;
                } else {
                    break;
                }
            }
            for (int i = n - 1; i >= 0; i--) {
                if (b[i] == b[n - 1]) {
                    m2++;
                } else {
                    break;
                }
            }
            v = (long) m1 * m2;
        } else {
            v = (long) n * (n - 1) / 2;
        }
        out.append(String.format("%d %d\n", b[n - 1] - b[0], v));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}