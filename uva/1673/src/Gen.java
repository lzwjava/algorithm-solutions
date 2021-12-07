import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int maxn = 100000;
        out.append(String.format("%d\n", 10000));
        out.flush();
        out.close();
    }
}
