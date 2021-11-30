import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int n = in.nextInt();
            String s = String.format("%d", n);
            ArrayList<Integer> list = new ArrayList<>();
            int p = 1;
            for (int i = s.length() - 1; i >= 0; i--) {
                int digit = s.charAt(i) - '0';
                if (digit != 0) {
                    int a = digit * p;
                    list.add(a);
                }
                p *= 10;
            }
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    System.out.print(' ');
                }
                System.out.print(list.get(i));
            }
            System.out.println();
            t--;
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
