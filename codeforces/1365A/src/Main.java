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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] g = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < m; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int c1 = 0;
            for (int i = 0; i < n; i++) {
                boolean ok = true;
                for (int j = 0; j < m; j++) {
                    if (g[i][j] == 1) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    c1++;
                }
            }

            int c2 = 0;
            for (int j = 0; j < m; j++) {
                boolean ok = true;
                for (int i = 0; i < n; i++) {
                    if (g[i][j] == 1) {
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    c2++;
                }
            }

            int mc = Math.min(c1, c2);
            if (mc % 2 == 1) {
                out.append("Ashish\n");
            } else {
                out.append("Vivek\n");
            }
        }
    }

}