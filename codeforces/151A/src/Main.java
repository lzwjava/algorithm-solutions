import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n, k, l, c, d, p, nl, np;
        n = in.nextInt();
        k = in.nextInt();
        l = in.nextInt();
        c = in.nextInt();
        d = in.nextInt();
        p = in.nextInt();
        nl = in.nextInt();
        np = in.nextInt();
        int t1 = k * l / nl;
        int t2 = c * d;
        int t3 = p / np;
        int min = Integer.min(t1, t2);
        min = Integer.min(min, t3);
        System.out.println(min / n);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
