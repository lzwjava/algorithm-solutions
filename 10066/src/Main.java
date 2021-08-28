import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    int dp(int n1, int n2, int s1, int s2, int nums1[], int nums2[]) {       
        if (s1 == s2) {
            return 0;
        }
        if (n1 == 0 && n2 == 0) {
            return 0;
        }
        if (n1 == 0) {
            return n2;
        } else if (n2 == 0) {
            return n1;
        }
        int v1 = dp(n1 - 1, n2, s1 - nums1[n1], s2, nums1, nums2);
        int v2 = dp(n1, n2 - 1, s1, s2 - nums2[n2], nums1, nums2);
        if (v1 < v2) {
            return v1 + 1;
        } else {
            return v2 + 1;
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (n1 == 0 && n2 == 0) {
                break;
            }
            s = in.readLine();
            st = new StringTokenizer(s);
            int s1 = 0;
            int nums1[] = new int[n1 + 1];
            for (int i = 0; i < n1; i++) {
                int num = Integer.parseInt(st.nextToken());
                nums1[n1-i] = num;
                s1 += num;
            }
            s = in.readLine();
            st = new StringTokenizer(s);
            int nums2[] = new int[n2 + 1];
            int s2 = 0;
            for (int i = 0; i < n2; i++) {
                int num = Integer.parseInt(st.nextToken());
                nums2[n2-i] = num;
                s2 += num;
            }
            int count = dp(n1, n2, s1, s2, nums1, nums2);
            out.append(String.format("Twin Towers #%d\n", caseNum));
            out.append(String.format("Number of Tiles : %d\n", n1+n2- count));
            out.append("\n");
            caseNum++;
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
