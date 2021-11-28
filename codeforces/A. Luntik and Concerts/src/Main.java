import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int[] nums;
    int total;
    int[][] map;
    int n;
    int a, b, c;
    
    int dp(int sum, int index) {
        if (map[sum][index] != -1) {
            return map[sum][index];
        }
        int ans;
        if (index == n) {
            int sum2 = total - sum;
            ans = Math.abs(sum - sum2);
        } else {
            int take = dp(sum + valueAtIndex(index), index + 1);
            int leave = dp(sum, index + 1);
            ans = Math.min(take, leave);
        }
        map[sum][index] = ans;
        return ans;
    }
    
    int valueAtIndex(int index) {
        if (index < a) {
            return 1;
        } else if (index < a + b) {
            return 2;
        } else {
            return 3;
        }
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            a %= 30;
            b %= 20;
            c %= 10;
            // while (a >= 2 && b >= 2 && c >= 2) {
            //     a -= 2;
            //     b -= 2;
            //     c -= 2;
            // }
            // while (a >= b * 2 + c * 3 && a >= 2) {                
            //     a -= 2;
            // }
            // while (b * 2 >= a + c * 3 && b >= 2) {                
            //     b -= 2;
            // }
            // while (c * 3 >= a + b * 2 && c >= 2) {                
            //     c -= 2;
            // }
            n = a+b+c;
            nums = new int[n];
            total = a + 2 * b + c * 3;                      
            map = new int[total + 5][n+5];
            for (int[] arr : map) {
                Arrays.fill(arr, -1);
            }
            int ans = dp(0, 0);
            out.append(String.format("%d\n", ans));            
            t--;
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
