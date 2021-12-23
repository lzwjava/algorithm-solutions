import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        Random random = new Random();
        PrintWriter out = new PrintWriter("1.in");
        out.append("1\n");
        int n = 2000;
        out.append(String.format("%d\n", n));
        for (int i = 0; i < n; i++) {
            out.append(String.format("%d ", random.nextInt(10000) + 1));
        }
        out.flush();
        out.close();
    }
}