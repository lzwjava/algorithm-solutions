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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void solve() throws IOException {
        String a = in.readLine();
        String b = in.readLine();
        int p = 0;
        for (char c : a.toCharArray()) {
            if (c == '+') {
                p++;
            } else if (c == '-') {
                p--;
            }
        }
        int u = 0;
        int q = 0;
        for (char c : b.toCharArray()) {
            if (c == '+') {
                q++;
            } else if (c == '-') {
                q--;
            } else if (c == '?') {
                u++;
            }
        }
        int d = p - q;
        double ans;
        if (d >= -u && d <= u) {
            
        } else {
            ans = 0;
        }
    }

}