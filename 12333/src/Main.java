import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    
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
            if (sum >= 10) {
                carry = 1;
                sum = sum % 10;
            } else {
                carry = 0;
            }
            sb.append((char) ('0' + sum));
        }
        if (carry > 0) {
            sb.append("1");
        }
        String s = sb.reverse().toString();       
        if (s.length() > 100) {
            s = s.substring(0, 100);
        }
        return s;
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        // System.out.println(add("4181", "6765"));

        int t = sc.nextInt();
        ArrayList<String> fs = new ArrayList<>();
        String fb2 = "1";
        String fb1 = "1";
        fs.add(fb2);
        fs.add(fb1);
        int n = 2;
        for (; n < 100000; n++) {
            String fb = add(fb1, fb2);
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
                String fb = fs.get(i);
                if (fb.startsWith(str)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                System.out.println(i);
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
