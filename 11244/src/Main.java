import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    int r, c;
    char grid[][];

    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    int dfs(boolean[][] vis, int x, int y) {
        int cnt = 1;
        vis[x][y] = true;
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < r && ny >= 0 && ny < c && !vis[nx][ny] && grid[nx][ny] == '*') {
                cnt += dfs(vis, nx, ny);
            }
        }
        return cnt;
    }

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            if (r == 0 && c == 0) {
                break;
            }
            grid = new char[r][c];
            for (int i = 0; i < r; i++) {
                String s = in.readLine();
                for (int j = 0; j < c; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            boolean[][] vis = new boolean[r][c];
            int cnt = 0;
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (!vis[i][j] && grid[i][j] == '*') {
                        int sub = dfs(vis, i, j);
                        if (sub == 1) {
                            cnt++;
                        }
                    }
                }
            }
            out.append(String.format("%d\n", cnt));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
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
