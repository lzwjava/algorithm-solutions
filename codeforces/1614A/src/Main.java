import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n = in.nextInt();
            int l = in.nextInt();
            int r = in.nextInt();
            int k = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            Arrays.sort(a);
            int s = 0;
            int c = 0;
            for (int i = 0; i < n; i++) {
                if (a[i] >= l && a[i] <= r) {
                    if (s + a[i] <= k) {
                        s += a[i];
                        c++;
                    } else {
                        break;
                    }
                }
            }
            System.out.println(c);
        }
    }

}