import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            int[] a = new int[n];
            StringTokenizer st = new StringTokenizer(in.readLine());
            int maxn = 1000001;
            boolean[] q = new boolean[maxn];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
                q[a[i]] = true;
            }
            int p = n / 2;
            int c = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    int min = Integer.min(a[i], a[j]);
                    int max = Integer.max(a[i], a[j]);
                    int m = max % min;
                    if (!q[m]) {
                        out.append(String.format("%d %d\n", max, min));
                        c++;
                        if (c == p) {
                            break;
                        }
                    }
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
