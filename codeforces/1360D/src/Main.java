import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    int maxn = 100000001;
    boolean[] isPrime;
    List<Integer> list;

    void calPrimes() {
        isPrime = new boolean[maxn];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        list = new ArrayList<>();
        list.add(2);
        for (int i = 4; i < maxn; i += 2) {
            isPrime[i] = false;
        }
        int si = (int) Math.sqrt(maxn * 10);
        for (int i = 3; i <= si; i += 2) {
            if (isPrime[i]) {
                list.add(i);
                for (int j = i * i; j < maxn; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }

    int cal(int n, int k) {
        int sn = (int) Math.sqrt(n);
        for (int x : list) {
            if (x > sn) {
                break;
            }
            if (n % x == 0) {
                if (n / x <= k) {
                    return x;
                } else {
                    int tn = n;
                    int c = 0;
                    while (tn % x == 0) {
                        tn /= x;
                        c++;
                    }
                    for (int i = 0; i < c; i++) {
                        int tx = (int) Math.pow(x, i + 1);
                        if (n / tx <= k) {
                            return tx;
                        }
                    }
                }
            }
        }
        return n;
    }

    boolean isPrime(int x) {
        if (x < maxn) {
            return isPrime[x];
        }
        for (int y : list) {
            if (x % y == 0) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        calPrimes();
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(in.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int ans;
            if (n <= k) {
                ans = 1;
            } else {
                if (isPrime(n)) {
                    ans = n;
                } else {
                    ans = cal(n, k);
                }
            }
            out.append(String.format("%d\n", ans));
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}