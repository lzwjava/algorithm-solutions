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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n, a, b, c, d;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            int p1 = (a - b) * n;
            int p2 = (a + b) * n;
            int m1 = c - d;
            int m2 = c + d;
            boolean ok;
            if ((m1 <= p2 && m2 >= p2) || (m1 <= p1 && m2 >= p1) || (m1 >= p1 && m2 <= p2) || (m1 <= p1 && m2 >= p2)) {
                ok = true;
            } else {
                ok = false;
            }
            if (ok) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}