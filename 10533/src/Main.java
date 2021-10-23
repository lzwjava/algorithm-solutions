import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
   
    void solve() throws IOException {
        int maxn = 1000000;
        
        int sx = (int)Math.sqrt(maxn);

        boolean[] notPrime = new boolean[maxn];
        notPrime[0] = notPrime[1] = true;
        for (int i = 4; i < maxn; i += 2) {
            notPrime[i] = true;
        }
        for (int i = 3; i <= sx; i += 2) {
            if (!notPrime[i]) {
                for (int j = i + i; j < maxn; j += i) {
                    notPrime[j] = true;
                }
            }
        }

        int count = 0;
        int[] digitCounts = new int[maxn];
        for (int i = 0; i < maxn; i++) {
            int sum = 0;
            int x = i;
            while (x != 0) {
                sum += x % 10;
                x /= 10;
            }
            if (!notPrime[i] && !notPrime[sum]) {
                count++;
            }
            digitCounts[i] = count;
        }
        
        int n = Integer.parseInt(in.readLine());
        while (n > 0) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int t1 = Integer.parseInt(st.nextToken());
            int t2 = Integer.parseInt(st.nextToken());

            out.append(String.format("%d\n", digitCounts[t2] - digitCounts[t1 - 1]));            
            n--;
        }
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
            inStream = new FileInputStream("2.in");
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
