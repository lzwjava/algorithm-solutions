import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    float dist[][];
    float minDist;
    int minIds[];

    class Point {
        float x;
        float y;

        Point() {
        }

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }
    
    float distOfPoints(Point a, Point b) {
        float dx = Math.abs(a.x - b.x);
        float dy = Math.abs(a.y - b.y);
        float d;
        if (dx < 1e-8) {
            d = dy;
        } else if (dy < 1e-8) {
            d = dx;
        } else {
            d = (float) Math.sqrt(dx * dx + dy * dy);
        }
        return d;
    }
    
    // 先把所有连的找到，然后删掉最长的    
    void dfs(int ids[], boolean vis[], float d, int i, int n) {
        if (d > minDist) {
            return;
        }
        if (i == n) {
            d += dist[i - 1][0];
            if (minDist > d) {
                minDist = d;
                minIds = Arrays.copyOf(ids, ids.length);
            }
            return;
        }
        for (int j = 0; j < n; j++) {
            if (!vis[j]) {
                vis[j] = true;
                ids[i] = j;
                d += dist[ids[i - 1]][j];
                dfs(ids, vis, d, i + 1, n);
                vis[j] = false;
            }
        }
    }
   
    void solve() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t > 0) {
            int n = sc.nextInt();
            Point[] pts = new Point[n];
            // ArrayList<Point> pts = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                float x = sc.nextFloat();
                float y = sc.nextFloat();
                pts[i] = new Point(x, y);
            }
            // float dist = 0;
            // for (int i = 0; i < n - 1; i++) {
            //     Point a = pts.get(i);
            //     Point b = pts.get(i + 1);                
            //     dist += d;
            // }
            dist = new float[n][n];
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    dist[i][j] = distOfPoints(pts[i], pts[j]);
                    dist[j][i] = dist[i][j];
                }
            }

            minDist = Float.MAX_VALUE;
            int ids[] = new int[n];
            ids[0] = 0;
            boolean vis[] = new boolean[n];
            vis[0] = true;
            dfs(ids, vis, 0, 1, n);
            float maxDist = 0;
            for (int i = 0; i < n; i++) {
                int ni = i + 1;
                if (ni == n) {
                    ni = 0;
                }
                if (dist[minIds[i]][minIds[ni]] > maxDist) {
                    maxDist = dist[minIds[i]][minIds[ni]];                    
                }
            }
            System.out.println(String.format("%.2f", minDist-maxDist));
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
