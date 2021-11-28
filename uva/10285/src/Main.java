import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int r, c;
    int[][] grid;
    int[][] fa;

    int[] dx = {-1, 0, 0, 1,};
    int[] dy = {0, -1, 1, 0};

    int max;

    void print(int x, int y) {
        if (fa[x][y] != x * r + y) {
            int fx = fa[x][y] / r;
            int fy = fa[x][y] % r;
            print(fx, fy);
        }
        out.append(String.format("%d ", grid[x][y]));
    }

    void dfs(boolean[][] vis, int x, int y, int len) {
        if (len > max) {
            max = len;
//            print(x, y);
//            out.append('\n');
        }
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < r && ny >= 0 && ny < c && grid[nx][ny] < grid[x][y]) {
                vis[nx][ny] = true;
                fa[nx][ny] = x * r + y;
                dfs(vis, nx, ny, len + 1);
                vis[nx][ny] = false;
            }
        }
    }

    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String name = st.nextToken();
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            grid = new int[r][c];
            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < c; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            max = 0;
            boolean[][] vis = new boolean[r][c];
            fa = new int[r][c];
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    vis[i][j] = true;
                    fa[i][j] = i * r + j;
                    dfs(vis, i, j, 1);
                    vis[i][j] = false;
                }
            }
            out.append(String.format("%s: %d\n", name, max));
            n--;
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
