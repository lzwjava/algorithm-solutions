import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] ns = new int[n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                ns[i][j] = in.nextInt();
            }
        }
        int c = 0;
        int mc = 0;
        for (int i = 0; i < n; i++) {
            c -= ns[i][0];
            c += ns[i][1];
            if (c > mc) {
                mc = c;
            }
        }
        System.out.println(mc);
    }

}
