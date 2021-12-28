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

    int[] parseArray(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        return a;
    }

    int sum(int[] a) {
        int s = 0;
        for (int i = 0; i < a.length; i++) {
            s += a[i];
        }
        return s;
    }

    void solve() throws IOException {
        int[] a = parseArray(in.readLine());
        int[] b = parseArray(in.readLine());
        int n = Integer.parseInt(in.readLine());
        int sa = sum(a);
        int sb = sum(b);
        int da = (sa - 1) / 5 + 1;
        int db = (sb - 1) / 10 + 1;
        if (da + db <= n) {
            out.append("YES\n");
        } else {
            out.append("NO\n");
        }
    }

}