import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] s = new int[n];
            for (int i = 0; i < n; i++) {
                s[i] = in.nextInt();
            }
            Arrays.sort(s);
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < n - 1; i++) {
                int d = s[i + 1] - s[i];
                if (d < min) {
                    min = d;
                    if (min == 0) {
                        break;
                    }
                }
            }
            System.out.println(min);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
