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
    
    String add(String a, String b) {
        if (a.length() > b.length()) {
            String tmp = a;
            a = b;
            b = tmp;
        }
        int alen = a.length();
        int blen = b.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < blen; i++) {
            int sum = carry;
            if (alen - 1 - i >= 0) {
                char cha = a.charAt(alen - 1 - i);
                sum += (cha - '0');
            }
            char chb = b.charAt(blen - 1 - i);
            sum += (chb - '0');
            if (sum > 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            sb.append((char)('0' + sum));
        }   
        return sb.reverse().toString();                
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);

        System.out.println(add("10", "11"));

        int t = sc.nextInt();
        ArrayList<FiBigInteger> fs = new ArrayList<>();
        FiBigInteger fb2 = new FiBigInteger(new BigInteger("1"), 0);
        FiBigInteger fb1 = new FiBigInteger(new BigInteger("1"), 1);
        fs.add(fb2);
        fs.add(fb1);
        int n = 2;
        for (; n < 1000; n++) {
            BigInteger bn = fb1.bi.add(fb2.bi);
            FiBigInteger fb = new FiBigInteger(bn, n);
            fs.add(fb);
        
            fb2 = fb1;
            fb1 = fb;            
        }
        int caseNum = 1;

        while (t > 0) {
            String str = sc.next();

            System.out.print(String.format("Case #%d: ", caseNum));
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
            caseNum++;
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
