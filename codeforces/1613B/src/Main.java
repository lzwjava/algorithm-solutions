import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            Set<Integer> set = new HashSet<>();
            PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                set.add(a[i]);
                queue.add(a[i]);
            }
            int p = n / 2;
            int c = 0;
            while (!queue.isEmpty()) {
                int x = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (x > a[i]) {
                        int m = x % a[i];
                        if (!set.contains(m)) {
                            out.append(String.format("%d %d\n", x, a[i]));
                            c++;
                            if (c == p) {
                                break;
                            }
                        }
                    }
                }
                if (c == p) {
                    break;
                }
            }
            t--;
        }
        in.close();
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

}
