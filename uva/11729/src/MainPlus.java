import java.io.*;
import java.util.StringTokenizer;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    class Soldier implements Comparable<Soldier> {
        int b;
        int j;

        Soldier(int b, int j) {
            this.b = b;
            this.j = j;
        }

        @Override
        public int compareTo(Soldier o) {
            return Integer.compare(o.b + o.j, b + j);
        }
    }

    int min;

    void permutation(int[] nums, boolean[] vis, int cur, int n) {
        if (cur == n) {
            int max = 0;
            int p = 0;
            for (int i = 0; i < n; i++) {
                Soldier s = soldiers[nums[i]];
                int a = p + s.b + s.j;
                if (a > max) {
                    max = a;
                }
                p += s.b;
            }
            if (max < min) {
                min = max;
            }
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                nums[cur] = i;
                vis[i] = true;
                permutation(nums, vis, cur + 1, n);
                vis[i] = false;
            }
        }
    }

    Soldier[] soldiers;
    int n;

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            n = Integer.parseInt(in.readLine().trim());
            if (n == 0) {
                break;
            }
            soldiers = new Soldier[n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int b = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                soldiers[i] = new Soldier(b, j);
            }
            boolean[] vis = new boolean[n];
            int[] nums = new int[n];
            min = Integer.MAX_VALUE;
            permutation(nums, vis, 0, n);
            out.append(String.format("Case %d: %d\n", caseNum, min));
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

        MainPlus main = new MainPlus();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
