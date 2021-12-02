import java.util.Scanner;

public class Main {

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int longest = in.nextInt();
            int blocked = in.nextInt();
            Point[] points = new Point[blocked];
            for (int i = 0; i < blocked; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                points[i] = new Point(x, y);
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
