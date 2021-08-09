import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {

    int readInt(BufferedReader br) throws IOException {
        while (true) {
            String str = br.readLine();
            if (str == null) {
                throw new IOException("end");
            }
            if (str.isEmpty()) {
                continue;
            }
            return Integer.parseInt(str);
        }
    }
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            
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
        try {
            new Main().solve();
        } catch (IOException e) {
        }        
        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
