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

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
//        m.solve();
        m.test();
        m.close();
    }

    int n, L;
    int[] a;
    int[] sums;

    void test() {
        int n = 5;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%d", random.nextInt(2)));
        }
        String s = sb.toString();
        int L = random.nextInt(n) + 1;
        Result r = cal(n, L, s);
        StringBuilder rsb = new StringBuilder();
        for (int i = r.start; i <= r.end; i++) {
            rsb.append(String.format("%c", s.charAt(i)));
        }
        String rs = rsb.toString();
        out.append(String.format("%s\n%s\n", s, rs));
    }

    class Result {
        int start, end;

        Result(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    Result cal(int n, int L, String s) {
        this.n = n;
        this.n = L;
        a = new int[n];
        int sum = 0;
        sums = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.charAt(i) - '0';
            sum += a[i];
            sums[i] = sum;
        }
        double maxAvg = 0;
        int maxD = 0, maxI = 0;
        for (int d = L; d <= n; d++) {
            // j-i+1=d
            // n-1-i+1=d
            // n-i=d
            // i=n-d
            for (int i = 0; i <= n - d; i++) {
                int j = i + d - 1;
                int si = sum(i, j);
                double avg = si * 1.0 / d;
                if (Double.compare(avg, maxAvg) > 0) {
                    maxAvg = avg;
                    maxD = d;
                    maxI = i;
                }
            }
        }
        return new Result(maxI, maxI + maxD - 1);
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            n = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            String s = in.readLine();
            Result r = cal(n, L, s);
            out.append(String.format("%d %d\n", r.start + 1, r.end + 1));
        }
    }

    int sum(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }

}