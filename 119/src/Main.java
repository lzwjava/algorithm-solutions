import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Giving {
        String from;
        int money;
        int toCount;
        String[] toNames;
        
        Giving(String from, int money, int toCount, String[] toNames) {
            this.from = from;
            this.money = money;
            this.toCount = toCount;
            this.toNames = toNames;
        }
    }
   
    void solve() throws IOException {
        String line = in.readLine();        
        while (true) {        
            int n = Integer.parseInt(line);
            String[] names = new String[n];
            line = in.readLine();
            StringTokenizer st = new StringTokenizer(line);
            for (int i = 0; i < n; i++) {
                names[i] = st.nextToken();
            }
            ArrayList<Giving> givings = new ArrayList<>();
            while (true) {
                line = in.readLine();
                if (line == null) {
                    break;
                }
                st = new StringTokenizer(line);
                int count = st.countTokens();
                if (count == 1) {
                    break;
                }
                String from = st.nextToken();
                int money = Integer.parseInt(st.nextToken());
                int toCount = Integer.parseInt(st.nextToken());
                String[] toNames = new String[toCount];
                for (int i = 0; i < toCount; i++) {
                    String toName = st.nextToken();
                    toNames[i] = toName;
                }
                Giving giving = new Giving(from, money, toCount, toNames);
                givings.add(giving);
            }                                
            if (line == null){
                break;
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
