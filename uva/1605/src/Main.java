import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    class Pair {
        char a, b;

        Pair(char a, char b) {
            if (a > b) {
                char t = a;
                a = b;
                b = t;
            }
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }

    void addPair(Set<Pair> pairs, char a, char b) {
        if (a != b) {
            pairs.add(new Pair(a, b));
        }
    }

    boolean cal(char[] chs, String s, int cur, int n, int w, int l) {
        int m = s.length();
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < cur; i++) {
            set.add(chs[i]);
        }
        if (set.size() + (n - cur) < m) {
            return false;
        }
        if (cur == n) {
            if (set.size() != m) {
                return false;
            }
            int k = w * l;
            int h = n / k;
            Set<Pair> pairs = new HashSet<>();
            for (int i = 0; i < h; i++) {
                int si = i * k;
                for (int j = 0; j < w - 1; j++) {
                    for (int d = 0; d < l; d++) {
                        int sj = si + j * l + d;
                        int nj = si + (j + 1) * l + d;
                        addPair(pairs, chs[sj], chs[nj]);
                    }
                }

                for (int j = 0; j < w; j++) {
                    for (int d = 0; d < l - 1; d++) {
                        int sj = si + j * l + d;
                        int nj = sj + 1;
                        addPair(pairs, chs[sj], chs[nj]);
                    }
                }
            }
            for (int i = 0; i < h - 1; i++) {
                int si = i * k;
                int sj = (i + 1) * k;
                for (int j = 0; j < k; j++) {
                    addPair(pairs, chs[si], chs[sj + j]);
                }
            }
            int pm = m * (m - 1) / 2;
            if (pairs.size() != pm) {
                return false;
            }
            out.append(String.format("%d %d %d\n", h, w, l));
            for (int i = 0; i < h; i++) {
                if (i != 0) {
                    out.append('\n');
                }
                int si = i * k;
                for (int j = 0; j < w; j++) {
                    for (int d = 0; d < l; d++) {
                        int sj = si + j * l + d;
                        out.append(String.format("%c", chs[sj]));
                    }
                    out.append('\n');
                }
            }
            return true;
        }
        for (int i = 0; i < m; i++) {
            chs[cur] = s.charAt(i);
            boolean ok = cal(chs, s, cur + 1, n, w, l);
            if (ok) {
                return true;
            }
        }
        return false;
    }

    void solve1(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) ('A' + i));
        }
        String s = sb.toString();
        int l = 3;
        int w = 3;
        int k = w * l;
        for (int h = 1; ; h++) {
            int h4 = h * k;
            char[] chs = new char[h4];
            boolean ok = cal(chs, s, 0, h4, w, l);
            if (ok) {
                break;
            }
        }
    }

    char charAt(int i) {
        if (i < 26) {
            return (char) ('A' + i);
        } else {
            return (char) ('a' + i - 26);
        }
    }

    void solve2(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(charAt(i));
        }
        String s = sb.toString();
        int m = s.length();
        int sum = m * (m - 1);
        int ss = (int) Math.ceil(Math.sqrt(sum));
        if (ss % 2 == 1) {
            ss++;
        }
        int len = ss * ss;
        char[] chs = new char[len];
        int p = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                chs[p++] = s.charAt(i);
                chs[p++] = s.charAt(j);
            }
        }
        while (p < len) {
            chs[p++] = s.charAt(0);
        }
        out.append(String.format("%d %d %d\n", 2, ss, ss));
        for (int i = 0; i < ss; i++) {
            for (int j = 0; j < ss; j++) {
                int idx = i * ss + j;
                out.append(String.format("%c", chs[idx]));
            }
            out.append('\n');
        }

        out.append('\n');
        for (int i = 0; i < ss; i++) {
            for (int j = 0; j < ss; j++) {
                out.append(String.format("%c", s.charAt(0)));
            }
            out.append('\n');
        }
    }

    void solve3(int n) {
        out.append(String.format("%d %d %d\n", 2, n, n));

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.append(charAt(i));
            }
            out.append('\n');
        }

        out.append('\n');

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                out.append(charAt(j));
            }
            out.append('\n');
        }
    }

    void solve() throws IOException {
        boolean first = true;
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            if (!first) {
                out.append('\n');
            }
            first = false;
            int n = Integer.parseInt(s);
            solve3(n);
        }
    }

}