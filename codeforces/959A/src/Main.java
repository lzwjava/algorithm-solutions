import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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

    // mmd win
    boolean dp(boolean mmd, int n) {
        if (n == 0) {
            if (mmd) {
                return false;
            } else {
                return true;
            }
        }
        if (n == 1) {
            if (mmd) {
                return false;
            } else {
                return true;
            }
        }
        if (mmd) {
            boolean ok = false;
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 0) {
                    if (dp(!mmd, n - i)) {
                        ok = true;
                        break;
                    }
                }
            }
            if (ok) {
                return true;
            } else {
                return false;
            }
        } else {
            boolean ok = false;
            for (int i = 1; i <= n; i++) {
                if (i % 2 == 1) {
                    if (!dp(!mmd, n - i)) {
                        ok = true;
                    }
                }
            }
            if (ok) {
                return false;
            } else {
                return true;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        boolean ok = dp(true, n);
        if (ok) {
            out.append("Mahmoud\n");
        } else {
            out.append("Ehab\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}