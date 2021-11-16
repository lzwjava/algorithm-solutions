import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            int n = s.length();
            int ans = 0;
            if (n == 0) {
                ans = 0;
            } else {
                int[][] dp = new int[n][n];
                for (int i = 0; i < n; i++) {
                    dp[i][i] = 1;
                }
                for (int i = 0; i < n - 1; i++) {
                    dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1) ? 2 : 1;
                }
                for (int len = 2; len < n; len++) {
                    for (int i = 0; i + len < n; i++) {
                        int j = i + len;
                        if (s.charAt(i) == s.charAt(j)) {
                            dp[i][j] = 2 + dp[i + 1][j - 1];
                        } else {
                            dp[i][j] = Integer.max(dp[i + 1][j], dp[i][j - 1]);
                        }
                    }
                }
                ans = dp[0][n - 1];
            }
            out.append(String.format("%d\n", ans));
            t--;
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
            inStream = new FileInputStream("2.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
