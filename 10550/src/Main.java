import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int p = sc.nextInt();
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            int c3 = sc.nextInt();
            if (p == 0 && c1 == 0 && c2 == 0 && c3 == 0) {
                break;
            }            
            int degree = 360 * 2;
            if (p < c1) {
                degree += (c1 - p) * 9;       
            } else {       
                degree += (c1 + 40 - p) * 9;
            }
            
            degree += 360;
            if (c1 < c2) {
                degree += (c1 + 40 - c2)*9;
            } else {
                degree += (c1 - c2) * 9;
            }
            if (c2 > c3) {
                degree += (40 - c2 + c3) * 9;                
            } else {
                degree += (c3 - c2) * 9;
            }
            System.out.println(degree);
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
