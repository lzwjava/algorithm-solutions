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

    List<Integer> mods(int x) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= x + 1; i++) {
            int m = x % i;
            set.add(m);
        }
        return new ArrayList<>(set);
    }

    boolean haveMod(int x, int y) {
        return y * 2 < x;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            List<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            boolean[] vis = new boolean[n + 1];
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                if (v >= 1 && v <= n && !vis[v]) {
                    vis[v] = true;
                } else {
                    list.add(v);
                }
            }
            Collections.sort(list);
            List<Integer> nos = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                if (!vis[i]) {
                    nos.add(i);
                }
            }
            boolean ok = true;
            int c = 0;
            for (int i = 0; i < list.size(); i++) {
                int v = list.get(i);
                if (haveMod(v, nos.get(i))) {
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
//        m.test();
        m.solve();
        m.close();
    }

}