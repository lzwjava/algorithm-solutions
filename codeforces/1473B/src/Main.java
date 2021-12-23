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

    class Factor {
        String s;
        int c;

        Factor(String s, int c) {
            this.s = s;
            this.c = c;
        }
    }

    Factor calFactor(String s) {
        int n = s.length();
        int m = n / 2;
        for (int l = 1; l <= m; l++) {
            if (n % l == 0) {
                int part = n / l;
                boolean ok = true;
                for (int i = 0; i < l; i++) {
                    for (int j = 1; j < part; j++) {
                        int ni = j * l + i;
                        if (s.charAt(ni) != s.charAt(i)) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    String sub = s.substring(0, l);
                    return new Factor(sub, part);
                }
            }
        }
        return new Factor(s, 1);
    }

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    void solve() throws IOException {
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            q--;
            String s = in.readLine();
            String t = in.readLine();
            Factor fs = calFactor(s);
            Factor ft = calFactor(t);
            if (fs.s.equals(ft.s)) {
                int lcm = lcm(fs.c, ft.c);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lcm; i++) {
                    sb.append(fs.s);
                }
                out.append(String.format("%s\n", sb));
            } else {
                out.append("-1\n");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}