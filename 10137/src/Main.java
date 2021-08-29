import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);        
    }
   
    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine().trim());
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            int total = 0;
            for (int i = 0; i < n; i++) {
                double num = Double.parseDouble(in.readLine().trim());
                nums[i] = (int)(num * 100);
                total += nums[i];
            }
            double avg = (double)(total*1.0 / n);
            int ans = 0;
            int ceilCount = 0;
            int floorCount = 0;
            int ceil = (int) Math.ceil(avg);
            int bottom = (int) Math.floor(avg);
            int ravg = (int) Math.round(avg);            
            for (int i = 0; i < n; i++) {
                if (Math.abs(avg - ravg) < 1e-5) {
                    int gap = (int) Math.abs(nums[i] - avg);
                    ans += gap;
                } else {                    
                    int x = Math.abs(ceil - nums[i]);
                    int y = Math.abs(bottom - nums[i]);
                    int gap;
                    if (ceilCount == floorCount) {
                        if (x<y){
                            gap = x;
                            ceilCount++;
                        } else {
                            gap = y;
                            floorCount++;
                        }
                    } else if (ceilCount < floorCount){
                        gap = x;
                        ceilCount++;
                    } else {
                        gap = y;
                        floorCount++;
                    }
                    ans += gap;
                }
            }        
            out.append(String.format("$%.2f\n", ans*1.0/2/100));
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
            outStream = new PrintStream("1.out");
            System.setIn(inStream);
            System.setOut(outStream);
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
