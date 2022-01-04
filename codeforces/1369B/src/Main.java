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

    boolean better(String a, String b) {
        int an = a.length();
        int bn = b.length();
        if (an < bn) {
            return true;
        } else if (an > bn) {
            return false;
        } else {
            for (int i = 0; i < an; i++) {
                char ca = a.charAt(i);
                char cb = b.charAt(i);
                if (ca != cb) {
                    return ca < cb;
                }
            }
            return false;
        }
    }

    String ans;

    void dp(String s) {
        if (better(s, ans)) {
            ans = s;
        }
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            char si = s.charAt(i);
            char sj = s.charAt(i + 1);
            if (si == '1' && sj == '0') {
                // remove 0
                String ns1 = s.substring(0, i + 1) + s.substring(i + 2, n);
                dp(ns1);
                // remove 1
                String ns2 = s.substring(0, i) + s.substring(i + 1, n);
                dp(ns2);
            }
        }
    }

    String cal(String s) {
        ans = s;
        dp(s);
        return ans;
    }

    void dp1(String s, int i, int left1, int right0) {
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
            dp1(s, i - 1, nleft1, nright0);
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
            dp1(ns, ni, nleft1, nright0);
        }
    }

    String cal1(String s) {
        ans = s;
        int n = s.length();
        int left1 = 0;
        for (int j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                left1++;
            }
        }
        int right0 = 0;
        dp1(s, s.length() - 1, left1, right0);
        return ans;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            ans = cal1(s);
            out.append(String.format("%s\n", ans));
        }
    }
}