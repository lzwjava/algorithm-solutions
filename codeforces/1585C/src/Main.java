import java.util.*;

public class Main {

    public static void main(String[] args) {
        new Main().solve();
    }

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            t--;
            int n, k;
            n = in.nextInt();
            k = in.nextInt();
            int[] x = new int[n];
            List<Integer> right = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                x[i] = in.nextInt();
                if (x[i] > 0) {
                    right.add(x[i]);
                } else {
                    right.add(-x[i]);
                }
            }
            Collections.sort(right, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });

            // visited depot count
            int vis = 0;
            int rn = right.size();
            long dist = 0;
            int pos = 0;
            if (rn > 0) {
                pos = right.get(0);
                while (vis < rn) {
                    if (pos != right.get(vis)) {
                        pos = right.get(vis);
                        dist += pos;
                    }
                    // rn: 3
                    if (vis + k <= rn) {
                        int rp = pos;
                        dist += rp;
                        pos = 0;
                        vis += k;
                    } else {
                        int nk = rn - vis;
                        int rp = pos;
                        pos = 0;
                        dist += rp;
                        vis += nk;
                    }
                }
            }
            System.out.println(dist);
        }
    }

}