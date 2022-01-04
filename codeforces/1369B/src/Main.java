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

    String ans;

    void dp(String s, int i, int left1, int right0) {
        int n = s.length();
        if (i < 0) {
            ans = s;
            return;
        }
        if (i == n - 1 || s.charAt(i) == '0' || (s.charAt(i) == '1' && s.charAt(i + 1) == '1')) {
            char c = s.charAt(i);
            int nleft1 = left1, nright0 = right0;
            if (c == '1') {
                nleft1--;
            } else {
                nright0++;
            }
            dp(s, i - 1, nleft1, nright0);
        } else {
            // 1, 0
            int sleft1 = left1 - 1;
            int sright0 = right0 - 1;
            String ns;
            int nleft1 = left1, nright0 = right0;
            int ni;
            if (sleft1 < sright0) {
                // remove 0
                nright0--;
                ns = s.substring(0, i + 1) + s.substring(i + 2, n);
                ni = i;
            } else {
                // remove 1
                nleft1--;
                ns = s.substring(0, i) + s.substring(i + 1, n);
                ni = i - 1;
            }
            dp(ns, ni, nleft1, nright0);
        }
    }

    String cal(String s) {
        ans = s;
        int n = s.length();
        int left1 = 0;
        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                left1++;
            }
        }
        int right0 = 0;
        dp(s, s.length() - 1, left1, right0);
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            ans = cal(s);
            out.append(String.format("%s\n", ans));
        }
    }
}