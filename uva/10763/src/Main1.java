import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main1 {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Integer> originals = new ArrayList<>();
            ArrayList<Integer> targets = new ArrayList<>();            
            for (int i = 0; i < n; i++) {
                int o = sc.nextInt();
                int t = sc.nextInt();
                originals.add(o);
                targets.add(t);
            }
            Collections.sort(originals);
            Collections.sort(targets);
            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (!originals.get(i).equals(targets.get(i))) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("2.in");
            // outStream = new PrintStream("2.out");
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
