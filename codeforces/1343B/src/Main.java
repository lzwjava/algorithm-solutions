import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int d = n / 2;
            if (d % 2 == 1) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
                int[] as = new int[n];
                int p = 2;
                for (int i = 0; i < d; i++) {
                    as[i] = p;
                    p += 2;
                }
                p = 1;
                for (int i = 0; i < d - 1; i++) {
                    as[d + i] = p;
                    p += 2;
                }
                as[n - 1] = p + d;
                for (int i = 0; i < n; i++) {
                    if (i != 0) {
                        System.out.print(' ');
                    }
                    System.out.print(as[i]);
                }
                System.out.println();
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
