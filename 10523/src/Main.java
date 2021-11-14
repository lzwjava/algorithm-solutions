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

    BigInteger fn(int n, int a) {
        BigInteger s = BigInteger.valueOf(0);
        BigInteger ba = BigInteger.valueOf(a);
        for (int i = 1; i <= n; i++) {
            BigInteger bi = BigInteger.valueOf(i);
            BigInteger ai = ba.pow(i);
            s = s.add(bi.multiply(ai));
        }
        return s;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            BigInteger ans = fn(n, a);
            out.append(String.format("%s\n", ans.toString()));
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
