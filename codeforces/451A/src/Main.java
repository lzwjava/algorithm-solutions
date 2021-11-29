import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        boolean malvikaWin;
        if (n > m) {
            int t = n;
            n = m;
            m = t;
        }
        if (n == 1) {
            malvikaWin = false;
        } else {
            malvikaWin = true;
            int s = n;
            while (s >= 1) {
                s -= 1;
                malvikaWin = !malvikaWin;
            }
        }
        if (malvikaWin) {
            System.out.println("Malvika");
        } else {
            System.out.println("Akshat");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
