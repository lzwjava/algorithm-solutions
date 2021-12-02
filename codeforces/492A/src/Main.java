import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int maxn = 100;
        int[] p = new int[maxn];
        for (int i = 0; i < maxn; i++) {
            int d = i + 1;
            p[i] = (1 + d) * d / 2;
        }
        int i = 0;
        while (true) {
            if (n - p[i] >= 0) {
                n -= p[i];
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
