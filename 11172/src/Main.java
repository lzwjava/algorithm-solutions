import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
   
    void work() {
        Scanner sc = new Scanner(System.in);    
        int t = sc.nextInt();
        while (t > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a < b) {
                System.out.println("<");
            } else if(a > b) {
                System.out.println(">");                
            } else {
                System.out.println("=");                                
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
