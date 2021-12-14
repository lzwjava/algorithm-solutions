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

    void solve() throws IOException {
        String s = in.readLine();
        boolean ok = false;
        if (s.contains("AB")) {
            String r = s.replaceFirst("AB", "XX");
            if (r.contains("BA")) {
                ok = true;
            }
        }
        if (s.contains("BA")) {
            String r = s.replaceFirst("BA", "XX");
            if (r.contains("AB")) {
                ok = true;
            }
        }
        if (ok) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}