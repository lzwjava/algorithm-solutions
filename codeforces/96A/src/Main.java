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

    void solve() throws IOException {
        String s = in.readLine();
        int n = s.length();
        boolean dangerous = false;
        for (int i = 0; i <= n - 7; i++) {
            boolean continuous = true;
            char c = s.charAt(i);
            for (int j = i + 1; j < i + 7; j++) {
                if (s.charAt(j) != c) {
                    continuous = false;
                    break;
                }
            }
            if (continuous) {
                dangerous = true;
                break;
            }
        }
        if (dangerous) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
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
        Main main = new Main();
        main.solve();
        main.close();
    }
}
