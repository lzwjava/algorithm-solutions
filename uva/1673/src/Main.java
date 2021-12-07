import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        while (true) {
            String s = in.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            String[] strs = new String[n];
            for (int i = 0; i < n; i++) {
                strs[i] = in.readLine();
            }
            Set<BigInteger> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                String str = strs[i];
                for (int j = 0; j < str.length(); j++) {
                    for (int k = j + 1; k <= str.length(); k++) {
                        String sub = str.substring(j, k);
                        BigInteger bi = new BigInteger(sub);
                        set.add(bi);
                    }
                }
            }
            BigInteger sum = BigInteger.ZERO;
            BigInteger m = BigInteger.valueOf(2012);
            for (BigInteger b : set) {
                sum = sum.add(b).mod(m);
            }
            out.append(String.format("%d\n", sum.intValue()));
        }
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
}