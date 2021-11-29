import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] hs = new int[n];
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            hs[i] = in.nextInt();
            as[i] = in.nextInt();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    if (hs[i] == as[j]) {
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
