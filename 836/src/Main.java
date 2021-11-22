import java.io.*;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        in.readLine();
        while (t > 0) {
            ArrayList<String> list = new ArrayList<String>();
            while (true) {
                String s = in.readLine();
                if (s == null || s.isEmpty()) {
                    break;
                }
                list.add(s);
            }
            int n = list.size();
            int m = list.get(0).length();
            char[][] grid = new char[n][m];
            for (int i = 0; i < n; i++) {
                String s = list.get(i);
                for (int j = 0; j < m; j++) {
                    grid[i][j] = s.charAt(j);
                }
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    for (int u = 0; u < m; u++) {
                        for (int v = u; v < m; v++) {
                            boolean ok = true;
                            for (int p = i; p <= j; p++) {
                                for (int q = u; q <= v; q++) {
                                    if (grid[p][q] != '1') {
                                        ok = false;
                                        break;
                                    }
                                }
                                if (!ok) {
                                    break;
                                }
                            }
                            if (ok) {
                                int area = (j - i + 1) * (v - u + 1);
                                if (area > ans) {
                                    ans = area;
                                }
                            }
                        }
                    }
                }
            }
            out.append(String.format("%d\n", ans));
            t--;
            if (t != 0) {
                out.append('\n');
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
