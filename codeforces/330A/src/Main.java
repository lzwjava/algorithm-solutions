import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] grid = new char[r][c];
        for (int i = 0; i < r; i++) {
            String s = in.readLine();
            for (int j = 0; j < c; j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        boolean[][] used = new boolean[r][c];
        int s = 0;
        for (int i = 0; i < r; i++) {
            boolean ok = true;
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == 'S') {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                s += c;
                for (int j = 0; j < c; j++) {
                    used[i][j] = true;
                }
            }
        }
        for (int j = 0; j < c; j++) {
            boolean ok = true;
            int notUsed = 0;
            for (int i = 0; i < r; i++) {
                if (grid[i][j] == 'S') {
                    ok = false;
                    break;
                }
                if (!used[i][j]) {
                    notUsed++;
                }
            }
            if (ok) {
                s += notUsed;
            }
        }
        out.append(String.format("%d\n", s));
    }
}