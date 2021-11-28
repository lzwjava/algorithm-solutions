import java.io.*;
import java.util.Arrays;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        String s = in.readLine();
        String[] splits = s.split("\\+");
        int n = splits.length;
        int[] ns = new int[n];
        for (int i = 0; i < n; i++) {
            ns[i] = Integer.parseInt(splits[i]);
        }
        Arrays.sort(ns);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                sb.append("+");
            }
            sb.append(ns[i]);
        }
        out.append(String.format("%s\n", sb.toString()));
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
