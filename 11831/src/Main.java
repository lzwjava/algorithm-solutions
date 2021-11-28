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

    void dfs(int sx, int sy, int d, char[] op, int cur) {
        if (cur == op.length) {
            return;
        }
    }

    int n, m, s;
    char[][] grid;

    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0 && s == 0) {
                break;
            }
            grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                for (int j = 0; j < m; j++) {
                    grid[i][j] = line.charAt(j);
                }
            }
            String ins = in.readLine();
            char[] op = new char[s];
            for (int i = 0; i < s; i++) {
                op[i] = ins.charAt(i);
            }
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
