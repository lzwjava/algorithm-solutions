import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            int p = n / 2;
            Arrays.sort(a);
            int c = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        int m = a[i] % a[j];
                        int index = Arrays.binarySearch(a, m);
                        if (index < 0) {
                            System.out.println(String.format("%d %d", a[i], a[j]));
                            c++;
                            if (c == p) {
                                break;
                            }
                        }
                    }
                }
                if (c == p) {
                    break;
                }
            }
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
