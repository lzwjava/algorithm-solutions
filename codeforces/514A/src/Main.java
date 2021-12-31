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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        long x = Long.parseLong(in.readLine());
        String s = String.format("%d", x);
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (char c : s.toCharArray()) {
            int v = c - '0';
            int nv;
            if (v >= 5) {
                nv = 9 - v;
            } else {
                nv = v;
            }
            if (first && nv == 0) {
                nv = v;
            }
            first = false;
            sb.append(String.valueOf(nv));
        }
        String fs = sb.toString();
        long ans = Long.parseLong(fs);
        out.append(String.format("%d\n", ans));
    }
}