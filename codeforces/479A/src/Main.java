import java.util.Arrays;
import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int[] ns = new int[3];
        for (int i = 0; i < 3; i++) {
            ns[i] = in.nextInt();
        }
        Arrays.sort(ns);
        int ans = 0;
        if (ns[0] > 1) {
            ans = ns[0] * ns[1] * ns[2];
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
