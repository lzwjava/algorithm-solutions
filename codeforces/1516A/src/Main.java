import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
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

    boolean smaller(int[] x, int[] y) {
        int xn = x.length;
        int yn = y.length;
        int mn = Integer.min(xn, yn);
        for (int i = 0; i < mn; i++) {
            if (x[i] != y[i]) {
                return x[i] < y[i];
            }
        }
        return false;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        Random random = new Random();
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int[] a = parseArray(in.readLine());
            while (true) {
                int i1 = random.nextInt(n);
                int i2 = random.nextInt(n);
                if (i1 != i2 && a[i1] > 1) {
                    int[] b = a.clone();
                    b[i1]--;
                    b[i2]++;
                    boolean smaller = smaller(b, a);
                    if (smaller) {
                        a = b;
                        k--;
                        if (k == 0) {
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", a[i]));
            }
        }
    }
}