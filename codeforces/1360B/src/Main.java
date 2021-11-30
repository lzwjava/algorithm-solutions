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
            
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
