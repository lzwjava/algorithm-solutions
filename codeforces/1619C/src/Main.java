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

    long cal(long a, long b) {
        StringBuilder sb = new StringBuilder();
        while (a != 0 || b != 0) {
            int ta = (int) (a % 10);
            int tb = (int) (b % 10);
            int sa = ta + tb;
            String s = String.format("%s", sa);
            String s1 = new StringBuilder(s).reverse().toString();
            sb.append(s1);

            a /= 10;
            b /= 10;
        }
        String fs = sb.reverse().toString();
        return Long.parseLong(fs);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            long a, s;
            StringTokenizer st = new StringTokenizer(in.readLine());
            a = Long.parseLong(st.nextToken());
            s = Long.parseLong(st.nextToken());
            long left = 0, right = s;
            while (right - left > 0) {
                long mid = (left + right) / 2;
                long v = cal(a, mid);
                if (v < s) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
                out.append(String.format("%d %d\n", left, right));
            }
            long fs = cal(a, left);
            if (fs == s) {
                out.append(String.format("%d\n", left));
            } else {
                out.append("-1\n");
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