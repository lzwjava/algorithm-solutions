import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

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
    int ans;
    List<Move> ansMoves;
    Set<Integer> set;
    boolean[][] vis;

    class Move {
        int a, b;

        Move(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    class State {
        int[] st;
        int dist;
        List<Move> moves;

        State(int[] st, int dist, List<Move> moves) {
            this.st = st;
            this.dist = dist;
            this.moves = moves;
        }
    }

    void bfs(int[] st) {
        Queue<State> queue = new ArrayBlockingQueue<>(5000);
        set = new HashSet<>();
        queue.add(new State(st, 0, new ArrayList<>()));
        set.add(key(st));
        ans = -1;
        ansMoves = new ArrayList<>();
        vis = new boolean[16][1 << 15];
        while (!queue.isEmpty()) {
            State state = queue.poll();
            for (int i = 0; i < n; i++) {
                if (state.st[i] != 0) {
                    for (int j = 0; j < n; j++) {
                        if (grid[i][j] && state.st[j] == 0) {
                            int[] nst = state.st.clone();
                            swap(nst, i, j);
                            int robotPos = robotPos(nst);
                            int obstacle = obstacle(nst);
                            if (!set.contains(key)) {
                                set.add(key);
                                List<Move> nmoves = new ArrayList<>(state.moves);
                                nmoves.add(new Move(i, j));
                                if (nst[t] == 1) {
                                    ans = state.dist + 1;
                                    ansMoves = new ArrayList<>(nmoves);
                                    break;
                                }
                                queue.add(new State(nst, state.dist + 1, nmoves));
                            }
                        }
                    }
                    if (ans != -1) {
                        break;
                    }
                }
            }
            if (ans != -1) {
                break;
            }
        }
    }

    int obstacle(int[] nst) {
        int v = 0;
        for (int i = 0; i < nst.length; i++) {
            if (nst[i] == 2) {
                v += 1 << i;
            }
        }
        return v;
    }

    int robotPos(int[] nst) {
        for (int i = 0; i < nst.length; i++) {
            if (nst[i] == 1) {
                return i;
            }
        }
        return -1;
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
                os[i] = Integer.parseInt(st.nextToken()) - 1;
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
            bfs(state);
            out.append(String.format("Case %d: %d\n", p + 1, ans));
            for (Move m : ansMoves) {
                out.append(String.format("%d %d\n", m.a + 1, m.b + 1));
            }
            out.append('\n');
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