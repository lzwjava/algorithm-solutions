import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    
    void addToMap(int location, HashMap<Integer, Integer> locations) {
        Integer count = locations.get(location);
        if (count == null) {
            locations.put(location, 1);
        } else {
            count++;
            locations.put(location, count);
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            HashMap<Integer, Integer> originals = new HashMap<>();
            HashMap<Integer, Integer> targets = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int o = sc.nextInt();
                int t = sc.nextInt();
                addToMap(o, originals);
                addToMap(t, targets);
            }
            boolean ok = true;
            for (Integer origin : originals.keySet()) {
                Integer originCount = originals.get(origin);
                Integer targetCount = targets.get(origin);
                if (targetCount == null || originCount != targetCount) {
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
            inStream = new FileInputStream("1.in");
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
