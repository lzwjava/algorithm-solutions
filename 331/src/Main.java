import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int total;

    void permutation(int[] arr, int[] nums, int[] sorted, int cur, int cnt, int n) {
        if (cur == cnt) {
            int[] ns = nums.clone();
            for (int i = 0; i < cnt; i++) {
                int x = arr[i];
                int tmp = ns[x];
                ns[x] = ns[x + 1];
                ns[x + 1] = tmp;
            }
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (sorted[i] != ns[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                total++;
            }
            return;
        }
        for (int i = 0; i < n - 1; i++) {
            arr[cur] = i;
            permutation(arr, nums, sorted, cur + 1, cnt, n);
        }
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int[] sorted = nums.clone();
            int cnt = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (sorted[i] > sorted[j]) {
                        int tmp = sorted[i];
                        sorted[i] = sorted[j];
                        sorted[j] = tmp;
                        cnt++;
                    }
                }
            }
            int[] arr = new int[cnt];
            total = 0;
            if (cnt == 0) {
                total = 0;
            } else {
                permutation(arr, nums, sorted, 0, cnt, n);
            }
            out.append(String.format("There are %d swap maps for input data set %d.\n", total, caseNum));
            caseNum++;
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
