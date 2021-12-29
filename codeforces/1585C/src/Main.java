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
            List<Integer> left = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                x[i] = in.nextInt();
                if (x[i] > 0) {
                    right.add(x[i]);
                } else {
                    left.add(x[i]);
                }
            }
            Collections.sort(left, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return Integer.compare(o2, o1);
                }
            });
            Collections.sort(right);
            // visited depot count
            int vis = 0;
            int rn = right.size();
            long dist = 0;
            int pos = 0;
            while (vis < rn) {
                if (pos != 0) {
                    dist += pos;
                    pos = 0;
                }
                // rn: 3
                if (vis + k <= rn) {
                    int rp = right.get(vis + k - 1);
                    dist += rp;
                    pos = rp;
                    vis += k;
                } else {
                    int nk = rn - vis;
                    int rp = right.get(vis + nk - 1);
                    pos = rp;
                    dist += rp;
                    vis += nk;
                }
            }
            int ln = left.size();
            if (ln > 0) {
                if (pos != 0) {
                    dist += pos;
                    pos = 0;
                }
                vis = 0;
                while (vis < ln) {
                    if (pos != 0) {
                        dist += -pos;
                        pos = 0;
                    }
                    if (vis + k <= ln) {
                        int lp = left.get(vis + k - 1);
                        dist += -lp;
                        pos = lp;
                        vis += k;
                    } else {
                        int nk = ln - vis;
                        int lp = left.get(vis + nk - 1);
                        pos = lp;
                        dist += -lp;
                        vis += nk;
                    }
                }
            }
            System.out.println(dist);
        }
    }

}