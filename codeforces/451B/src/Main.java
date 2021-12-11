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
        int n = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        int last = 0;
        int incr = 0, decr = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int c = Integer.compare(a[i], a[i + 1]);
            if (i == 0) {
                last = c;
            } else {
                if (c == last) {
                } else {
                    list.add(i);
                    if (last == -1) {
                        incr++;
                    } else {
                        decr++;
                    }
                }
            }
            last = c;
        }
        if (last == -1) {
            incr++;
        } else {
            decr++;
        }
        if (decr <= 1) {
            out.append("yes\n");
            int start = -1, end = -1;
            for (int j = 0; j < list.size(); j++) {
                int i = list.get(j);
                if (Integer.compare(a[i], a[i + 1]) == 1) {
                    start = i;
                    if (j + 1 < list.size()) {
                        end = list.get(j + 1);
                    } else {
                        end = n - 1;
                    }
                }
            }
            if (start == -1) {
                start = 0;
                end = n - 1;
            }
            out.append(String.format("%d %d\n", start + 1, end + 1));
        } else {
            out.append("no\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}