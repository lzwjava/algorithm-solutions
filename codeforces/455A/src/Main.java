import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] as = new int[n];
        for (int i = 0; i < n; i++) {
            as[i] = in.nextInt();
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Integer c = map.get(as[i]);
            if (c == null) {
                c = 0;
            }
            c++;
            map.put(as[i], c);
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
