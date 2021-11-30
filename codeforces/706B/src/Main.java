import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = in.nextInt();
        }
        Arrays.sort(x);
        int q = in.nextInt();
        while (q > 0) {
            int m = in.nextInt();
            int i = Arrays.binarySearch(x, m);
            int ans;
            if (i >= 0) {
                ans = i + 1;
            } else {
                i = -(i + 1);
                ans = i;
            }
            System.out.println(ans);
            q--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
