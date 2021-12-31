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

    void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    boolean check(int[] a) {
        int n = a.length;
        boolean ok = true;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] > a[i + 1]) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        Random random = new Random();
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            int[] b = parseArray(in.readLine());
            int cnt = 0;
            while (cnt < 1000) {
                int i = random.nextInt(n);
                int j = random.nextInt(n);
                if (a[i] > a[j] && b[i] != b[j]) {
                    swap(a, i, j);
                    swap(b, i, j);
                }
                if (check(a)) {
                    break;
                }
                cnt++;
            }
            if (check(a)) {
                out.append("Yes\n");
            } else {
                out.append("No\n");
            }
        }
    }
}