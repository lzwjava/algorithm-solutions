import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

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
    int m;

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    // Ashish win
    boolean dp(int[][] g, boolean ashish, int level) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1) {
                    continue;
                }
                // 0
                boolean ok = true;
                for (int d = 0; d < dx.length; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                        if (g[ni][nj] == 1) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    g[i][j] = 1;
                    boolean result = dp(g, !ashish, level + 1);
                    g[i][j] = 0;
                    if (ashish && result) {
                        return true;
                    }
                    if (!ashish && !result) {
                        return false;
                    }
                }
            }
        }
        if (ashish) {
            return false;
        } else {
            return true;
        }
    }

    boolean win(int n, int m, int[][] g) {
        this.n = n;
        this.m = m;
        boolean win = dp(g, true, 0);
        return win;
    }

    class Pos implements Comparable<Pos> {
        int i, j;

        Pos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int compareTo(Pos o) {
            if (i != o.i) {
                return Integer.compare(i, o.i);
            } else {
                return Integer.compare(j, o.j);
            }
        }
    }

    boolean win1(int n, int m, int[][] g) {
        List<Pos> ps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (g[i][j] == 1) {
                    continue;
                }
                // 0
                boolean ok = true;
                for (int d = 0; d < dx.length; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                        if (g[ni][nj] == 1) {
                            ok = false;
                            break;
                        }
                    }
                }
                if (ok) {
                    ps.add(new Pos(i, j));
                }
            }
        }
        List<Integer> cnts = new ArrayList<>();
        int pn = ps.size();
        for (int i = 0; i < pn; i++) {
            Pos p = ps.get(i);
            int c = 0;
            for (int d = 0; d < dx.length; d++) {
                int ni = p.i + dx[d];
                int nj = p.j + dy[d];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    int idx = Collections.binarySearch(ps, new Pos(ni, nj));
                    if (idx >= 0) {
                        c++;
                    }
                }
            }
            cnts.add(c);
        }
        out.append('\n');
        return true;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[][] g = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < m; j++) {
                    g[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean win = win1(n, m, g);
            if (win) {
                out.append("Ashish\n");
            } else {
                out.append("Vivek\n");
            }
        }
    }

}