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
            int mp = -1;
            char mc = 'a';
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (mp == -1) {
                    mc = c;
                    mp = i;
                } else {
                    if (Character.compare(c, mc) < 0) {
                        mc = c;
                        mp = i;
                    }
                }
            }
//            String s1 = s.substring(0, mp + 1);
//            String s2 = new StringBuilder(s1).reverse().toString();
//            String ns = String.format("%s%s", s1, s2);


            String ans = "";
            for (int i = 0; i <= mp; i++) {
                String s1 = s.substring(0, i + 1);
                if (!ans.equals("")) {
                    if (ans.compareTo(s1) < 0) {
                        break;
                    }
                }
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