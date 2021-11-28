import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = br.readLine();
            if (s.equals("DONE")) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (Character.isAlphabetic(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }
            }
            String ns = sb.toString();
            boolean ok = true;
            int len = ns.length();
            for (int i = 0; i < len / 2; i++) {
                if (ns.charAt(i) != ns.charAt(len - 1 - i)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                System.out.println("You won't be eaten!");
            } else {
                System.out.println("Uh oh..");
            }
        }
        br.close();
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

        new Main().solve();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
