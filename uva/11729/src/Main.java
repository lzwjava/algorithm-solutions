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

    class Soldier implements Comparable<Soldier> {
        int b;
        int j;

        Soldier(int b, int j) {
            this.b = b;
            this.j = j;
        }

        @Override
        public int compareTo(Soldier o) {
            // do this, then o
            int u = Integer.max(b + j, b + o.b + o.j);
            // do o, then this
            int v = Integer.max(o.b + o.j, o.b + b + j);
            return Integer.compare(u, v);
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
            Arrays.sort(soldiers);
            int max = 0;
            int p = 0;
            for (int i = 0; i < n; i++) {
                Soldier s = soldiers[i];
                int a = p + s.b + s.j;
                if (a > max) {
                    max = a;
                }
                p += s.b;
            }
            out.append(String.format("Case %d: %d\n", caseNum, max));
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
