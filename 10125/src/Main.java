import java.io.*;
import java.util.Arrays;

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
        while (true) {
            int n = readInt();
            if (n == 0) {
                break;
            }
            int[] ns = new int[n];
            for (int i = 0; i < n; i++) {
                ns[i] = readInt();
            }
            Arrays.sort(ns);
            boolean found = false;
            for (int d = n - 1; d >= 0; d--) {
                for (int c = 0; c < n; c++) {
                    if (c == d) {
                        continue;
                    }
                    for (int b = 0; b < n; b++) {
                        if (b == c || b == d) {
                            continue;
                        }
                        int a = ns[d] - ns[c] - ns[b];
                        if (a != ns[d] && a != ns[c] && a != ns[b] && Arrays.binarySearch(ns, a) >= 0) {
                            out.append(String.format("%d\n", ns[d]));
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (!found) {
                out.append("no solution\n");
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
