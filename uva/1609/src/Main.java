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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    int n;
    String[] strs;
    String ans;

    boolean check(int[] nums, int cur, StringBuilder sb) {
        while (cur != 1) {
            int nc = cur / 2;
            int[] as = new int[nc];
            for (int i = 0; i < nc; i++) {
                int j = 2 * i;
                int k = 2 * i + 1;
                as[i] = winner(strs, nums[j], nums[k]);
                sb.append(String.format("%d %d\n", nums[j] + 1, nums[k] + 1));
            }
            nums = as;
            cur /= 2;
        }
        return nums[0] == 0;
    }

    int winner(String[] strs, int a, int b) {
        if (strs[a].charAt(b) == '1') {
            return a;
        } else {
            return b;
        }
    }

    boolean found;

    void permutation(int[] nums, boolean[] vis, int cur) {
        if (found) {
            return;
        }
        if (cur == n) {
            StringBuilder sb = new StringBuilder();
            if (check(nums, n, sb)) {
                found = true;
                ans = sb.toString();
            }
            return;
        }
        double rd = Math.log(cur) / Math.log(2);
        if (Math.abs(rd - (int) rd) < 1e-10) {
            if (!check(nums, cur, new StringBuilder())) {
                return;
            }
        }
        int st;
        if (cur % 2 == 0) {
            st = nums[cur - 2] + 1;
        } else {
            st = 0;
        }
        for (int i = st; i < n; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                vis[i] = true;
                permutation(nums, vis, cur + 1);
                vis[i] = false;
            }
        }
    }

    String cal(int n, String[] strs) {
        this.n = n;
        this.strs = strs;
        int[] nums = new int[n];
        boolean[] vis = new boolean[n];
        nums[0] = 0;
        vis[0] = true;
        found = false;
        permutation(nums, vis, 1);
        return ans;
    }

    List<Pair> cal1(List<Integer> teams, String[] strs) {
        int n = teams.size();
        if (n == 1) {
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
        if (!ok) {
            int ov = other.get(0);
            pairs.add(new Pair(0, ov));
            oi++;
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

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            strs = new String[n];
            List<Integer> teams = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
                teams.add(i);
            }
            List<Pair> pairs = cal1(teams, strs);
            StringBuilder sb = new StringBuilder();
            for (Pair p : pairs) {
                sb.append(String.format("%d %d\n", p.a + 1, p.b + 1));
            }
            out.append(String.format("%s", sb.toString()));
        }
    }
}
