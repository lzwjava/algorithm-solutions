import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int n = 100000;
        out.append(String.format("%d\n", n));
        Random random = new Random();
        int[] arr = new int[]{25, 50, 100};
        for (int i = 0; i < n; i++) {
            int idx = random.nextInt(3);
            out.append(String.format("%d ", arr[idx]));
        }
        out.flush();
        out.close();
    }
}