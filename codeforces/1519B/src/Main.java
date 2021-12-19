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


    void dp(int x, int y, int cost) {
        if (found != -1) {
            return;
        }
        if (cost < 0) {
            return;
        }
        if (x == n && y == m) {
            found = cost == 0 ? 1 : 0;
            return;
        }
        if (x + 1 <= n) {
            dp(x + 1, y, cost - y);
        }
        if (y + 1 <= m) {
            dp(x, y + 1, cost - x);
        }
    }

    int n, m, k;
    int found;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            found = -1;
            dp(1, 1, k);
            if (found == 1) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
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