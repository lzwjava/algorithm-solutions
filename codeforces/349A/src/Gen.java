import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int n = 100000;
        out.append(String.format("%d\n", n));
        for (int i = 0; i < n; i++) {
            out.append(String.format("%d ", 25));
        }
        out.flush();
        out.close();
    }
}