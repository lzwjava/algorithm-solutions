import java.io.FileInputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    class FiBigInteger {
        BigInteger bi;
        String str;
        int index;

        FiBigInteger() {
            
        }

        FiBigInteger(BigInteger bi, int index) {
            this.bi = bi;
            this.index = index;
            this.str = bi.toString(10);
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ArrayList<FiBigInteger> fs = new ArrayList<>();
        fs.add(new FiBigInteger(new BigInteger("1"), 0));
        fs.add(new FiBigInteger(new BigInteger("1"), 1));
        int n = 2;
        for (; n < 10000; n++) {
            FiBigInteger fb1 = fs.get(n - 1);
            FiBigInteger fb2 = fs.get(n - 2);
            BigInteger bn = fb1.bi.add(fb2.bi);
            FiBigInteger fb = new FiBigInteger(bn, n);
            fs.add(fb);
        }

        while (t > 0) {
            String str = sc.next();

            System.out.print(String.format("Case #%d: ", t));
            boolean found = false;
            int i = 0;
            for (i = 0; i < fs.size(); i++) {
                FiBigInteger fb = fs.get(i);
                if (fb.str.startsWith(str)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(fs.get(i).index);
            } else {
                System.out.println("-1");
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
