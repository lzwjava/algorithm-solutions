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
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            long n = Integer.parseInt(st.nextToken());
            long k = Integer.parseInt(st.nextToken());
            double logSum = 0;
//            for (long i = n - k + 1; i <= n; i++) {
//                logSum += Math.log10(i);
//            }
//
//            for (long i = 1; i <= k; i++) {
//                logSum -= Math.log10(i);
//            }

            if (k > n - k) {
                for (long i = k + 1; i <= n; i++) {
                    logSum += Math.log10(i) - Math.log10(n - i + 1);
                }
            } else {
                for (long i = n - k + 1; i <= n; i++) {
                    logSum += Math.log10(i) - Math.log10(n - i + 1);
                }
            }
            out.append(String.format("%d\n", (long) logSum + 1));
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
            outStream = new PrintStream("1.out");
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
