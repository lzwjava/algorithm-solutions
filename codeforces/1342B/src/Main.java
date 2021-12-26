import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int period(String s) {
        int n = s.length();
        for (int k = 1; k <= n; k++) {
            boolean ok = true;
            for (int i = 0; i < n - k; i++) {
                if (s.charAt(i) != s.charAt(i + k)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return k;
            }
        }
        return -1;
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            tt--;
            String t = in.readLine();
            int p = period(t);
            out.append(String.format("%d\n", p));
        }
    }

}