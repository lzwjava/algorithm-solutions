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

    int charToInt(char ch) {
        return ch - 'a';
    }

    void solve() throws IOException {
        String s = in.readLine();
        int a = 0;
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int b = charToInt(c);
            int d1 = (b - a + 26) % 26;
            int d2 = (a + 26 - b) % 26;
            if (d1 <= d2) {
                p += d1;
            } else {
                p += d2;
            }
            a = b;
        }
        out.append(String.format("%d\n", p));
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}