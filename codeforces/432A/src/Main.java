import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int v = in.nextInt();
            if (5 - v >= k) {
                cnt++;
            }
        }
        int ans = cnt / 3;
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
