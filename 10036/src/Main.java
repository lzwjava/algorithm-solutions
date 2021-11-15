import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n, k;
    int[] nums;
    int[] ds;

    void solve() throws IOException {
        int m = Integer.parseInt(in.readLine());
        while (m > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            nums = new int[n];
            st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            ds = new int[n];
            for (int i = 0; i < n; i++) {
                ds[i] = (nums[i] % k + k) % k;
            }
            boolean[][] d = new boolean[n][k];
            d[0][ds[0]] = true;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < k; j++) {
                    if (d[i - 1][j]) {
                        d[i][(j + ds[i] + k) % k] = true;
                        d[i][(j - ds[i] + k) % k] = true;
                    }
                }
            }

            if (d[n - 1][0]) {
                out.append("Divisible\n");
            } else {
                out.append("Not divisible\n");
            }
            m--;
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
