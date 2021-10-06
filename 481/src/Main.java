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

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    // start with i, max len subsequence
    Result dp(ArrayList<Integer> nums, int i) {
        // choose i
        int max = 1;
        int maxj = -1;
        for (int j = i + 1; j < nums.size(); j++) {
            if (nums.get(i) < nums.get(j)) {
                Result result = dp(nums, j);                
                if (result.len + 1 >= max) {
                    max = result.len + 1;
                    maxj = j;
                }
            }
        }
        return new Result(max, maxj);
    }

    class Result {
        int len;
        int index;

        Result() {
        }
        
        Result(int len, int index) {
            this.len = len;
            this.index = index;
        }
    }
    
    class Node {
        int i;
        Node next;
        
        Node() {            
        }
    }
   
    void solve() throws IOException {
        ArrayList<Integer> nums = new ArrayList<>();
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            line = line.trim();
            int num = Integer.parseInt(line);
            nums.add(num);
        }
        int n = nums.size();
        int max = 0;
        for (int i = 0; i < n; i++) {
            Node root = new Node();
            root.i = i;
            Result result = dp(nums, i);
            if (result.len > max) {
                max = result.len;
            }
        }
        out.append(String.format("%d\n-\n", max));
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
