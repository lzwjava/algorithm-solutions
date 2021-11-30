import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int ans;
        if (b >= a * m) {
            // b/m >=a
            ans = n * a;
        } else {
            if (n < m) {
                ans = n * a;
            } else {
                int d = n / m;
                ans = b * d + (n % m) * a;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
