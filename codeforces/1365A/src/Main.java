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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.solve();
        m.test();
        m.close();
    }

    int n;
    int m;

    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, -1, 1};

    // Ashish win
    boolean dp(int[][] g, List<Pos> ps, boolean ashish, int level) {
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
                    g[i][j] = 1;
                    boolean result = dp(g, ps, !ashish, level + 1);
                    g[i][j] = 0;
                    ps.remove(ps.size() - 1);
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
            winPos = new ArrayList<>(ps);
            return true;
        }
    }

    List<Pos> winPos;

    boolean win(int n, int m, int[][] g) {
        this.n = n;
        this.m = m;
        winPos = new ArrayList<>();
        boolean win = dp(g, new ArrayList<>(), true, 0);
        for (Pos p : winPos) {
            out.append(String.format("%d %d\n", p.i, p.j));
        }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return i == pos.i && j == pos.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
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
        return tryCal(n, m, g, ps, true);
    }


    boolean tryCal(int n, int m, int[][] g, List<Pos> ps, boolean ashish) {
        int pn = ps.size();
        if (pn == 0) {
            if (ashish) {
                return false;
            } else {
                return true;
            }
        }
        for (int i = 0; i < pn; i++) {
            Pos p = ps.get(i);
            List<Pos> nps = new ArrayList<>();
            nps.add(p);
            for (int d = 0; d < dx.length; d++) {
                int ni = p.i + dx[d];
                int nj = p.j + dy[d];
                if (ni >= 0 && ni < n && nj >= 0 && nj < m) {
                    Pos np = new Pos(ni, nj);
                    int idx = Collections.binarySearch(ps, np);
                    if (idx >= 0) {
                        nps.add(np);
                    }
                }
            }

            List<Pos> restPos = new ArrayList<>(ps);
            restPos.removeAll(nps);
            boolean win = tryCal(n, m, g, restPos, !ashish);
            if (ashish && win) {
                return true;
            }
            if (!ashish && !win) {
                return false;
            }
        }
        if (ashish) {
            return false;
        } else {
            return true;
        }
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = 2;
            int m = 3;
            int[][] g = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    g[i][j] = random.nextInt(2);
                }
            }
            boolean win1 = win1(n, m, g);
            boolean win = win(n, m, g);
            assert (win == win1);
        }

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