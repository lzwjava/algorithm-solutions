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

    void solve() throws IOException {
        int[][] grid = new int[7][8];
        for (int i = 0; i < 7; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int j = 0; j < 8; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
//        System.setOut(new PrintStream(new FileOutputStream("1.out")));
        Main m = new Main();
        m.solve();
        m.close();
    }
}