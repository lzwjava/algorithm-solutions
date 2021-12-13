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
            int n = Integer.parseInt(in.readLine());
            int m = n * 2;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int c = 0;
            for (int i = 0; i < m; i++) {
                int v = Integer.parseInt(st.nextToken());
                if (v % 2 == 1) {
                    c++;
                }
            }
            if (c == n) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}