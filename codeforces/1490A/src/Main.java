import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            int n = Integer.parseInt(in.readLine());
            List<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            List<Integer> nlist = new ArrayList<>();
            for (int i = 0; i < list.size() - 1; i++) {
                int v0 = list.get(i);
                int v1 = list.get(i + 1);
                int max = Integer.max(v0, v1);
                int min = Integer.min(v0, v1);
                if (min * 2 >= max) {
                    nlist.add(v0);
                } else {
                    if (v0 < v1) {
                        nlist.add(v0);
                        while (v0 * 2 < v1) {
                            nlist.add(v0 * 2);
                            v0 *= 2;
                        }
                    } else {
                        // v1 > v0
                        nlist.add(v0);
                        while (v1 * 2 < v0) {
                            int v = (int) Math.ceil(v0 * 1.0 / 2);
                            nlist.add(v);
                            v0 = v;
                        }
                    }
                }
            }
            nlist.add(list.get(list.size() - 1));
            int ans = nlist.size() - list.size();
            out.append(String.format("%d\n", ans));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}