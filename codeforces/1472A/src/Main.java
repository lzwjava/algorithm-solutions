import java.util.Scanner;

public class Main {

    int dp(int w, int h) {
        if (w % 2 == 1 && h % 2 == 1) {
            return 0;
        }
        int v = 0;
        if (w % 2 == 0) {
            v = Integer.max(v, dp(w / 2, h));
        }
        if (h % 2 == 0) {
            v = Integer.max(v, dp(w, h / 2));
        }
        return v;
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int w = in.nextInt();
            int h = in.nextInt();
            int n = in.nextInt();
            int p = dp(w, h);
            if (p >= n) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
