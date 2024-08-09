import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    private final BufferedReader in;
    private final PrintWriter out;
    private final int[] dx = new int[]{1, 0, -1, 0};
    private final int[] dy = new int[]{0, 1, 0, -1};

    public Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    private void close() throws IOException {
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        try {
            m.solve();
        } finally {
            m.close();
        }
    }

    private void solveCase() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(in.readLine());
        int[][] grid = new int[m][n];
        int[][] dists = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                dists[i][j] = Integer.MAX_VALUE;
            }
        }


        Pos p1 = new Pos(0, 0, 0, k);
        dists[0][0] = 0;

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.add(p1);
        int ans = -1;
        while (!pq.isEmpty()) {
            Pos p = pq.poll();
            if (p.x == m - 1 && p.y == n - 1) {
                ans = p.dist;
                break;
            } else {
                for (int d = 0; d < 4; d++) {
                    int ndx = p.x + dx[d];
                    int ndy = p.y + dy[d];
                    if (ndx >= 0 && ndx < m && ndy >= 0 && ndy < n && dists[ndx][ndy] > p.dist + 1) {
                        if (grid[ndx][ndy] == 0) {
                            dists[ndx][ndy] = p.dist + 1;
                            Pos np = new Pos(ndx, ndy, p.dist + 1, k);
                            pq.add(np);
                        } else if (p.k > 0) {
                            dists[ndx][ndy] = p.dist + 1;
                            Pos np = new Pos(ndx, ndy, p.dist + 1, p.k - 1);
                            pq.add(np);
                        }
                    }
                }
            }
        }

        out.println(ans);
    }

    private void solve() throws IOException {
        int c = Integer.parseInt(in.readLine());
        for (int i = 0; i < c; i++) {
            solveCase();
        }
    }
}

class Pos implements Comparable<Pos> {
    int x;
    int y;
    int dist;
    int k;

    public Pos(int x, int y, int dist, int k) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.k = k;
    }

    @Override
    public int compareTo(Pos pos) {
        return Integer.compare(dist, pos.dist);
    }
}
