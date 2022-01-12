import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
//        m.test();
        m.close();
    }

    int winner(String[] strs, int a, int b) {
        if (strs[a].charAt(b) == '1') {
            return a;
        } else {
            return b;
        }
    }

    List<Pair> cal1(List<Integer> teams, String[] strs) {
        int n = teams.size();
        if (n <= 1) {
            return new ArrayList<>();
        }
        List<Integer> black = new ArrayList<>();
        List<Integer> rest = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int ti = teams.get(i);
            if (ti == 0) {
                continue;
            }
            if (strs[0].charAt(ti) == '0') {
                black.add(ti);
            } else {
                rest.add(ti);
            }
        }
        List<Integer> gray = new ArrayList<>();
        List<Integer> other = new ArrayList<>();
        for (int x : rest) {
            String str = strs[x];
            boolean ok = false;
            for (int y : black) {
                if (str.charAt(y) == '1') {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                gray.add(x);
            } else {
                other.add(x);
            }
        }
        int bi;
        int gn = gray.size();
        int bn = black.size();
        boolean[] grayVis = new boolean[gn];
        boolean[] blackVis = new boolean[bn];
        List<Pair> pairs = new ArrayList<>();
        for (bi = 0; bi < bn; bi++) {
            int bv = black.get(bi);
            boolean ok = false;
            for (int i = 0; i < gn; i++) {
                if (!grayVis[i]) {
                    int gv = gray.get(i);
                    if (winner(strs, gv, bv) == gv) {
                        grayVis[i] = true;
                        blackVis[bi] = true;
                        pairs.add(new Pair(gv, bv));
                        ok = true;
                        break;
                    }
                }
            }
            if (!ok) {
                break;
            }
        }
        boolean ok = false;
        for (int i = 0; i < gn; i++) {
            int gv = gray.get(i);
            if (!grayVis[i]) {
                grayVis[i] = true;
                pairs.add(new Pair(0, gv));
                ok = true;
                break;
            }
        }
        int oi = 0;
        if (!ok && oi < other.size()) {
            int ov = other.get(oi);
            oi++;
            pairs.add(new Pair(0, ov));
        }
        if (bi != bn) {
            int blackPair = (bn - bi) / 2;
            int obi = bi;
            for (int i = 0; i < blackPair; i++) {
                int bj = obi + i * 2;
                int bv1 = black.get(bj);
                int bv2 = black.get(bj + 1);
                blackVis[bj] = blackVis[bj + 1] = true;
                pairs.add(new Pair(bv1, bv2));
                bi += 2;
            }
        }
        List<Integer> notPaired = new ArrayList<>();
        if (bi != bn) {
            for (; bi < bn; bi++) {
                notPaired.add(black.get(bi));
            }
        }
        for (; oi < other.size(); oi++) {
            notPaired.add(other.get(oi));
        }
        for (int i = 0; i < gn; i++) {
            if (!grayVis[i]) {
                notPaired.add(gray.get(i));
            }
        }
        int pn = notPaired.size() / 2;
        for (int i = 0; i < pn; i++) {
            int v1 = notPaired.get(2 * i);
            int v2 = notPaired.get(2 * i + 1);
            pairs.add(new Pair(v1, v2));
        }
        List<Integer> wins = new ArrayList<>();
        for (Pair p : pairs) {
            wins.add(winner(strs, p.a, p.b));
        }
        List<Pair> subPairs = cal1(wins, strs);
        pairs.addAll(subPairs);
        return pairs;
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    boolean check(List<Pair> pairs, int wn, String[] strs) {
        if (pairs.size() == 1) {
            Pair p = pairs.get(0);
            int w = winner(strs, p.a, p.b);
            return w == 0;
        } else {
            List<Integer> ws = new ArrayList<>();
            for (int i = 0; i < wn; i++) {
                Pair p = pairs.get(i);
                int w = winner(strs, p.a, p.b);
                ws.add(w);
            }
            int next = wn / 2;
            List<Integer> actuals = new ArrayList<>();
            for (int i = 0; i < next; i++) {
                Pair p = pairs.get(next + i);
                actuals.add(p.a);
                actuals.add(p.b);
            }
            if (equal(ws, actuals)) {
                List<Pair> subPairs = pairs.subList(wn, pairs.size());
                return check(subPairs, next, strs);
            } else {
                return false;
            }
        }
    }

    boolean equal(List<Integer> a, List<Integer> b) {
        Collections.sort(a);
        Collections.sort(b);
        return a.equals(b);
    }

    void test() {
        while (true) {
            Random random = new Random();
            int n = random.nextInt(8) + 2;
            double round = Math.log(n) / Math.log(2);
            if (Math.abs(round - (int) round) > 1e-10) {
                continue;
            }
            char[][] grid = new char[n][n];
            for (int i = 0; i < n; i++) {
                grid[i][i] = '0';
                for (int j = i + 1; j < n; j++) {
                    int win = random.nextInt(2);
                    grid[i][j] = (char) (win + '0');
                    grid[j][i] = (char) ((1 - win) + '0');
                }
            }
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = new String(grid[i]);
            }
            int c = 0;
            for (int i = 1; i < n; i++) {
                if (grid[0][i] == '1') {
                    c++;
                }
            }
            if (c < n / 2) {
                continue;
            }
            List<Pair> pairs = solve1(n, strs);
            assert check(pairs, n / 2, strs);
        }
    }

    List<Pair> solve1(int n, String[] strs) {
        List<Integer> teams = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            teams.add(i);
        }
        List<Pair> pairs = cal1(teams, strs);
        return pairs;
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
            }
            List<Pair> pairs = solve1(n, strs);
            check(pairs, n / 2, strs);
            StringBuilder sb = new StringBuilder();
            for (Pair p : pairs) {
                sb.append(String.format("%d %d\n", p.a + 1, p.b + 1));
            }
            out.append(String.format("%s", sb.toString()));
        }
    }
}
