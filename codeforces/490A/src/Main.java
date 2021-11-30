import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ts = new int[n];
        for (int i = 0; i < n; i++) {
            ts[i] = in.nextInt();
        }
        ArrayList<Integer>[] list = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            list[ts[i] - 1].add(i);
        }
        int min = n;
        for (int i = 0; i < 3; i++) {
            int s = list[i].size();
            if (s < min) {
                min = s;
            }
        }
        System.out.println(min);
        if (min > 0) {
            for (int i = 0; i < min; i++) {
                for (int j = 0; j < 3; j++) {
                    if (j != 0) {
                        System.out.print(' ');
                    }
                    System.out.print(list[j].get(i) + 1);
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
