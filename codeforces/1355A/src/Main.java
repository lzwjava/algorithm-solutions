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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            long a = Long.parseLong(in.readLine());
            long k = Long.parseLong(in.readLine());
            for (int i = 0; i < k; i++) {
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
                long b = a + min + max;
                a = b;
            }
            out.append(String.format("%d\n", a));
        }
    }
}