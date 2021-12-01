import java.util.Scanner;

public class Main {

    int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int ans;
            if (n % 2 == 0) {
                ans = n / 2;
            } else {
                ans = (n - 1) / 2;
            }
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
