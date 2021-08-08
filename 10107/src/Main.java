import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            int find = Collections.binarySearch(nums, x);
            if (find < 0) {
                nums.add(-find - 1, x);
            } else {
                nums.add(find, x);
            }
            int m;
            int numslen = nums.size();
            if (numslen % 2 == 0) {
                m = (nums.get(numslen / 2) + nums.get(numslen/2 - 1))/2;
            } else {
                m = nums.get(numslen / 2);
            }    
            System.out.println(m);            
        }
        sc.close();
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
