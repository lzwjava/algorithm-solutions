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

    // [start, end)
    int lowerBound(int[] nums, int start, int end, int value) {
        // if nums contain value, return lower bound pos
        // if nums not contain value, return the insertion pos
        while (end - start > 0) {
            // 0,1, mid = 0, 
            // 0,2, mid = 1
            int mid = (start + end) / 2;
            if (nums[mid] >= value) {
                end = mid;
                // [start, mid)
            } else if (nums[mid]< value){
                // <= 
                start = mid+1;
                // [mid,end)
            }
        }
        // [start, end)
        // [0,1)
        // value insert at start
        return start;
    }

    void test() {
        int[] nums = new int[]{2, 2, 2};                
        int pos = lowerBound(nums, 0, 3, 1);
        out.append(String.format("%d\n", pos));
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
            int pos = lowerBound(list, 0, lisCount, nums.get(i));           
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
        // main.test();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
