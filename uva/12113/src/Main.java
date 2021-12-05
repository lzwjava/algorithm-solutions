import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    char[][] grid;

    void draw(char[][] g, int v) {
        char[][] gt = new char[][]{
            {' ', '_', ' ', '_', ' '},
            {'|', ' ', ' ', ' ', '|'},
            {'|', '_', ' ', '_', '|'}
        };
        int ox = v / 3;
        int oy = (v % 3) * 2;
        for (int x = 0; x < gt.length; x++) {
            for (int y = 0; y < gt[0].length; y++) {
                if (x == 0 && (y == 0 || y == 4)) {
                    continue;
                }
                int nx = ox + x;
                int ny = oy + y;
                g[nx][ny] = gt[x][y];
            }
        }
    }

    boolean equal(char[][] g1, char[][] g2) {
        for (int i = 0; i < g1.length; i++) {
            for (int j = 0; j < g1[0].length; j++) {
                if (g1[i][j] != g2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    void print(char[][] g) {
        for (int x = 0; x < g.length; x++) {
            for (int y = 0; y < g[0].length; y++) {
                out.append(g[x][y]);
            }
            out.append('\n');
        }
        out.append('\n');
    }

    boolean permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (cur == n) {
            char[][] g = new char[5][9];
            for (int i = 0; i < 5; i++) {
                Arrays.fill(g[i], ' ');
            }
            for (int i = 0; i < n; i++) {
                draw(g, nums[i]);
            }
            if (equal(grid, g)) {
                return true;
            }
            return false;
        }
        for (int i = 0; i < 9; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                boolean found = permutation(nums, vis, cur + 1, n);
                if (found) {
                    return true;
                }
            }
        }
        return false;
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String line = in.readLine();
            if (line.charAt(0) == '0') {
                break;
            }
            int i = 0;
            grid = new char[5][9];
            do {
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = line.charAt(j);
                }
                i++;
                if (i != 5) {
                    line = in.readLine();
                }
            } while (i < 5);
            boolean ok = false;
            for (int p = 1; p <= 6; p++) {
                int[] nums = new int[p];
                boolean[] vis = new boolean[9];
                boolean found = permutation(nums, vis, 0, p);
                if (found) {
                    ok = true;
                    break;
                }
            }
            out.append(String.format("Case %d: ", caseNum));
            if (ok) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
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