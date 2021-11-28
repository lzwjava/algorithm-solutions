import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        long ans = 0;
        if (n % 2 == 0) {
            ans = n / 2;
        } else {
            ans = (n - 1) / 2;
            ans -= n;
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
