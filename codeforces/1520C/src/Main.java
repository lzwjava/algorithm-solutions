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

    int[] dx = {-1, 0};
    int[] dy = {0, -1};

    boolean adjacent(int a, int b) {
        return Math.abs(a - b) == 1;
    }

    boolean check(int[] nums, int p) {
        if (p == 0) {
            return true;
        }
        int i = p - 1;
        int x = i / n;
        int y = i % n;
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                int ni = nx * n + ny;
                if (ni < p) {
                    if (adjacent(nums[i], nums[ni])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void print(int[][] grid) {
        for (int u = 0; u < n; u++) {
            for (int v = 0; v < n; v++) {
                if (v != 0) {
                    out.append(' ');
                }
                out.append(String.format("%2d", grid[u][v] + 1));
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
                int q = m - 2;
                int part = m / 2 + 2;
                for (int k = 0; k < part; k++) {
                    int i = (k / n) * 2;
                    int j = k % n;
                    if (k == part - 1) {
                        grid[i][j] = q;
                        q -= 2;
                    } else {
                        grid[i][j] = p;
                        p += 2;
                    }

                }
                part = n / 2;
                for (int k = 0; k < part; k++) {
                    int i = (k / n) * 2 + 1;
                    int j = k % n;
                    grid[i][j] = q;
                    q -= 2;
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