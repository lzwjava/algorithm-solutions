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
        int x = (v / 3) * 2;
        int y = v % 3;
    }

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (cur == n) {
            char[][] g = new char[5][9];
            for (int i = 0; i < 5; i++) {
                Arrays.fill(g[i], ' ');
            }
            for (int i = 0; i < n; i++) {
                draw(g, nums[i]);
            }
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                permutation(nums, vis, cur + 1, n);
            }
        }
    }

    void solve() throws IOException {
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
            for (int p = 1; p <= 6; p++) {

            }
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