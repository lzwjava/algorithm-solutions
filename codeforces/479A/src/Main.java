import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] ns = new int[3];
        int s = 0;
        for (int i = 0; i < 3; i++) {
            ns[i] = in.nextInt();
            s += ns[i];
        }
        int[] cs = ns.clone();
        Arrays.sort(cs);
        int ans;
        if (cs[2] == 1) {
            ans = s;
        } else if (cs[0] > 1) {
            ans = cs[0] * cs[1] * cs[2];
        } else {
            ans = Integer.max((ns[0] + ns[1]) * ns[2], ns[0] * (ns[1] + ns[2]));
            ans = Integer.max(ans, s);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
