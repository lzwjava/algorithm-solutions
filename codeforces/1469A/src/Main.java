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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            int level = 0;
            int n = s.length();
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    level++;
                } else if (c == ')') {
                    if (level == 0) {
                        ok = false;
                        break;
                    }
                    level--;
                } else if (c == '?') {
                    if (level > 0) {
                        // )
                        level--;
                    } else {
                        level++;
                    }
                }
            }
            if (ok) {
                if (level > 0) {
                    ok = false;
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }

}