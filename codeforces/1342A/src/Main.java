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

    int[] dx = new int[]{-1, 1, 0, 0, 1, -1};
    int[] dy = new int[]{0, 0, -1, 1, 1, -1};

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        while (tt > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(in.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (x > y) {
                int t = x;
                x = y;
                y = t;
            }
            long ans;
            if (2 * a <= b) {
                ans = (long) a * (x + y);
            } else {
                ans = (long) x * b + (long) (y - x) * a;
            }
            out.append(String.format("%d\n", ans));
            tt--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}