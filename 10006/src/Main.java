import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {

    int cal(int a, int n) {
        int mod = 1;
        for (int i = 0; i < n; i++) {
            mod *= a % n;
            mod %= n;
        }
        return mod % n;
    }
   
    void solve() throws IOException {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i < 65000; i++) {
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }                
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            boolean isPrime = primes.contains(n);
            boolean carmichael = false;
            if (!isPrime) {
                boolean pass = true;
                for (int a = 2; a <= n - 1; a++) {
                    int mod = cal(a, n);
                    if (mod != a) {
                        pass = false;
                        break;
                    }
                }
                if (pass) {
                    carmichael = true;
                }
            }
            if (carmichael) {
                System.out.println(String.format("The number %d is a Carmichael number.", n));
            } else {
                System.out.println(String.format("%d is normal.", n));
            }
        }
        br.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
