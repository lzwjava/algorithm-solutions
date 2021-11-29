import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] ns = new int[3];
        for (int i = 0; i < 3; i++) {
            ns[i] = in.nextInt();
        }
        int[] cs = ns.clone();
        Arrays.sort(cs);
        int ans;
        if (cs[2] == 1) {
            ans = 3;
        } else if (cs[0] > 1) {
            ans = cs[0] * cs[1] * cs[2];
        } else {
            ans = Integer.max((ns[0] + ns[1]) * ns[2], ns[0] * (ns[1] + ns[2]));
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
