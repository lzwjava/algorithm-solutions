import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int maxn = 1000;
    BigInteger[] map = new BigInteger[maxn];

    void solve() throws IOException {
        BigInteger v = BigInteger.ONE;
        for (int i = 1; i < maxn; i++) {
            v = v.multiply(BigInteger.valueOf(i));
            map[i] = v;
        }
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int a = 1, b = 1, c = 1;
            BigInteger ans = BigInteger.ONE;
            while (true) {
                boolean ok = false;
                if (a <= n) {
                    ans = ans.multiply(BigInteger.valueOf(a));
                    a++;
                    ok = true;
                }
                if (b <= k) {
                    while (true) {
                        BigInteger bn = BigInteger.valueOf(b);
                        if (ans.mod(bn).intValue() == 0) {
                            ans = ans.divide(bn);
                            b++;
                            ok = true;
                        } else {
                            break;
                        }
                    }
                }
                if (c <= n - k) {
                    while (true) {
                        BigInteger cn = BigInteger.valueOf(b);
                        if (ans.mod(cn).intValue() == 0) {
                            ans = ans.divide(cn);
                            c++;
                            ok = true;
                        } else {
                            break;
                        }
                    }
                }
                if (!ok) {
                    break;
                }
            }
            String str = String.format("%d", ans);
            out.append(String.format("%s\n", str.length()));
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
