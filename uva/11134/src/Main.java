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
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[] xl, yl, xr, yr;
            xl = new int[n];
            yl = new int[n];
            xr = new int[n];
            yr = new int[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                xl[i] = Integer.parseInt(st.nextToken());
                yl[i] = Integer.parseInt(st.nextToken());
                xr[i] = Integer.parseInt(st.nextToken());
                yr[i] = Integer.parseInt(st.nextToken());
            }
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