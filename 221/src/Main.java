import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    class Building {
        int num;
        int x;
        int y;
        int w;
        int d;
        int h;

        // a cover b
        boolean cover(Building b, boolean strict) {
            int ax1 = x;
            int ax2 = x + w;
            int bx1 = b.x;
            int bx2 = b.x + b.w;
            if (strict) {
                return ax1 <= bx1 && bx2 <= ax2 && !(ax1 == bx1 && bx2 == ax2);
            } else {
                return ax1 <= bx1 && bx2 <= ax2;
            }
        }
        
        boolean cover(Building b) {
            return cover(b, false);
        }

        boolean intersect(Building b) {
            return cover(b) || b.cover(this);
        }

        boolean visibleBy(Building b) {
            if (num == 9 && b.num == 5) {
                System.out.println();
            }
            if (cover(b, true)) {
                return true;
            }
            boolean intersect = intersect(b);
            if (!intersect) {
                return true;
            }
            if (y < b.y) {
                assert (y + d < b.y);
                return true;
            } else {
                if (h > b.h) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
   
    void work() {
        Scanner sc = new Scanner(System.in);
        int num=1;
        for (;;) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            ArrayList<Building> buildings = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Building b = new Building();
                b.x = sc.nextInt();
                b.y = sc.nextInt();
                b.w = sc.nextInt();
                b.d = sc.nextInt();
                b.h = sc.nextInt();
                b.num = i + 1;
                buildings.add(b);
            }
            buildings.sort(new Comparator<Building>(){
                @Override
                public int compare(Building o1, Building o2) {
                    if (o1.x != o2.x) {
                        return o1.x - o2.x;
                    } else {
                        return o1.y - o2.y;
                    }
                }                
            });            

            ArrayList<Building> visibles = new ArrayList<>();            
            for (Building a : buildings) {
                boolean visible = true;
                for (Building b : buildings) {
                    if (b != a) {
                        if (!a.visibleBy(b)) {
                            visible = false;
                            break;
                        }
                    }
                }
                if (visible) {
                    visibles.add(a);
                }
            }
            visibles.sort(new Comparator<Building>() {
                @Override
                public int compare(Main.Building o1, Main.Building o2) {
                    return o1.x - o2.x;                 
                }                                
            });
            System.out.println(String.format("For map #%d, the visible buildings are numbered as follows:", num));
            for (int i = 0; i < visibles.size(); i++) {
                Building v = visibles.get(i);
                if (i != 0) {
                    System.out.print(" ");
                }
                System.out.print(v.num);
            }
            System.out.println();
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
