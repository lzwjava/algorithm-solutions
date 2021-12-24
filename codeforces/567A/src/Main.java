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

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        int[] x = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE;
            if (i - 1 >= 0) {
                int d = x[i] - x[i - 1];
                if (d < min) {
                    min = d;
                }
            }
            if (i + 1 < n) {
                int d = x[i + 1] - x[i];
                if (d < min) {
                    min = d;
                }
            }
            int max = -1;
            if (i != 0) {
                int d = x[i] - x[0];
                if (d > max) {
                    max = d;
                }
            }
            if (i != n - 1) {
                int d = x[n - 1] - x[i];
                if (d > max) {
                    max = d;
                }
            }
            out.append(String.format("%d %d\n", min, max));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}