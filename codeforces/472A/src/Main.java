import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int maxn = 1000001;
    boolean[] isPrimes;

    void calPrimes() {
        isPrimes = new boolean[maxn];
        Arrays.fill(isPrimes, true);
        isPrimes[0] = isPrimes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            isPrimes[i] = false;
        }
        for (int i = 3; i < maxn; i += 2) {
            if (isPrimes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    isPrimes[j] = false;
                }
            }
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
