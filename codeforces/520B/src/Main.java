import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int c = 0;
        if (n < m) {
            while (n * 2 < m) {
                n *= 2;
                c++;
            }
            if (n * 2 == m) {
                c++;
            } else {
                while ((n - 1) * 2 >= m) {
                    n--;
                    c++;
                }
                n *= 2;
                c++;
                while (n > m) {
                    n--;
                    c++;
                }
            }
        } else {
            while (n > m) {
                n--;
                c++;
            }
        }
        System.out.print(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
