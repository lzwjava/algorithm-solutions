import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int[] s = new int[4];
            for (int i = 0; i < 4; i++) {
                s[i] = in.nextInt();
            }
            int[] a = s.clone();
            Arrays.sort(a);
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
