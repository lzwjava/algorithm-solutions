import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        // int n = 0;
        while(true){
            int n = 0;
            // n++;
            // if (n % 2 == 0 || n % 5 == 0) {
            //     continue;
            // }
            try {
                n = sc.nextInt();
            } catch (NoSuchElementException e) {
                break;
            }
            int x;
            int mods[] = new int[1000];
            mods[0] = 1 % n;
            for (int i=1;i<1000;i++){
                mods[i] =  (mods[i-1] * 10)%n;
            }
            for (x = 1;; x++) {
                int p = 0;
                for (int i = 0; i <= x - 1; i++) {
                    p = (p + mods[i]) % n;
                }
                if (p == 0) {
                    break;
                }
            }
            // System.out.println(n);
            System.out.println(x);
        }
        // sc.close();
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
