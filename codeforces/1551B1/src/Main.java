import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;

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

    boolean isColor(char c) {
        int idx = color.indexOf(c);
        return idx >= 0;
    }

    int cnt;
    int maxCnt;

    void permutation(char[] cs, int cur, int n, int red, int green) {
        if (cnt > maxCnt) {
            return;
        }
        int rest = n - cur;
        if (rest + red < green || rest + green < red) {
            return;
        }
        if (red + green + rest / 2 < maxk) {
            return;
        }
        if (cur == n) {
            if (red == green) {
                if (red > maxk) {
                    cnt = 0;
                    maxk = red;
                } else {
                    cnt++;
                }
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            char c = options.charAt(i);
            cs[cur] = c;
            boolean ok = true;
            if (isColor(c)) {
                for (int j = 0; j < cur; j++) {
                    if (s.charAt(j) == s.charAt(cur) && cs[j] == c) {
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) {
                int nred = red, ngreen = green;
                if (c == 'r') {
                    nred = red + 1;
                } else if (c == 'g') {
                    ngreen = green + 1;
                }
                permutation(cs, cur + 1, n, nred, ngreen);
            }
        }
    }

    int cal(String s) {
        this.s = s;
        int n = s.length();
        char[] chs = new char[n];
        maxk = -1;
        cnt = 0;
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
        int c = 0;
        while (true) {
            String s = randomString(50);
//            maxCnt = Integer.MAX_VALUE;
//            int n1 = cal(s);
            maxCnt = 1000;
            int n2 = cal(s);
//            assert (n1 == n2);
            c++;
            out.append(String.format("%d\n", c));
            out.flush();
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            maxCnt = 300;
            cnt = 0;
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