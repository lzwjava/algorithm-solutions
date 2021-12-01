import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    ArrayList<Integer> list;

    void calPrimes() {
        int maxn = 10000001;
        boolean[] primes = new boolean[maxn];
        Arrays.fill(primes, true);
        primes[0] = primes[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            primes[i] = false;
        }
        int sm = (int) Math.sqrt(maxn);
        for (int i = 3; i <= sm; i += 2) {
            if (primes[i]) {
                for (int j = i * i; j < maxn; j += i) {
                    primes[j] = false;
                }
            }
        }
        list = new ArrayList<>();
        for (int i = 0; i < maxn; i++) {
            if (primes[i]) {
                list.add(i);
            }
        }
    }

    void solve() {
        calPrimes();

        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            long n = in.nextLong();
            int sn = (int) Math.sqrt(n);
            boolean odd = true;
            for (int i = 1; i < list.size(); i++) {
                int a = list.get(i);
                if (a > sn) {
                    break;
                }
                if (n % a == 0) {
                    odd = true;
                    break;
                }
            }
            if (odd) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
