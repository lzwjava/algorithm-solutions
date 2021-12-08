import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Gen {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("2.in");
        int maxn = 200;
        out.append(String.format("%d\n", maxn));
        Random random = new Random();
        int total = 0;
        for (int i = 0; i < maxn; i++) {
            StringBuilder sb = new StringBuilder();
            int len = random.nextInt(1000);
            for (int j = 0; j < len; j++) {
                sb.append(String.format("%d", random.nextInt(10)));
            }
            String s = sb.toString();
            out.append(String.format("%s\n", s));
            total += len;
        }
        out.flush();
        out.close();
    }
}
