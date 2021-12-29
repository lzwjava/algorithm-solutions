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

    public static void main(String[] args) throws IOException {
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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
            List<Integer> list = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            StringTokenizer st = new StringTokenizer(in.readLine());
            for (int i = 0; i < n; i++) {
                int v = Integer.parseInt(st.nextToken());
                list.add(v);
                set.add(v);
            }
            boolean ok = false;
            if (!list.get(0).equals(list.get(n - 1))) {
                set = new HashSet<>();
                set.add(list.get(0));
                set.add(list.get(n - 1));
            }
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