import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    Ans dp(int n) {
        if (n == 1) {
            return new Ans(2, 1);
        }
        Ans ans = dp(n - 1);
        long endWithZero = ans.count - ans.endWithOne;
        long newCount = endWithZero * 2 + ans.endWithOne; // 01 00 ; 10
        long newEndWithOne = endWithZero;
        return new Ans(newCount, newEndWithOne);
    }
    
    class Ans {
        long count;
        long endWithOne;

        Ans() {
        }
        
        Ans(long count, long endWithOne) {
            this.count = count;
            this.endWithOne = endWithOne;            
        }
    }
   
    void solve() throws IOException {
        int n = Integer.parseInt(in.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(in.readLine());
            Ans ans= dp(num);
            out.append(String.format("Scenario #%d:\n%d\n\n", i + 1, ans.count));
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
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
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
