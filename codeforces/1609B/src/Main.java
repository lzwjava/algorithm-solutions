import java.io.*;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    void solve() throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        String s = in.readLine();
        char[] chs = s.toCharArray();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(in.readLine());
            int index = Integer.parseInt(st.nextToken());
            char ch = st.nextToken().charAt(0);
            chs[index - 1] = ch;
            String ns = new String(chs);
            int cnt = 0;
            for (int j = 0; j <= n - 3; j++) {
                String sub = ns.substring(j, j + 3);
                if (sub.equals("abc")) {
                    cnt++;
                }
            }
            out.append(String.format("%d\n", cnt));
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
