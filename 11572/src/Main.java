import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    int[] vs;

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            n = Integer.parseInt(in.readLine());
            vs = new int[n];
            for (int i = 0; i < n; i++) {
                vs[i] = Integer.parseInt(in.readLine());
            }
            int ans;
            if (n == 0) {
                ans = 0;
            } else {
                Set<Integer> set = new HashSet<Integer>();
                int u = 0;
                set.add(vs[0]);
                ans = 1;
                for (int i = 1; i < n; i++) {
                    while (true) {
                        boolean contains = set.contains(vs[i]);
                        if (contains) {
                            set.remove(vs[u]);
                            u++;
                        } else {
                            break;
                        }
                    }
                    set.add(vs[i]);
                    if (set.size() > ans) {
                        ans = set.size();
                    }
                }
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
            inStream = new FileInputStream("1.in");
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
