import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] vs;
    int n;
    int p;

    boolean dp(int cur, int s) {
        if (s > n) {
            return false;
        }
        if (cur == p) {
            if (s == n) {
                return true;
            }
            return false;
        }
        if (dp(cur + 1, s + vs[cur])) {
            return true;
        }
        if (dp(cur + 1, s)) {
            return true;
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            p = Integer.parseInt(in.readLine());
            vs = new int[p];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < p; i++) {
                vs[i] = Integer.parseInt(st.nextToken());
            }
            boolean ok = dp(0, 0);
            if (ok) {
                out.append(String.format("YES\n"));
            } else {
                out.append(String.format("NO\n"));
            }
            t--;
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
