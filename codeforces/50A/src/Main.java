import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int ans;
        if (m % 2 == 0 && n % 2 == 0) {
            ans = m * n / 2;
        } else if (m % 2 == 0 || n % 2 == 0) {
            if (m % 2 != 0) {
                int t = m;
                m = n;
                n = t;
            }
            int n1 = n - 1;
            ans = m * n1 / 2 + m / 2;
        } else {
            int m1 = m - 1;
            int n1 = n - 1;
            ans = m1 * n1 / 2 + m / 2 + n / 2;
        }
        out.append(String.format("%d\n", ans));
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
