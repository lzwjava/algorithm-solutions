import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Objects;
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
        Random random = new Random();
        while (true) {
            int n = 10;
            StringBuilder sb = new StringBuilder();
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
            int len = r.end - r.start + 1;
            Result r1 = cal1(n, L, s);
            assert r.equals(r1);
            out.append(String.format("%s\n%s\n", s, rs));
            out.append('\n');
        }
    }

    class Result {
        int start, end;

        Result(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Result result = (Result) o;
            return start == result.start && end == result.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
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

    Result cal1(int n, int L, String s) {
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
        int d = L;
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
        int start = maxI;
        int end = maxI + maxD - 1;
        while (start - 1 >= 0 && a[start - 1] == 1) {
            start--;
        }
        while (end + 1 < n && a[end + 1] == 1) {
            end++;
        }
        return new Result(start, end);
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