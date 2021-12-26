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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            String ans;
            if (n == 1) {
                ans = "9";
            } else if (n == 2) {
                ans = "98";
            } else {
                StringBuilder sb = new StringBuilder("989");
                int p = 0;
                for (int i = 3; i < n; i++) {
                    sb.append(String.format("%d", p));
                    p++;
                    if (p == 10) {
                        p = 0;
                    }
                }
                ans = sb.toString();
            }
            out.append(String.format("%s\n", ans));
        }
    }

}