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
        int i1 = s.indexOf("AB");
        int i2 = s.lastIndexOf("BA");
        if (i1 >= 0 && i2 >= 0 && i1 + 1 != i2) {
            ok = true;
        }
        if (!ok) {
            i1 = s.lastIndexOf("AB");
            i2 = s.indexOf("BA");
            if (i1 >= 0 && i2 >= 0 && i2 != i1 + 1) {
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