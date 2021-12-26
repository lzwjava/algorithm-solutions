import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

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

    boolean check(int[] a, int n) {
        int last = -1;
        for (int i = 0; i < n - 1; i++) {
            int d = Math.abs(a[i + 1] - a[i]);
            if (last > d) {
                return false;
            }
            last = d;
        }
        return true;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        Random random = new Random();
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            while (!check(a, n)) {
                Set<Integer> set = new HashSet<>();
                while (set.size() != 3) {
                    int ri = random.nextInt(n);
                    set.add(ri);
                }
                List<Integer> list = new ArrayList<>(set);
                int l0 = list.get(0);
                int l1 = list.get(1);
                int l2 = list.get(2);
                if (Math.abs(a[l0] - a[l1]) > Math.abs(a[l1] - a[l2])) {
                    int ta = a[l0];
                    a[l0] = a[l2];
                    a[l2] = ta;
                }
            }
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                out.append(String.format("%d", a[i]));
            }
            out.append('\n');
        }
    }

}