import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n > 0) {
            int k = sc.nextInt();
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < k; i++) {
                String s = sc.next();
                int cents = sc.nextInt();
                Character ch = s.charAt(0);
                map.put(ch, cents);
            }
            int m = sc.nextInt();
            int total = 0;
            for (int i = 0; i < m; i++) {
                String s = sc.nextLine();
                if (s.isEmpty()) {
                    s = sc.nextLine();
                }
                for (int j = 0; j < s.length(); j++) {
                    Character ch = s.charAt(j);
                    Integer cents = map.get(ch);
                    if (cents != null) {
                        total += cents;
                    }
                }
            }
            System.out.println(String.format("%.2f$", total * 1.0 / 100));
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
