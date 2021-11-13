import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int f(int m, int n) {
        if (n < m) {
            return f(n, m);
        }
        if (m == 1) {
            return n;
        }
        if (m == 2) {
            return n / 4 * 4 + (n % 4 >= 2 ? 4 : (n % 4) * 2);
        } else {
            return (n * m + 1) / 2;
        }
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 0 && n == 0) {
                break;
            }
            int ans;
            if (m == 1 || n == 1) {
                ans = m * n;
            } else if (m * n < 6) {
                ans = m * n;
            } else if (m * n == 6) {
                ans = 4;
            } else if (m == 2 || n == 2) {
                ans = m * n / 2;
                if (m % 2 == 0 && n % 2 == 0) {
                    ans += 2;
                }
            } else {
                ans = m / 2 * n;
                if (m % 2 == 1) {
                    ans += (n + 1) / 2;
                }
            }
//            ans = (int) ((m + 1) / 2) * (int) ((n + 1) / 2);
//            ans = ((m + 1) / 2) * ((n + 1) / 2) * 2;
            out.append(String.format("%d knights may be placed on a %d row %d column board.\n", ans, m, n));
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("2.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
