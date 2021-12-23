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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String s = in.readLine();
            int zero = 0;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    zero++;
                }
            }
            int n = s.length();
            int ans;
            if (zero == n) {
                ans = 0;
            } else {
                int i1 = s.indexOf('1');
                int i2 = s.lastIndexOf('1');
                ans = zero - i1 - (n - 1 - i2);
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}