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

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int[] a = new int[n];
        int[] sums = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sum += a[i];
            sums[i] = sum;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            if (a[j] > t) {
                continue;
            }
            while (j + 1 < n && sum(sums, i, j + 1) <= t) {
                j++;
            }
            int len = j - i + 1;
            if (len > max) {
                max = len;
            }
        }
        out.append(String.format("%d\n", max));
    }

    int sum(int[] sums, int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}