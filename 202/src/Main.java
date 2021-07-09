import java.io.FileInputStream;
import java.util.Scanner;

public class Main {

    private static void work(){
        Scanner sc = new Scanner(System.in);

        for (;;) {
            if (!sc.hasNextInt()) {
                break;
            }
            int numerator = sc.nextInt();
            int denominator = sc.nextInt();
            int size = 1000;
            int nums[] = new int[size];
            int positive = (int) (numerator / denominator);
            int res = numerator % denominator;
            int i;
            // System.out.println(positive);
            for (i = 0; i < size; i++) {
                if (res == 0) {
                    break;
                }
                nums[i] = (int) (res * 10 / denominator);
                res = (res * 10) % denominator;
                // System.out.println("nums[i] " + i + "=" + nums[i]);
            }
            if (i < size) {
                System.out.print(String.format("%d/%d = %d.", numerator, denominator, positive));
                for (int j = 0; j < i; j++) {
                    System.out.print(nums[j]);
                }
                System.out.println("(0)");
                System.out.println("   1 = number of digits in repeating cycle");
            } else {
                boolean found = false;
                int start =0, len = 0;                
                for (len = 1; len * 2 < size; len++) {
                    for (start = 0; start + len * 2 < size; start++) {
                        boolean ok = true;
                        for (int j = 0; start + j * len < size; j++) {
                            if (nums[start + j % len] != nums[j]) {
                                ok = false;
                                break;
                            }
                        }
                        if (ok) {
                            found = true;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    System.out.print(String.format("%d/%d = %d.", numerator, denominator, positive));
                    for (int j = 0; j < start; j++) {
                        System.out.print(nums[j]);
                    }
                    System.out.print("(");
                    int printLen = Math.min(50, len);
                    for (int j = 0; j < printLen; j++) {
                        System.out.print(nums[start + j]);
                    }
                    if (len > 50) {
                        System.out.println("...)");
                    } else {
                        System.out.println(")");
                    }
                    System.out.println(String.format("   %d = number of digits in repeating cycle", len));
                }
            }
        }
        sc.close();
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            System.setIn(inStream);
        }

        work();

        if (isLocal) {
            inStream.close();
        }
    }
}
