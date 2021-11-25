import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    int[] nums;
    int max;

    void calLIS(int[] nums, int[] lis) {
        int n = nums.length;
        int[] top = new int[n];
        int piles = 0;
        for (int i = 0; i < n; i++) {
            int poker = nums[i];

            int left = Arrays.binarySearch(top, 0, piles, poker);
            if (left < 0) {
                left = -(left + 1);
            }
            if (left == piles) {
                piles++;
            }
            top[left] = poker;
            lis[i] = left + 1;
        }
    }

    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            n = Integer.parseInt(line);
            nums = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            ArrayList<Integer> parts = new ArrayList<>();


            max = 0;

            out.append(String.format("%d\n", max));
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
            inStream = new FileInputStream("3.in");
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
