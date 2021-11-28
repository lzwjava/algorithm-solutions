import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        String s = in.next();
        while (t > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; ) {
                if (i + 1 < n && s.charAt(i) == 'B' && s.charAt(i + 1) == 'G') {
                    sb.append('G');
                    sb.append('B');
                    i += 2;
                } else {
                    sb.append(s.charAt(i));
                    i++;
                }
            }
            s = sb.toString();
            t--;
        }
        System.out.println(s);
    }

}
