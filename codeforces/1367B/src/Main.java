import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt() % 2;
                if (v != i % 2) {
                    cnt++;
                }
            }
            if (cnt % 2 == 1) {
                System.out.println(-1);
            } else {
                System.out.println(cnt / 2);
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
