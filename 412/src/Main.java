import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {

    BufferedReader in;
    PrintWriter out;

    ArrayList<Integer> primes;

    void calPrimes() {
        primes = new ArrayList<>();
        for (int i = 2; i <= 32768; i++) {
            if (i > 2 && i % 2 == 0) {
                continue;
            }
            boolean ok = true;
            int si = (int) Math.sqrt(i);
            for (int j = 0; j < primes.size(); j++) {
                int pj = primes.get(j);
                if (pj > si) {
                    break;
                }
                if (i % pj == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                primes.add(i);
            }
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    boolean check(int a, int b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (a == 1) {
            return true;
        }
        if (b % a == 0) {
            return false;
        }
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);           
            if (a % pi == 0 && b % pi == 0) {              
                return false;
            }
        }     
        return true;
    }
   
    void solve() throws IOException {
        calPrimes();
        while (true) {
            String s = in.readLine();
            if (s.isEmpty()) {
                s = in.readLine();
            }
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            ArrayList<ArrayList<Integer>> factors = new ArrayList<>();            
            for (int i = 0; i < n; i++) {
                int a = Integer.parseInt(in.readLine());
                nums[i] = a;
                ArrayList<Integer> factor = calFactor(a);
                factors.add(factor);
            }

            int count = 0;
            int total = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    ArrayList<Integer> f1 = factors.get(i);
                    ArrayList<Integer> f2 = factors.get(j);
                    boolean hasCommon = false;
                    for (int k = 0; k < f1.size(); k++) {
                        if (f2.contains(f1.get(k))) {
                            hasCommon = true;
                            break;
                        }
                    }
                    if (!hasCommon) {
                        count++;
                    }
                    total++;
                }
            }
            if (count == 0) {
                out.append("No estimate for this data set.");
            } else {
                double v =  count * 1.0 / total;
                out.append(String.format("%.6f", Math.sqrt(6/v)));
            }
            out.append('\n');
        }
    }

    private ArrayList<Integer> calFactor(int a) {
        ArrayList<Integer> factors = new ArrayList<>();
        int si = (int)Math.sqrt(a);
        for (int i = 0; i < primes.size(); i++) {
            int pi = primes.get(i);
            if (pi > si) {
                break;
            }
            if (a % pi == 0) {
                factors.add(pi);
                while (a % pi == 0) {
                    a /= pi;
                }
            }
        }
        if (a != 1) {
            factors.add(a);
        }
        return factors;
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();              
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
