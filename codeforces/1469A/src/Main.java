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

    boolean dp(String s, int i, int level) {
        if (i == s.length()) {
            return level == 0;
        }
        char c = s.charAt(i);
        if (c == '(') {
            return dp(s, i + 1, level + 1);
        } else if (c == ')') {
            if (level == 0) {
                return false;
            }
            return dp(s, i + 1, level - 1);
        } else if (c == '?') {
            // (
            boolean ok = dp(s, i + 1, level + 1);
            if (ok) {
                return true;
            }
            if (level > 0) {
                // )
                ok = dp(s, i + 1, level - 1);
                if (ok) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            boolean ans = dp(s, 0, 0);
            if (ans) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

}