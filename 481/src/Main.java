import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    HashMap<Integer, Result> map;
    
    class Result {
        int len;
        Node next;

        Result(int len, Node next) {
            this.len = len;
            this.next = next;
        }
    }
    
    // start with i, max len subsequence
    int dp(ArrayList<Integer> nums, Node root, int i) {
        Result v = map.get(i);
        if (v != null) {
            root.next = v.next;
            return v.len;                          
        }     
        // choose i
        int max = 1;
        for (int j = i + 1; j < nums.size(); j++) {
            if (nums.get(i) < nums.get(j)) {
                Node next = new Node();
                next.i = j;
                int len = dp(nums, next, j) + 1;
                if (len >= max) {
                    root.next = next;
                    max = len;
                }
            }
        }

        v = new Result(max, root.next);
        map.put(i, v);
        return max;
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
        int[] list = new int[n];
        int[] idList = new int[n];
        int[] path = new int[n];
        int lisEnd = 0, lisCount = 0;
        for (int i = 0; i < nums.size(); i++) {
            int pos = Arrays.binarySearch(list, 0, lisCount, nums.get(i));
            if (pos < 0) {
                pos = -(pos + 1);
            }
            list[pos] = nums.get(i);
            idList[pos] = i;
            path[i] = pos != 0 ? idList[pos - 1] : -1;
            if (pos == lisCount) {
                lisCount++;
                lisEnd = i;
            }
        }
        out.append(String.format("%d\n-\n", lisCount));
        print(nums, path, lisEnd);
    }
    
    void print(ArrayList<Integer> nums, int[] path, int pos) {
        if (pos == -1) {
            return;
        }
        print(nums, path, path[pos]);
        out.append(String.format("%d\n", nums.get(pos)));
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
