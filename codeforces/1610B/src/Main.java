import java.io.PrintWriter;
import java.util.*;

public class Main {

    Scanner in;
    PrintWriter out;

    Main() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.close();
    }

    void close() {
        out.flush();
        out.close();
    }

    boolean palindrome(List<Integer> list) {
        int m = list.size();
        for (int i = 0; i < m; i++) {
            int a = list.get(i);
            int b = list.get(m - 1 - i);
            if (a != b) {
                return false;
            }
        }
        return true;
    }

    void solve() {
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int v = in.nextInt();
                list.add(v);
            }
            Set<Integer> set = new HashSet<>();
            set.add(list.get(0));
            set.add(list.get(n - 1));
            boolean ok = false;
            for (int x : set) {
                List<Integer> tl = new ArrayList<>(list);
                tl.removeIf(integer -> integer == x);
                if (palindrome(tl)) {
                    ok = true;
                    break;
                }
            }
            if (ok) {
                out.append("YES\n");
            } else {
                out.append("NO\n");
            }
        }
    }
}