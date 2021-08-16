import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    String numsToString(int nums[], int i, int j) {
        StringBuilder sb = new StringBuilder();
        for (int k = i; k < j; k++) {
            sb.append(nums[k]);
        }
        return sb.toString();
    }

    void permutation(int nums[], int i, int n) {
        if (i == n) {            
            String a = numsToString(nums, 0, n/2);        
            String b = numsToString(nums, n / 2, n);           
            int s = Integer.parseInt(a) + Integer.parseInt(b);

            String c = numsToString(nums, 0, n);            
            if (s * s == Integer.parseInt(c)) {
                System.out.println(c);
            }
            return;
        }
        for (int j = 0; j <= 9; j++) {
            nums[i] = j;
            permutation(nums, i+1, n);
        }
    }
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = br.readLine();
            if (line == null) {
                break;
            }
            int n = Integer.parseInt(line);
            int nums[] = new int[n];
            permutation(nums, 0, n);
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
