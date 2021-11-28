import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    int nums[];
    int total;
    int map[][];

    int dp(int sum, int index) {        
        if (index == nums.length) {
            int sum2 = total - sum;
            return Math.abs(sum - sum2);
        }
        if (map[sum][index]!=-1) {
            return map[sum][index];
        }        
        int take = dp(sum+nums[index], index+1);
        int leave = dp(sum, index + 1);
        int ans = Math.min(take, leave);
        map[sum][index] = ans;
        return ans;
    }

    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int m = sc.nextInt();
            nums = new int[m];
            total = 0;
            for (int i = 0; i < m; i++) {
                nums[i] = sc.nextInt();
                total += nums[i];
            }
            map = new int[total][m];
            for (int[] a : map) {
                Arrays.fill(a, -1);
            }
            int ans = dp(0, 0);
            System.out.println(ans);
            n--;
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
