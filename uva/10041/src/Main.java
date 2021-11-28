import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);    
        int t = sc.nextInt();
        while (t > 0) {
            int r = sc.nextInt();
            int nums[] = new int[r];
            for (int i = 0; i < r; i++) {
                nums[i] = sc.nextInt();
            }
            Arrays.sort(nums);
            double d;
            if (r%2==1){
                d = nums[r/2];
            } else {
                d = (nums[r / 2 - 1] + nums[r / 2]) * 1.0 / 2;
            }             
            double s = 0;
            for (int i = 0; i < r; i++) {
                s += Math.abs(nums[i] - d);
            }
            if (Math.abs(s - Math.round(s)) < 1e-10) {
                System.out.println(Math.round(s));                
            } else {
                System.out.println(s);
            }
            t--;
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
