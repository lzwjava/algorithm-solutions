import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    class Obj implements Comparable<Obj> {
        int p;
        int w;
        double r;

        Obj(int p, int w) {
            this.p = p;
            this.w = w;
            this.r = p * 1.0 / w;
        }

        @Override
        public int compareTo(Main.Obj o) {
            return Double.compare(o.r, r);
        }

    }
    
    int calObjs(ArrayList<Obj> objs, ArrayList<Boolean> used, int i, int n, int mw, int value) {
        if (i == n) {
            return value;
        }
        used.set(i, false);
        int p1 = calObjs(objs, used, i + 1, n, mw, value);
        Obj o = objs.get(i);
        if (mw>= o.w){
            used.set(i, true);
            int p2 = calObjs(objs, used, i + 1, n, mw - o.w, value+o.p);
            return p1 > p2 ? p1 : p2;
        } else {
            return p1;
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
            for (int i = 0; i < g; i++) {
                mws.add(sc.nextInt());
            }
            
            Collections.sort(mws);

            Collections.sort(objs);         

            int maxValue = 0;
            for (int i = 0; i < mws.size(); i++) {
                ArrayList<Boolean> used = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    used.add(false);
                }

                int mw = mws.get(i);
                int p = calObjs(objs, used, i, n, mw, 0);
                maxValue += p;
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
