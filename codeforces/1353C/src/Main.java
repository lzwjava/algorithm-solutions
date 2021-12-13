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

    long cal(int n) {
        long s = 0;
        int d = (n - 1) / 2;
        for (int i = 0; i < d; i++) {
            long a = 2 * i + 3;
            long b = a - 2;
            s += (a * a - b * b) * (i + 1);
        }
        return s;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            long s;
            if (n % 2 == 1) {
                s = cal(n);
            } else {
                s = cal(n - 1);
                long d = n / 2;
                s += ((long) n * n - (long) (n - 1) * (n - 1)) * d;
            }
            out.append(String.format("%d\n", s));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}