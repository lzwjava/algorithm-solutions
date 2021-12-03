import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    ArrayList<Pair> list;
    int n;
    int m;

    int[] parent;
    int[] rank;
    int min;

    int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return find(parent[x]);
        }
    }

    void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx == fy) {
            return;
        }
        if (rank[fx] < rank[fy]) {
            parent[fx] = fy;
        } else {
            parent[fy] = fx;
            if (rank[fx] == rank[fy]) {
                rank[fx]++;
            }
        }
    }

    void dfs(boolean[] cut, int cur, int cnt) {
        if (cnt > min) {
            return;
        }
        if (cur == m) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
            boolean cyclic = false;
            for (int i = 0; i < m; i++) {
                if (!cut[i]) {
                    Pair p = list.get(i);
                    int fa = find(p.a);
                    int fb = find(p.b);
                    if (fa == fb) {
                        cyclic = true;
                        break;
                    } else {
                        union(p.a, p.b);
                    }
                }
            }
            if (!cyclic) {
                Set<Integer> set = new HashSet<>();
                for (int i = 1; i <= n; i++) {
                    int fi = find(i);
                    set.add(fi);
                }
                int group = set.size();
                int link = group - 1;
                int ans;
                if (cnt >= link) {
                    ans = cnt;
                } else {
                    ans = cnt + link - cnt;
                }
                if (ans < min) {
                    min = ans;
                }
            }
            return;
        }
        cut[cur] = false;
        dfs(cut, cur + 1, cnt);
        cut[cur] = true;
        dfs(cut, cur + 1, cnt + 1);
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            list = new ArrayList<>();
            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == -1 && b == -1) {
                    break;
                }
                list.add(new Pair(a, b));
            }
            m = list.size();
            min = Integer.MAX_VALUE;
            boolean[] cut = new boolean[m];
            dfs(cut, 0, 0);
            out.append(String.format("Set %d: Minimum links to open is %d\n", caseNum, min));
            caseNum++;
        }
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
}