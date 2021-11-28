import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    long f(long n) {
        if (n < 3) {
            return 0;
        }
        return n * (n - 3) / 2;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            long N = Long.parseLong(in.readLine());
            if (N == 0) {
                break;
            }
            long ans;
            if (N <= 2) {
                ans = 4;
            } else {
                long bottom = (long) Math.sqrt(N * 2);
                long top = bottom + 5;
                while (bottom != top) {
                    if (top - bottom == 1 && f(top) >= N && f(bottom) < N) {
                        break;
                    }
                    long mid = (bottom + top) / 2;
                    long v = f(mid);
                    if (v < N) {
                        bottom = mid;
                    } else {
                        top = mid;
                    }
                }
                ans = top;
            }
            out.append(String.format("Case %d: %d\n", caseNum, ans));
            caseNum++;
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
//            outStream = new PrintStream("1.out");
            System.setIn(inStream);
//            System.setOut(outStream);
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
