import java.util.Scanner;

public class Main {

    int dp(int n, int m) {
        if (n >= m) {
            return n - m;
        }
        
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        dp(n, m);
        System.out.print(c);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
