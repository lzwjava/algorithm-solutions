import java.io.*;
import java.util.Arrays;
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

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            if (n == 0 && q == 0) {
                break;
            }
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = readInt();
            }
            Arrays.sort(nums);
            out.append(String.format("SET %d:\n", caseNum));
            for (int i = 0; i < q; i++) {
                st = new StringTokenizer(in.readLine());
                int d = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());
                int[] ns = new int[n];
                for (int j = 0; j < n; j++) {
                    ns[j] = nums[j] % d;
                }
                
            }
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
            inStream = new FileInputStream("2.in");
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
