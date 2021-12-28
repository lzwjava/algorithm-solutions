import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    String options = "rbw";
    String color = "rb";
    String s;

    void permutation(char[] cs, int cur, int n) {
        if (cur == n) {
            check(cs, n);
            return;
        }
        for (int i = 0; i < 3; i++) {
            cs[cur] = options.charAt(i);
            permutation(cs, cur + 1, n);
        }
    }

    boolean check(char[] cs, int n) {
        List<Character>[] as = new ArrayList[2];
        for (int i = 0; i < 2; i++) {
            as[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            int idx = color.indexOf(c);
            if (idx >= 0) {
                as[idx].add(s.charAt(i));
            }
        }

        for (int i = 0; i < 2; i++) {
            
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            s = in.readLine();
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