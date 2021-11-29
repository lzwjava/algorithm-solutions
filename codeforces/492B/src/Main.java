import java.util.*;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int l = in.nextInt();
        ArrayList<Integer> as = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            as.add(in.nextInt());
        }
        Set<Integer> set = new HashSet<>();
        set.add(l);
        set.add(0);
        set.addAll(as);

        ArrayList<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        double max = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            double d = (list.get(i + 1) - list.get(i)) * 1.0 / 2;
            if (d > max) {
                max = d;
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
