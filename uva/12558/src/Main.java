import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int a, b, k;
    int[] ks;
    double target;

    void dfs(List<Integer> dms, int start, double sum) {
        if (Math.abs(target - sum) < 1e-8) {
            return;
        }
        if (sum > target) {
            return;
        }
        for (int i = start; ; i++) {
            dms.add(i);
            
            dms.remove(dms.size() - 1);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            ks = new int[k];
            for (int i = 0; i < k; i++) {
                ks[i] = Integer.parseInt(st.nextToken());
            }
            target = a * 1.0 / b;
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