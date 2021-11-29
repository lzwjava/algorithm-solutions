import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int s = n + m;
        boolean win = true;
        while (s >= 2) {
            s -= 2;
            win = !win;
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
