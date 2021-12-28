import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    String options = "rgw";
    String color = "rg";
    String s;
    int maxk;

    void permutation(char[] cs, int cur, int n, int red, int green) {
        if (!notDuplicate(cs, cur)) {
            return;
        }
        int rest = n - cur;
        if (rest + red < green || rest + green < red) {
            return;
        }
        if (cur == n) {
            if (red == green) {
                if (red > maxk) {
                    maxk = red;
                }
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            char c = options.charAt(i);
            cs[cur] = c;
            int nred = red, ngreen = green;
            if (c == 'r') {
                nred = red + 1;
            } else if (c == 'g') {
                ngreen = green + 1;
            }
            permutation(cs, cur + 1, n, nred, ngreen);
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

    boolean notDuplicate(char[] cs, int n) {
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
        return true;
    }

    int cal(String s) {
        this.s = s;
        int n = s.length();
        char[] chs = new char[n];
        maxk = 0;
        permutation(chs, 0, n, 0, 0);
        return maxk;
    }

    String randomString(int n) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = (char) (random.nextInt(26) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }

    void test() {
        while (true) {
            String s = randomString(50);
            int n = cal(s);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int maxk = cal(s);
            out.append(String.format("%d\n", maxk));
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
    }

}