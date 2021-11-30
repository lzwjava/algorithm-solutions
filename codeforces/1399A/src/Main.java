import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] as = new int[n];
            for (int i = 0; i < n; i++) {
                as[i] = in.nextInt();
            }
            Arrays.sort(as);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
