import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    long factorial(int n) {
        if (n == 1) {
            return 1;
        }
        return factorial(n - 1) * n;
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        for(int u=0;u<t;u++){
            String word = in.readLine();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                Integer count = map.get(ch);
                if (count == null) {
                    count = 0;
                }
                count++;
                map.put(ch, count);
            }
            long ans = factorial(word.length());
            for (Character ch : map.keySet()) {
                Integer count = map.get(ch);
                if (count > 1) {
                    ans /= factorial(count);
                }
            }
            out.append(String.format("Data set %d: %d\n", u + 1, ans));
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
