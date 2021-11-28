import java.io.*;

public class Main {

    BufferedReader in;
    PrintWriter out;

    static int maxn = 100005;

    int[][] ch;
    int cnt;
    int[] val;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void insert(String str) {
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - '0';
            if (ch[u][k] == 0) {
                ch[u][k] = cnt;
                cnt++;
            }
            u = ch[u][k];
        }
        val[u]++;
    }

    boolean check(String str) {
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - '0';
            if (val[u] > 0) {
                return false;
            }
            u = ch[u][k];
        }
        if (val[u] > 1) {
            return false;
        }
        return true;
    }

    int query(String str) {
        int u = 0;
        for (int i = 0; i < str.length(); i++) {
            int k = str.charAt(i) - '0';
            if (ch[u][k] == 0) {
                return -1;
            }
            u = ch[u][k];
        }
        return val[u];
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            String[] vs = new String[n];
            for (int i = 0; i < n; i++) {
                vs[i] = in.readLine();
            }
            boolean consistent = true;
            ch = new int[maxn][10];
            val = new int[maxn];
            cnt = 1;
            for (int i = 0; i < n; i++) {
                insert(vs[i]);
            }
            for (int i = 0; i < n; i++) {
                boolean check = check(vs[i]);
                if (!check) {
                    consistent = false;
                    break;
                }
                insert(vs[i]);
            }
            if (consistent) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
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
