import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                list.add(v);
            }
            Collections.sort(list);
            int s = 0;
            for (int i = 0; i < k; i++) {
                s += list.get(n - 1 - i - k) / list.get(n - 1 - i);
            }
            for (int i = 0; i < n - 2 * k; i++) {
                s += list.get(i);
            }
            out.append(String.format("%d\n", s));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}