import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(in.readLine());
            }
            int[] sums = new int[n];
            int s = 0;
            for (int i = 0; i < n; i++) {
                s += nums[i];
                sums[i] = s;
            }
            int max = 0, maxi = 0, maxj = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    int t = i == 0 ? 0 : sums[i - 1];
                    int sum = sums[j] - t;
                    if (sum > max || (sum == max && j - i + 1 < maxj - maxi + 1)) {
                        max = sum;
                        maxi = i;
                        maxj = j;
                    }
                }
            }
            for (int i = maxi; i <= maxj; i++) {
                if (i != maxi) {
                    out.append(" ");
                }
                out.append(String.valueOf(nums[i]));
            }
            out.append('\n');
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
