import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        while (k > 0) {
            int last = n % 10;
            if (last == 0) {
                n /= 10;
            } else {
                n--;
            }
            k--;
        }
        System.out.println(n);
    }

}
