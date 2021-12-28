import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    int maxk;

    void permutation(char[] cs, int cur, int n) {
        if (cur == n) {
            if (check(cs, n)) {
                int painted = painted(cs, n);
                int k = painted / 2;
                if (k > maxk) {
                    maxk = k;
                }
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            cs[cur] = options.charAt(i);
            permutation(cs, cur + 1, n);
        }
    }

    int painted(char[] cs, int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            int idx = color.indexOf(c);
            if (idx >= 0) {
                total++;
            }
        }
        return total;
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
            Set<Character> set = new HashSet<>();
            set.addAll(as[i]);
            if (set.size() != as[i].size()) {
                //duplicate
                return false;
            }
        }
        if (as[0].size() != as[1].size()) {
            return false;
        }
        return true;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            s = in.readLine();
            int n = s.length();
            char[] chs = new char[n];
            maxk = 0;
            permutation(chs, 0, n);
            out.append(String.format("%d\n", maxk));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}