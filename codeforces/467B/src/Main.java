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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        int n, m, k;
        StringTokenizer st = new StringTokenizer(in.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[] x = new int[m];
        for (int i = 0; i < m; i++) {
            x[i] = Integer.parseInt(in.readLine());
        }
        int p = Integer.parseInt(in.readLine());
        String pb = Integer.toBinaryString(p);
        int plen = pb.length();
        int ans = 0;
        for (int i = 0; i < m; i++) {
            String xb = Integer.toBinaryString(x[i]);
            int xlen = xb.length();
            int min = Integer.min(xlen, plen);
            int c = 0;
            for (int j = 0; j < min; j++) {
                int xj = xlen - 1 - j;
                int pj = plen - 1 - j;
                if (xb.charAt(xj) != pb.charAt(pj)) {
                    c++;
                }
            }
            int max = Integer.max(xlen, plen);
            c += (max - min);
            if (c <= k) {
                ans++;
            }
        }
        out.append(String.format("%d\n", ans));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}