import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            long a = 1;
            int b = n / 2;
            for (int i = 1; i <= b; i++) {
                int c = n - i * 2;
                int d = c + i;
                long e = combine(d, i);
                a += e;
            }
            System.out.println(a);
        }
        sc.close();
    }
    
    long factorial(int n) {
        long s = 1;
        for (int i = 1; i <= n; i++) {
            s *= i;
        }
        return s;
    }

    private long combine(int n, int r) {
        int i = 1;
        int j = 1;
        int k = 1;
        long s = 1;
        while (true) {
            if (i <= n) {
                s *= i;
                i++;                
            }
            if (j <= r) {
                if (s % j != 0) {
                    continue;
                }
                s /= j;
                j++;
            }
            if (k <= n - r) {
                if (s % k != 0) {
                    continue;
                }
                s /= k;
                k++;
            }
            if (i > n && j > r && k > n - r) {
                break;
            }
        }
        // long s = factorial(n) / factorial(r) / factorial(n - r);
        return s;
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
