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
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int d = n / k;
            int[] as = new int[k];
            int min = Integer.min(m, d);
            as[0] = min;
            m -= min;
            if (m > 0) {
                while (m > 0) {
                    for (int i = 1; i < k; i++) {
                        as[i]++;
                        m--;
                        if (m == 0) {
                            break;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", as[0] - as[1]));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}