import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int d = 180 - a;
            if (d > 120) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
