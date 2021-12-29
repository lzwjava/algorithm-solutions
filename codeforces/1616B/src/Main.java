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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String s = in.readLine();
            String ans = "";
            for (int i = 0; i < n; i++) {
                String s1 = s.substring(0, i + 1);
                String s2 = new StringBuilder(s1).reverse().toString();
                String ns = String.format("%s%s", s1, s2);
                if (ans.equals("")) {
                    ans = ns;
                } else {
                    if (ns.compareTo(ans) < 0) {
                        ans = ns;
                    }
                }
            }
            out.append(String.format("%s\n", ans));
        }
    }
}