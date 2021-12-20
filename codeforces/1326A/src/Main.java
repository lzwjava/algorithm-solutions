import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        Random random = new Random();
        while (t > 0) {
            int n = Integer.parseInt(in.readLine());
            if (n == 1) {
                out.append("-1\n");
            } else {
                String ans;
                while (true) {
                    Set<Integer> set = new HashSet<>();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < n; i++) {
                        int digit = random.nextInt(9) + 1;
                        if (i == n - 1) {
                            while (digit % 2 == 0) {
                                digit = random.nextInt(8) + 2;
                            }
                        }
                        sb.append(String.valueOf(digit));
                        set.add(digit);
                    }
                    String s = sb.toString();
                    BigInteger bi = new BigInteger(s);
                    boolean ok = true;
                    for (int x : set) {
                        if (bi.mod(BigInteger.valueOf(x)).intValue() == 0) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        ans = s;
                        break;
                    }
                }
                out.append(String.format("%s\n", ans));
            }
            t--;
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}