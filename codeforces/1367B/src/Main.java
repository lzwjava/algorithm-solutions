import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
//            int[] as = new int[n];
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                int v = in.nextInt() % 2;
                if (v != i % 2) {
                    cnt++;
                }
            }
            
            System.out.println();
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
