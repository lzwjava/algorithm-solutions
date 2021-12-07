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

    int n, m, s, t;
    int[] os;
    boolean[][] grid;
    Random random;
    Set<Integer> set;
    int ans;

    void dfs(int[] st, int cur, int dist) {
        if (st[t] == 1) {
            if (dist < ans) {
                ans = dist;
            }
            return;
        }
        List<Integer> nodes = new ArrayList<>();
        int i = 0;
        boolean[] vis = new boolean[n];
        int cnt = 0;
        while (cnt < n) {
            do {
                i = random.nextInt(n);
                if (st[i] != 0 && !vis[i]) {
                    break;
                }
            } while (true);
            cnt++;
            vis[i] = true;
            nodes = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (grid[i][j] && st[j] == 0) {
                    swap(st, i, j);
                    int key = key(st);
                    if (!set.contains(key)) {
                        nodes.add(j);
                    }
                    swap(st, i, j);
                }
            }
            if (nodes.size() != 0) {
                break;
            }
        }
        if (cnt == n && nodes.size() == 0) {
            return;
        }
        int k = random.nextInt(nodes.size());
        int j = nodes.get(k);
        swap(st, i, j);
        int key = key(st);
        set.add(key);
        dfs(st, cur + 1, dist + 1);
        swap(st, i, j);
    }

    int key(int[] a) {
        return Arrays.hashCode(a);
    }

    void swap(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    void solve() throws IOException {
        int tt = Integer.parseInt(in.readLine());
        for (int p = 0; p < tt; p++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken()) - 1;
            t = Integer.parseInt(st.nextToken()) - 1;
            os = new int[m];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < m; i++) {
                os[i] = Integer.parseInt(st.nextToken());
            }
            grid = new boolean[n][n];
            for (int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(in.readLine());
                int u = Integer.parseInt(st.nextToken()) - 1;
                int v = Integer.parseInt(st.nextToken()) - 1;
                grid[u][v] = grid[v][u] = true;
            }
            // 1: robot, 2: obstacle, 0: empty
            int[] state = new int[n];
            state[s] = 1;
            for (int i = 0; i < m; i++) {
                state[os[i]] = 2;
            }
            random = new Random();
            set = new HashSet<>();
            ans = Integer.MAX_VALUE;
            dfs(state, 0, 0);
            out.append(String.format("Case %d: %d\n", p + 1, ans));
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