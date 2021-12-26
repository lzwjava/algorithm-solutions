import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    void permutation(String s1, List<String> ps, int cur, int m) {
        if (cur == m) {
            ps.add(s1);
            return;
        }
        permutation(s1 + "0", ps, cur + 1, m);
        permutation(s1 + "1", ps, cur + 1, m);
    }

    int mink;
    String minks;

    boolean subsequence(String a, String b) {
        int an = a.length();
        int bn = b.length();
        int p = 0;
        for (int i = 0; i < bn; i++) {
            while (p < an) {
                
            }
        }
    }

    void cal(char[] chs, String np, boolean[] vis, int i, int m) {
        if (i == m) {
            String ns = new String(chs);
            int k = period(ns);
            if (k < mink) {
                mink = k;
                minks = ns;
            }
            return;
        }
        for (int j = 0; j < m; j++) {
            if (!vis[j]) {
                vis[j] = true;
                chs[i] = np.charAt(j);
                cal(chs, np, vis, i + 1, m);
                vis[j] = false;
            }
        }
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            tt--;
            String t = in.readLine();
            int n = t.length();
            mink = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                List<String> ps = new ArrayList<>();
                permutation("", ps, 0, i);
                for (String p : ps) {
                    String np = p + t;
                    int m = np.length();

                    char[] chs = new char[m];
                    boolean[] vis = new boolean[m];
                    cal(chs, np, vis, 0, m);
                }
            }
            out.append(String.format("%s\n", minks));
        }
    }

}