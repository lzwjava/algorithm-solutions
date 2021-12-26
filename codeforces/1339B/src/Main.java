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
//        m.solve();
        m.test();
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

    void cal1(int[] a, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(a[i]);
        }
        int v = a[0];
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int d1 = Math.abs(o1 - v);
                int d2 = Math.abs(o2 - v);
                return Integer.compare(d1, d2);
            }
        });
        for (int i = 0; i < n; i++) {
            a[i] = list.get(i);
        }
    }

    void cal(int[] a, int n) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int v = a[i];
            if (list.size() < 2) {
                list.add(v);
            } else {
                boolean ok = false;
                int j;
                for (j = 1; j < list.size(); j++) {
                    if (Math.abs(v - list.get(j - 1)) <= Math.abs(v - list.get(j))) {
                        ok = true;
                        break;
                    }
                }
                if (ok) {
                    list.add(j, a[i]);
                } else {
                    if (Math.abs(v - list.get(0)) <= Math.abs(list.get(0) - list.get(1))) {
                        list.addFirst(v);
                    } else {
                        list.addLast(v);
                    }
                }
            }
            int ln = list.size();
            int[] b = new int[ln];
            for (int j = 0; j < ln; j++) {
                b[j] = list.get(j);
            }
            assert check(b, ln);
        }
        for (int i = 0; i < n; i++) {
            a[i] = list.get(i);
        }
    }

    void test() {
        Random random = new Random();
        while (true) {
            int n = 10;
            int[] a = new int[n];
            double max = 2e1;
            int maxn = (int) max;
            for (int i = 0; i < n; i++) {
                a[i] = (int) (random.nextInt(maxn) - max / 2);
            }
            cal1(a, n);
            assert check(a, n);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            cal1(a, n);
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