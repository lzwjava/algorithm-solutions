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
            List<Integer> ps = new ArrayList<>();
            char mc = 'a';
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (ps.size() == 0) {
                    mc = c;
                    ps.add(i);
                } else {
                    int cmp = Character.compare(c, mc);
                    if (cmp < 0) {
                        mc = c;
                        ps.clear();
                        ps.add(i);
                    } else if (cmp == 0) {
                        ps.add(i);
                    }
                }
            }
            List<Integer> nps = new ArrayList<>();
            int p0 = ps.get(0);
            for (int i = 0; i < p0; i++) {
                nps.add(i);
            }
            nps.addAll(ps);
            String ans = "";
            for (int i = 0; i < nps.size(); i++) {
                int p = nps.get(i);
                String s1 = s.substring(0, p + 1);
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