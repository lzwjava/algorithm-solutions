import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] xs = new int[3];
        for (int i = 0; i < 3; i++) {
            xs[i] = in.nextInt();
        }
        Arrays.sort(xs);
        double p = (xs[0] + xs[2]) * 1.0 / 2;
        double s = 0;
        for (int i = 0; i < 3; i++) {
            s += Math.abs(p - xs[i]);
        }
        System.out.println((int) s);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
