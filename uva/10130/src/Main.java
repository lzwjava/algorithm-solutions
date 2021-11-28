import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    class Obj {
        int p;
        int w;

        Obj(int p, int w) {
            this.p = p;
            this.w = w;
        }
    }
    
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            ArrayList<Obj> objs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int p = sc.nextInt();
                int w = sc.nextInt();
                Obj obj = new Obj(p, w);
                objs.add(obj);
            }
            int g = sc.nextInt();
            ArrayList<Integer> mws = new ArrayList<>();
            int max = 0;
            for (int i = 0; i < g; i++) {
                int mw = sc.nextInt();
                mws.add(mw);
                if (mw > max){
                    max = mw;
                }
            }

            int d[][] = new int[n+1][max+1];
            for (int i = 0; i <= max; i++) {
                d[0][i] = 0;
            }
            for (int i = 1; i <= n; i++) {
                for (int w = 0; w <= max; w++) {
                    int v = d[i - 1][w];
                    Obj obj = objs.get(i-1);
                    if (w >= obj.w) {
                        int nv = d[i - 1][w - obj.w] + obj.p;
                        if (v < nv) {
                            v = nv;
                        }
                    }
                    d[i][w] = v;
                }
            }

            int maxValue = 0;
            for (int i = 0; i < mws.size(); i++) {         
                int mw = mws.get(i);
                maxValue += d[n][mw];
            }

            System.out.println(maxValue);

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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
