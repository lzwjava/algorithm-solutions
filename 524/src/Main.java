import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class Main {

    ArrayList<Integer> primes;

    boolean isPrime(int x) {
        return primes.contains(x);
    }

    void permutation(int nums[], boolean vis[], int i, int n) {
        if (i == n) {
            if (isPrime(nums[n - 1] + nums[0])) {
                for (int k = 0; k < n; k++) {
                    if (k != 0) {
                        System.out.print(" ");
                    }
                    System.out.print(nums[k]);
                }
                System.out.println();
            }            
            return;
        }
        for (int k = 1; k <= n; k++) {
            if (!vis[k]) {
                if (isPrime(k + nums[i - 1])) {
                    nums[i] = k;
                    vis[k] = true;
                    permutation(nums, vis, i + 1, n);
                    vis[k] = false;
                }
            }
        }
    }
   
    void solve() throws IOException {
        primes = new ArrayList<>();
        for (int i = 2; i <= 32; i++) {
            boolean ok = true;
            int si = (int)Math.sqrt(i);
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
        int caseNum = 1;
        while (true) {
            String s = br.readLine();
            if (s == null) {
                break;
            }
            int n = Integer.parseInt(s);
            System.out.println(String.format("Case %d:", caseNum));            
            boolean vis[] = new boolean[n+1];
            int nums[] = new int[n];
            nums[0] = 1;
            vis[1] = true;
            permutation(nums, vis, 1, n);
            System.out.println();
            caseNum++;
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
