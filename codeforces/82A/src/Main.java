import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void close() throws IOException {
        in.close();
        out.flush();
        out.close();
    }

    void solve() throws IOException {
        String[] names = new String[]{"Sheldon", "Leonard", "Penny", "Rajesh", "Howard"};
        int n = Integer.parseInt(in.readLine());
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1000);
        for (int i = 0; i < 5; i++) {
            queue.add(i);
        }
        for (int i = 0; i < n; i++) {
        }
    }

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.solve();
        m.close();
    }

}