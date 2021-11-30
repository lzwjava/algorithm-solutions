import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] x = new int[n];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < n; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(x);
        int q = Integer.parseInt(in.readLine());
        while (q > 0) {
            int m = Integer.parseInt(in.readLine());
            int i = Arrays.binarySearch(x, m);
            int ans;
            if (i >= 0) {
                while (i + 1 < n && x[i + 1] == x[i]) {
                    i++;
                }
                ans = i + 1;
            } else {
                i = -(i + 1);
                ans = i;
            }
            System.out.println(ans);
            q--;
        }
    }

    public static void main(String[] args) throws IOException {
        new Main().solve();
    }

}
