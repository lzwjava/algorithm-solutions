import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        boolean ans;
        if (n < 26) {
            ans = false;
        } else {
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) {
                set.add(Character.toLowerCase(c));
            }
            ans = set.size() == 26;
        }
        if (ans) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
