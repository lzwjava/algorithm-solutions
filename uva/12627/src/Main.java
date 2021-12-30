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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int k = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int n = (1 << k) + 1;
            int[][] grid = new int[n][n];
            // 0: red, 1: blue
            grid[1][1] = 0;
            int m = 1;
            for (int k1 = 0; k1 < k; k1++) {
                int im = m, jm = m;
                for (int i = 1; i <= im; i++) {
                    for (int j = 1; j <= jm; j++) {
                        if (grid[i][j] == 0) {
                            for (int d = -1; d <= 0; d++) {
                                for (int f = -1; f <= 0; f++) {
                                    int nv;
                                    if (d == 0 && f == 0) {
                                        nv = 1;
                                    } else {
                                        nv = 0;
                                    }
                                    grid[2 * i + d][2 * j + f] = nv;
                                }
                            }
                        } else {
                            for (int d = -1; d <= 0; d++) {
                                for (int f = -1; f <= 0; f++) {
                                    grid[2 * i + d][2 * j + f] = 1;
                                }
                            }
                        }
                    }
                }
                m *= 2;
            }

            int red = 0;
            for (int i = a; i <= b; i++) {
                for (int j = 1; j <= m; j++) {
                    if (grid[i][j] == 0) {
                        red++;
                    }
                }
            }
            out.append(String.format("%d\n", red));
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