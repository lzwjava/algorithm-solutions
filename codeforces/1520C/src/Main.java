import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
    
    void print(int[][] grid) {
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (v != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", grid[u][v] + 1));
            }
            out.append('\n');
        }
    }

    int n;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            int m = n * n;
            if (n == 2) {
                out.append("-1\n");
                t--;
                continue;
            }
            int[][] grid = new int[n][n];
            if (n == 1) {
                grid[0][0] = 0;
                print(grid);
                t--;
                continue;
            }
            if (n % 2 == 0) {
                int p = 0;
                int q = 1;
                boolean left = true;
                for (int i = 0; i < n; i++) {
                    if (left) {
                        for (int j = 0; j < n; j++) {
                            grid[i][j] = p;
                            p += 2;
                        }
                    } else {
                        int part = n / 2;
                        for (int j = 0; j < n; j++) {
                            int nj = (j + part) % n;
                            grid[i][nj] = q;
                            q += 2;
                        }
                    }
                    left = !left;
                }
            } else {
                int p = 0;
                int q = 1;
                int part = m / 2 + 1;
                for (int k = 0; k < part; k++) {
                    int i = (k / n) * 2;
                    int j = k % n;
                    grid[i][j] = p;
                    p += 2;
                }
                int rp = n - part % n;
                for (int k = 0; k < rp; k++) {
                    int i = n - 1;
                    int j = n - 1 - k;
                    grid[i][j] = q;
                    q += 2;
                }
                part = m / 2 - rp;
                for (int k = 0; k < part; k++) {
                    int i = n - 2 - (k / n) * 2;
                    int j = k % n;
                    grid[i][j] = q;
                    q += 2;
                }
            }
            print(grid);
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}