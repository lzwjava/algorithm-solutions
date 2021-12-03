import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int n;
    int min;
    int maxn = 16;
    int[][] g;
    int[] gmask;

    void judge(int open) {
        
    }

    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) {
                break;
            }
            g = new int[maxn][maxn];
            gmask = new int[maxn];
            while (st.hasMoreTokens()) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if (a == -1 && b == -1) {
                    break;
                }
                a--;
                b--;
                g[a][b] = g[b][a] = 1;
                gmask[a] |= 1 << b;
                gmask[b] |= 1 << a;
            }
            min = Integer.MAX_VALUE;
            for (int i = 0; i < 1 << n; i++) {
                int op = Integer.bitCount(i);
                if (op >= min) {
                    continue;
                }
                if (judge(i)) {
                    min = Integer.min(min, op);
                }
            }
            out.append(String.format("Set %d: Minimum links to open is %d\n", caseNum, min));
            caseNum++;
        }
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }
}