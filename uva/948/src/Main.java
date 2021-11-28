import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    void solve() throws IOException {
        int maxn = 38;
        int[] fib = new int[maxn];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 2; i < maxn; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        int n = readInt();
        while (n > 0) {
            int v = readInt();
            int[] bs = new int[maxn];
            int p = v;
            int firstI = -1;
            for (int i = maxn - 1; i >= 0; i--) {
                if (p >= fib[i]) {
                    bs[i] = 1;
                    p -= fib[i];
                    if (firstI == -1) {
                        firstI = i;
                    }
                    if (p == 0) {
                        break;
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = firstI; i >= 0; i--) {
                sb.append(String.format("%d", bs[i]));
            }
            out.append(String.format("%d = %s (fib)\n", v, sb.toString()));
            n--;
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
