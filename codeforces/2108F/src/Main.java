import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    int[] a;
    int n;
    int mex;

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
            mex = 0;
            permutation(p, used, 0);
            out.println(mex);
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
                int pi = b[p[i]];
                for (int j = 0; j < pi; j++) {
                    if (i + j + 1 < n) {
                        b[i + j + 1] += 1;
                    } else {
                        break;
                    }
                }
                b[p[i]] = 0;
            }
            boolean nonDecreasing = true;
            for (int i = 0; i < n - 1; i++) {
                if (b[i] > b[i + 1]) {
                    nonDecreasing = false;
                    break;
                }
            }
            if (nonDecreasing) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += b[i];
                }
                if (sum > mex) {
                    mex = sum;
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
