import java.io.*;
import java.math.BigInteger;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    BigInteger[] map;

    BigInteger dp(int x) {
        if (map[x] != null) {
            return map[x];
        }
        BigInteger ans;
        if (x == 0) {
            ans = BigInteger.valueOf(1);
        } else {
            BigInteger s = BigInteger.valueOf(0);
            for (int i = 1; i <= 4; i++) {
                int v = i;
                if (v == 4) {
                    v = 1;
                }
                if (x >= v) {
                    s.add(dp(x - v));
                }
            }
            ans = s;
        }
        map[x] = ans;
        return ans;
    }

    int maxn = 1000;

    void solve() throws IOException {
        map = new BigInteger[maxn + 1];
        for (n = 0; n <= 1000; n++) {
            BigInteger ans = dp(n);
            out.append(String.format("%s,", ans.toString()));
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
