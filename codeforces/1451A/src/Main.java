import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

    List<Integer> list;

    void calPrimes() {
        int maxn = 31625;
        boolean[] prime = new boolean[maxn];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i = 4; i < maxn; i += 2) {
            prime[i] = false;
        }
        list = new ArrayList<>();
        list.add(2);
        for (int i = 3; i < maxn; i += 2) {
            if (prime[i]) {
                list.add(i);
                if (i * i > 0) {
                    for (int j = i * i; j < maxn; j += i) {
                        prime[j] = false;
                    }
                }
            }
        }
    }

    class Factor {
        int p, c;

        Factor(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    List<Factor> calFactors(int x) {
        List<Factor> fs = new ArrayList<>();
        int sx = (int) Math.sqrt(x);
        for (int p : list) {
            if (p > sx) {
                break;
            }
            if (x % p == 0) {
                int c = 0;
                while (x % p == 0) {
                    x /= p;
                    c++;
                }
                fs.add(new Factor(p, c));
            }
            if (x == 1) {
                break;
            }
        }
        if (x != 1) {
            fs.add(new Factor(x, 1));
        }
        return fs;
    }

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            t--;
            int n = Integer.parseInt(in.readLine());
        }
    }

}