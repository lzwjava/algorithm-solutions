import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n - 1; i++) {
            a[i + 1] = in.nextInt();
        }
        int p = 1;
        boolean ans = false;
        while (p <= n) {
            if (p == t) {
                ans = true;
                break;
            }
            if (p > t) {
                ans = false;
                break;
            }
            int np = p + a[p];
            p = np;
        }
        if (ans) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
