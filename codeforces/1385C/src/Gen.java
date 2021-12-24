import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int t = 1;
        out.append(String.format("%d\n", t));
        int n = 66666;
        out.append(String.format("%d\n", n));
        for (int i = 0; i < n; i++) {
            int v = (int) (Math.random() * 2 + i + 1);
            if (i == n - 2) {
                v = 2;
            }
            out.append(String.format("%d ", v));
        }
        out.flush();
        out.close();
    }
}