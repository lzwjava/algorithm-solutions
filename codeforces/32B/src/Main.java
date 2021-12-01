import java.util.Scanner;

public class Main {

    void solve() {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int p = 0;
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        while (p < len) {
            char c = s.charAt(p);
            if (c == '.') {
                sb.append('0');
                p++;
            } else if (c == '-') {
                if (p + 1 < len) {
                    char nch = s.charAt(p + 1);
                    if (nch == '.') {
                        sb.append('1');
                        p += 2;
                    } else if (nch == '-') {
                        sb.append('2');
                        p += 2;
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Main().solve();
    }

}
