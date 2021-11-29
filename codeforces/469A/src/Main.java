import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int[] ps = new int[n];
        for (int i = 0; i < p; i++) {
            ps[i] = in.nextInt();
        }
        int q = in.nextInt();
        int[] qs = new int[q];
        for (int i = 0; i < q; i++) {
            qs[i] = in.nextInt();
        }
        
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
