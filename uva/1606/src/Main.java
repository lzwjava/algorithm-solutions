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

    class Particle {
        int x, y, r;

        Particle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            Particle[] ps = new Particle[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                ps[i] = new Particle(x, y, r);
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    Particle pi = ps[i];
                    Particle pj = ps[j];
                    double k = (pi.y - pj.y) * 1.0 / (pi.x - pj.x);
                    double b = pi.y - pi.x * k;

                    int[][] rs = new int[2][3];

                    for (int u = 0; u < n; u++) {
                        Particle pu = ps[u];
                        double y1 = k * pu.x + b;
                        if (Math.abs(y1 - pu.y) < 1e-8) {
                            rs[pu.r][0]++;
                        } else if (pu.y < y1) {
                            rs[pu.r][1]++;
                        } else {
                            rs[pu.r][2]++;
                        }
                    }

                    int white = 0, black = 0;
                    white = rs[0][1] + rs[0][0];
                    black = rs[1][2] + rs[1][0];

                    int total = white + black;
                    white = rs[0][2] + rs[0][0];
                    black = rs[1][1] + rs[1][0];

                    total = Integer.max(total, white + black);
                    if (total > max) {
                        max = total;
                    }
                }
            }
            out.append(String.format("%d\n", max));
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