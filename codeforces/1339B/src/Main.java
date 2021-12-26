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
//        m.test();
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

    void cal(int[] a, int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(a[i]);
        }
        Collections.sort(list);
        int left = n / 2;
        List<Integer> leftList = list.subList(0, left);
        List<Integer> rightList = list.subList(left, n);
        Collections.sort(leftList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        Collections.sort(rightList);

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n / 2; i++) {
            result.add(rightList.get(i));
            result.add(leftList.get(i));
        }
        if (n % 2 == 1) {
            result.add(rightList.get(rightList.size() - 1));
        }
        for (int i = 0; i < n; i++) {
            a[i] = result.get(i);
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
            cal(a, n);
            assert check(a, n);
        }
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            int[] a = parseArray(in.readLine());
            cal(a, n);
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