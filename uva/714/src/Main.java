import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] p;
    int m, k;
    int min;
    int[] minSeps;

    void permutation(int[] seps, int cur, int sum) {
        if (sum > min) {
            return;
        }
        if (cur == k - 1) {
            int s = 0;
            for (int j = seps[cur - 1] + 1; j < m; j++) {
                s += p[j];
            }
            sum = Integer.max(sum, s);
            if (sum < min) {
                min = sum;
                minSeps = seps.clone();
            }
            return;
        }
        int st;
        if (cur == 0) {
            st = 0;
        } else {
            st = seps[cur - 1] + 1;
        }
        for (int i = st; i < m; i++) {
            seps[cur] = i;
            int s = 0;
            for (int j = st; j <= i; j++) {
                s += p[j];
            }
            int nsum = Integer.max(s, sum);
            permutation(seps, cur + 1, nsum);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            p = new int[m];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                p[i] = Integer.parseInt(st.nextToken());
            }
            min = Integer.MAX_VALUE;
            int[] seps = new int[k];
            permutation(seps, 0, 0);
            int v = 0;
            for (int i = 0; i < m; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", p[i]));

                if (v < k && i == minSeps[v]) {
                    out.append(" /");
                    v++;
                }
            }
            out.append('\n');
            t--;
        }
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
}