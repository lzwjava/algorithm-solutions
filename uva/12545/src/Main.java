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

    int count(String s, char c) {
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                cnt++;
            }
        }
        return cnt;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            String a = in.readLine();
            String b = in.readLine();
            int a1 = count(a, '1');
            int a0 = count(a, '0');
            int au = count(a, '?');

            int b1 = count(b, '1');
            int b0 = count(b, '0');

            int a00 = a0 + au;
            if (a00 >= b0) {

            }
        }
    }
}