import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main1 {
   
    void work() {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            String table[][] = new String[n][m];
            for (int i = 0; i < n; i++) {
                String s = sc.nextLine();
                if (s.isEmpty()) {
                    s = sc.nextLine();
                }
                String strs[] = s.split(",");
                assert (strs.length == m);
                table[i] = strs;
            }
            boolean found = false;
            int r1 = 0, r2 =0, c1=0, c2=0;
            for (r1 = 0; r1 < n - 1; r1++) {
                for (r2 = r1 + 1; r2 < n; r2++) {
                    for (c1 = 0; c1 < m - 1; c1++) {
                        if (!table[r1][c1].equals(table[r2][c1])) {
                            continue;
                        }
                        for (c2 = c1 + 1; c2 < m; c2++) {
                            if (table[r1][c2].equals(table[r2][c2])) {
                                found = true;
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                System.out.println("NO");
                System.out.println(String.format("%d %d", r1 + 1, r2 + 1));
                System.out.println(String.format("%d %d", c1 + 1, c2 + 1));                
            } else {
                System.out.println("YES");
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
