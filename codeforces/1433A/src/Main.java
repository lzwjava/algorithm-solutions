import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int x = in.nextInt();
            int d = x % 10;
            int a = String.format("%d", x).length();
            int ans = (d - 1) * 10 + a * (a + 1) / 2;
            System.out.println(ans);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
