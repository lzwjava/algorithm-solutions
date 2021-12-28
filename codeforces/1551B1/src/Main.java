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

    void permutation(char[] cs, int cur, int n) {
        if (cur == n) {
            return;
        }
        String color = "rbw";
        for (int i = 0; i < 3; i++) {
            cs[cur] = color.charAt(i);
            permutation(cs, cur + 1, n);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int n = s.length();
            char[] chs = new char[n];
            permutation(chs, 0, n);
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}