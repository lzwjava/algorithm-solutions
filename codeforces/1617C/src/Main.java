import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    List<Integer> mods(int x, int n) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= x + 1; i++) {
            int m = x % i;
            if (m >= 1 && m <= n) {
                set.add(m);
                if (set.size() == n) {
                    break;
                }
            }
        }
        return new ArrayList<>(set);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            List<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            List<Integer> nos = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                int idx = list.indexOf(i);
                if (idx >= 0) {
                    list.remove(idx);
                } else {
                    nos.add(i);
                }
            }
            Collections.sort(list);
            boolean ok = true;
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                int v = list.get(i);
                if (mods(v, n).contains(nos.get(i))) {
                    c++;
                } else {
                    ok = false;
                    break;
                }
            }
            if (!ok) {
                out.append("-1\n");
            } else {
                out.append(String.format("%d\n", c));
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}