import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int c2 = 0;
            while (n % 2 == 0) {
                n /= 2;
                c2++;
            }
            int c3 = 0;
            while (n % 3 == 0) {
                n /= 3;
                c3++;
            }
            if (n != 1 || c2 > c3) {
                System.out.println(-1);
            } else {
                int c = 0;
                if (c3 > c2) {
                    c = c3 - c2;
                }
                System.out.println(c3 + c);
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
