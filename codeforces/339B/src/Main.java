import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[] as = new int[m];
        for (int i = 0; i < m; i++) {
            as[i] = in.nextInt();
        }
        int p = 1;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            if (as[i] == p) {
                // do
            } else {
                while (as[i] != p) {
                    p = p % n + 1;
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
