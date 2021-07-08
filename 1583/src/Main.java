import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;        
        if (System.getProperty("ONLINE_JUDGE") == null) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int j;
            for (j = 1; j < n; j++) {
                String s = String.format("%d", j);
                int digitSum = 0;
                for (int k = 0; k < s.length(); k++) {
                    int digit = s.charAt(k) - '0';
                    digitSum += digit;
                }
                if (j + digitSum == n) {
                    System.out.println(j);
                    break;
                }
            }
            if (j == n) {
                System.out.println(0);
            }
        }
        sc.close();

        if (inStream != null) {
            inStream.close();
        }
    }
}
