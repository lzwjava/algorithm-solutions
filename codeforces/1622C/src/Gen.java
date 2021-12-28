import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int t = 1;
        out.append(String.format("%d\n", t));
        int n = 100000;
        out.append(String.format("%d %d\n", n, 4071505));
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int k = 987000000;
            k += random.nextInt(10000);
            out.append(String.format("%d ", k));
        }
        out.flush();
        out.close();
    }
}