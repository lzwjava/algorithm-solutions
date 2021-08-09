import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class Main {

    String readLine(BufferedReader br) throws IOException {
        while (true) {
            String str = br.readLine();
            if (str == null) {
                throw new IOException("end");
            }
            if (str.isEmpty()) {
                continue;
            }
            return str;
        }
    }
   
    void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String nstr = br.readLine();
            int n = Integer.parseInt(nstr);
            if (n == 0) {
                break;
            }
            String line = br.readLine();
            int nums[] = new int[n];
            int begin = 0;
            for (int i = 0; i < n; i++) {
                int end = line.indexOf(" ", begin);
                if (end == -1) {
                    end = line.length();
                }
                String s = line.substring(begin, end);
                if (s.isEmpty()) {
                    System.out.println();
                }
                nums[i] = Integer.parseInt(s);
                begin = end+1;
            }
            Arrays.sort(nums);
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    System.out.print(" ");
                }
                System.out.print(nums[i]);
            }
            System.out.println();
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
