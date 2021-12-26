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

    boolean allEqual(int[] c) {
        return c[0] == c[1] && c[0] == c[2];
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int[] c = new int[3];
            for (int i = 0; i < n; i++) {
                int r = a[i] % 3;
                c[r]++;
            }
            int ans;
            if (allEqual(c)) {
                ans = 0;
            } else {
                int avg = n / 3;
                int move = 0;
                while (!allEqual(c)) {
                    for (int i = 0; i < 3; i++) {
                        if (c[i] > avg) {
                            int ni = (i - 1 + 3) % 3;
                            if (c[ni] < avg) {
                                c[i]--;
                                c[ni]++;
                                move++;
                                break;
                            }

                            ni = (i + 1) % 3;
                            if (c[ni] < avg) {
                                c[i]--;
                                c[ni]++;
                                move++;
                                break;
                            }
                        }
                    }
                }
                ans = move;
            }
            out.append(String.format("%d\n", ans));
        }
    }

}