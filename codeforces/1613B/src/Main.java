import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
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
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            int p = n / 2;
            Arrays.sort(a);
            int c = 0;
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int m = a[i] % a[j];
                        int index = Arrays.binarySearch(a, m);
                        if (index < 0) {
                            out.append(String.format("%d %d\n", a[i], a[j]));
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
        out.flush();
        out.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

}
