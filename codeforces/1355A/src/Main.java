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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.solve();
        m.test();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    long cal(long a, long k) {
        for (int i = 0; i < k - 1; i++) {
            String s = String.format("%d", a);
            int min = 10;
            int max = -1;
            for (char c : s.toCharArray()) {
                int digit = Character.digit(c, 10);
                if (digit < min) {
                    min = digit;
                }
                if (digit > max) {
                    max = digit;
                }
            }
            a = a + min * max;
        }
        return a;
    }

    void test() {
        for (int i = 1; i <= 100; i++) {
            long ans = cal(487, i);
            out.append(String.format("%d\n", ans));
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            long a = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            long ans = cal(a, k);
            out.append(String.format("%d\n", ans));
        }
    }
}