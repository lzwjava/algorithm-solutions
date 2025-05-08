import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    int[] a;
    int n;
    int maxMEX;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(in.readLine());
            a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int[] p = new int[n];
            boolean[] used = new boolean[n];
            maxMEX = 0;
            permutation(p, used, 0);
            out.println(maxMEX);
        }
    }

    void printArray(int[] p) {
        for (int i = 0; i < p.length; i++) {
            out.print(p[i] + " ");
        }
        out.println();
    }

    void permutation(int[] p, boolean[] used, int cur) {
        if (cur == n) {
            int[] b = a.clone();
            for (int i = 0; i < n; i++) {
                int pos = p[i];
                int bpi = b[pos];
                for (int j = 0; j < bpi; j++) {
                    if (pos + j + 1 < n) {
                        b[pos + j + 1] += 1;
                    } else {
                        break;
                    }
                }
                b[pos] = 0;
            }
            boolean nonDecreasing = true;
            for (int i = 0; i < n - 1; i++) {
                if (b[i] > b[i + 1]) {
                    nonDecreasing = false;
                    break;
                }
            }

            if (nonDecreasing) {
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < n; i++) {
                    set.add(b[i]);
                }
                int mex;
                for (mex = 1; ; mex++) {
                    if (!set.contains(mex)) {
                        break;
                    }
                }
                if (mex > maxMEX) {
                    maxMEX = mex;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!used[i]) {
                    used[i] = true;
                    p[cur] = i;
                    permutation(p, used, cur + 1);
                    used[i] = false;
                }
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
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();
    }
}
