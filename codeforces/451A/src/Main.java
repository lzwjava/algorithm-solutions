import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        boolean win = true;
        if (n > m) {
            int t = n;
            n = m;
            m = t;
        }
        if (n == 1) {
            win = false;
        } else {
            int s = n + m;
            while (s >= 2) {
                s -= 2;
                win = !win;
            }
        }
        if (win) {
            System.out.println("Malvika");
        } else {
            System.out.println("Akshat");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
