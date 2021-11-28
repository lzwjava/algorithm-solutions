import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {            
            int nums[] = new int[3];
            boolean allZero = true;
            for (int i = 0; i < 3; i++) {
                nums[i] = sc.nextInt();
                if (nums[i] != 0) {
                    allZero = false;
                }
            }
            if (allZero) {
                break;
            }
            Arrays.sort(nums);
            if (nums[0] * nums[0] + nums[1] * nums[1] == nums[2] * nums[2]) {
                System.out.println("right");
            } else {
                System.out.println("wrong");                
            }
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
