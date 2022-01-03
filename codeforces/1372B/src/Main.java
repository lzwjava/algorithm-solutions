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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int minLcm = Integer.MAX_VALUE;
            int ma = 0, mb = 0;
            for (int a = 1; a < n; a++) {
                int b = n - a;
                int lcm = lcm(a, b);
                if (lcm < minLcm) {
                    minLcm = lcm;
                    ma = a;
                    mb = b;
                }
            }
            out.append(String.format("%d %d\n", ma, mb));
        }
    }
}