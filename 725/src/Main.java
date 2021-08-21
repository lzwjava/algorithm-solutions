import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;    
    int n;
    boolean found;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    String numsToStr(int nums[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    
    void permutation(int nums[], boolean vis[], int i) {
        if (i == 10) {
            String str1 = numsToStr(nums, 0, 5);
            String str2 = numsToStr(nums, 5, 10);
            int a1 = Integer.parseInt(str1);
            int a2 = Integer.parseInt(str2);
            if (a2 != 0 && a1 == n * a2) {
                System.out.println(String.format("%s / %s = %d", str1, str2, n));
                found = true;
            }
            return;
        }
        for (int j = 0; j <= 9; j++) {
            if (!vis[j]) {
                vis[j] = true;
                nums[i] = j;
                permutation(nums, vis, i + 1);
                vis[j] = false;
            }            
        }
    }
   
    void solve() throws IOException {
        boolean first = true;
        while (true) {
            n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int nums[] = new int[10];
            found = false;
            boolean vis[] = new boolean[10];
            if (first) {
                first = false;
            } else {
                System.out.println();
            }
            permutation(nums, vis, 0);
            if (!found) {
                System.out.println(String.format("There are no solutions for %d.", n));
            }
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
