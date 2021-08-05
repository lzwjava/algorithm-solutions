import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    class Dot {
        int x;
        int y;
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t>0){
            int n = sc.nextInt();
            ArrayList<Dot> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Dot dot = new Dot();
                dot.x = sc.nextInt();
                dot.y = sc.nextInt();
                list.add(dot);
            }
            if (n == 1) {
                System.out.println("YES");
            } else {
                Dot d = list.get(0);
                double mx = 0;
                boolean found = false;
                for (int i = 1; i < n; i++) {
                    Dot di = list.get(i);
                    if (di.y == d.y) {
                        mx = (di.x + d.x) / 2.0;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("NO");                    
                } else {
                    final double mx1 = mx;
                    list.sort(new Comparator<Dot>() {
                        @Override
                        public int compare(Main.Dot o1, Main.Dot o2) {
                            double v1 = Math.abs(o1.x - mx1);
                            double v2 = Math.abs(o2.x - mx1);
                            if (Math.abs(v1-v2)<1e-10){
                                return 0;
                            } else if (v1-v2<0){
                                return -1;
                            } else {
                                return 1;
                            }
                        }                                                
                    });
                    boolean ok = true;
                    for (int i = 0; i < n;) {
                        Dot dot = list.get(i);
                        double v1 = Math.abs(dot.x - mx);
                        if (v1 < 1e-10) {
                            i++;
                            continue;
                        } else {
                            if (i + 1 < n) {
                                Dot dn = list.get(i + 1);
                                double v2 = Math.abs(dn.x - mx);
                                if (Math.abs(v1 - v2) < 1e-10) {
                                    i += 2;
                                } else {
                                    ok = false;
                                    break;
                                }
                            } else {
                                ok = false;
                                break;
                            }
                        }
                    }
                    if (ok) {
                        System.out.println("YES");
                    } else {
                        System.out.println("NO");
                    }
                }
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
