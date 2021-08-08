import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1000000; i++) {
            boolean isPrime = true;
            int square = (int)Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (i % pj == 0) {
                    isPrime = false;
                    break;
                }
                if (pj > square) {
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int i = 1;
            boolean found = false;
            int a = 0, b = 0;
            while (true) {
                a = primes.get(i);
                b = n - a;
                if (b < a) {
                    break;
                }
                int m = Collections.binarySearch(primes, b);
                if (m > 0) {
                    found = true;
                    break;
                }
                i++;
            }
            if (found) {
                System.out.println(String.format("%d = %d + %d", n, a, b));
            }
        }
        sc.close();
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
