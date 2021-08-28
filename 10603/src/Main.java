import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
   
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int vols[] = new int[] { a, b, c };
            int amounts[] = new int[] { 0, 0, c };
            Random random = new Random();
            for (;;) {
                boolean vis[] = new boolean[3];
                int from = 0;
                while (true) {
                    from = random.nextInt(3);
                    if (vis[from]) {
                        continue;
                    }
                    if (amounts[from] > 0) {
                        break;
                    }
                    vis[from] = true;
                    boolean allVis = true;
                    for (int i = 0; i < 3; i++) {
                        if (!vis[i]) {
                            allVis = false;
                            break;
                        }
                    }
                    if (allVis) {
                        break;
                    }
                }
                if (amounts[from] < 0) {

                }
            }
        }
    }

    void close() throws IOException {
        if (in != null) {
            in.close();
        }
        if (out != null) {
            out.flush();
            out.close();              
        }
    }

    public static void main(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getProperty("os.name").equals("Mac OS X");        
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main main = new Main();
        main.solve();
        main.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
