import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    int f91(int n) {
        if (n <= 100) {
            return f91(f91(n + 11));
        } else {
            return n - 10;
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            System.out.println(String.format("f91(%d) = %d", n, f91(n)));
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
