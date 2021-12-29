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
            int[] a = new int[n];
            int mx = 0;
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                mx = Integer.max(mx, a[i]);
            }
            int i;
            for (i = n - 1; i >= 0; i--) {
                if (a[i] == mx) {
                    break;
                }
            }
            System.out.println(n - 1 - i);
        }
    }

}