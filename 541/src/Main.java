import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int nums[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    nums[i][j] = sc.nextInt();
                }
            }
            int si = -1;
            int sj = -1;
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += nums[i][j];
                }
                if (sum % 2 == 0) {
                    continue;
                } else {
                    if (si == -1) {
                        si = i;
                    } else {
                        ok = false;
                    }
                }
            }

            if (!ok) {
                System.out.println("Corrupt");
                continue;
            }

            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    sum += nums[i][j];
                }
                if (sum % 2 == 0) {
                    continue;
                } else {
                    if (sj == -1) {
                        sj = j;
                    } else {
                        ok = false;
                    }
                }
            }

            if (!ok) {
                System.out.println("Corrupt");
            } else {
                if (si == -1 && sj == -1) {
                    System.out.println("OK");                    
                } else {
                    assert (si != -1 && sj != -1);
                    System.out.println(String.format("Change bit (%d,%d)", si+1,sj+1));
                }
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
