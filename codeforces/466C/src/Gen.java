import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int n = 100000;
        out.append(String.format("%d\n", n));
        out.append("1");
        for (int i = 0; i < n - 3; i++) {
            out.append(String.format(" 0"));
        }
        out.append(" 1 1\n");
        out.flush();
        out.close();
    }
}