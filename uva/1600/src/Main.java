import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {

    private final BufferedReader in;
    private final PrintWriter out;

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
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private void solve() throws IOException {
        int c = Integer.parseInt(in.readLine());
        for (int i = 0; i < c; i++) {
            solveCase();
        }
    }
}
