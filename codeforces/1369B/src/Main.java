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

    void dp(StringBuilder s, int i, int left1, int right0) {
        while (true) {
            int n = s.length();
            if (i < 0) {
                ans = s.toString();
                return;
            }
            if (i == n - 1 || s.charAt(i) == '0' || (s.charAt(i) == '1' && s.charAt(i + 1) == '1')) {
                char c = s.charAt(i);
                if (c == '1') {
                    left1--;
                } else {
                    right0++;
                }
                i--;
            } else {
                // 1, 0
                int sleft1 = left1 - 1;
                int sright0 = right0 - 1;
                if (sleft1 < sright0) {
                    // remove 0
                    right0--;
                    s.deleteCharAt(i + 1);
                } else {
                    // remove 1
                    left1--;
                    s.deleteCharAt(i);
                    i--;
                }
            }
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
        StringBuilder sb = new StringBuilder(s);
        dp(sb, s.length() - 1, left1, right0);
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