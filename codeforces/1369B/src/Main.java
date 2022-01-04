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

    void dp1(String s) {
        int n = s.length();
        boolean found = false;
        for (int i = n - 2; i >= 0; i--) {
            if (s.charAt(i) == '1' && s.charAt(i + 1) == '0') {
                int left1 = 0;
                for (int j = 0; j < i; j++) {
                    if (s.charAt(j) == '1') {
                        left1++;
                    }
                }
                int right0 = 0;
                for (int j = i + 2; j < n; j++) {
                    if (s.charAt(j) == '0') {
                        right0++;
                    }
                }
                if (left1 < right0) {
                    // remove 0
                    String ns1 = s.substring(0, i + 1) + s.substring(i + 2, n);
                    dp(ns1);
                } else {
                    // remove 1
                    String ns2 = s.substring(0, i) + s.substring(i + 1, n);
                    dp(ns2);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            ans = s;
        }
    }

    String cal1(String s) {
        ans = s;
        dp1(s);
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