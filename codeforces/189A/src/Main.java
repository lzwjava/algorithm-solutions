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

    int n;
    List<Integer> a;
    int max;
    int total;

    void cal(int st, int cur, int s) {
        if (total > 10) {
            return;
        }
        if (s == 0) {
            total++;
            if (cur > max) {
                max = cur;
            }
            return;
        }
        for (int i = st; i < a.size(); i++) {
            if (s >= a.get(i)) {
                cal(i, cur + 1, s - a.get(i));
            }
        }
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            int v = Integer.parseInt(st.nextToken());
            set.add(v);
        }
        a = new ArrayList<>(set);
        Collections.sort(a);
        max = -1;
        total = 0;
        cal(0, 0, n);
        out.append(String.format("%d\n", max));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}