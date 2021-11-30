import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int d = Math.abs(a - b);
            int c = 0;
            if (d > 10) {
                c += (int) Math.ceil(d * 1.0 / 10);
            } else {
                c = 1;
            }
            System.out.println(c);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
