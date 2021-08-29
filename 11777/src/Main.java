import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
        int caseNum =1;
        while (t > 0) {
            int term1, term2, finalc, attendance;
            int test[] = new int[3];
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            term1 = Integer.parseInt(st.nextToken());
            term2 = Integer.parseInt(st.nextToken());
            finalc = Integer.parseInt(st.nextToken());
            attendance = Integer.parseInt(st.nextToken());
            test[0] = Integer.parseInt(st.nextToken());
            test[1] = Integer.parseInt(st.nextToken());
            test[2] = Integer.parseInt(st.nextToken());
            Arrays.sort(test);
            int mark = (test[1] + test[2]) / 2;
            int total = term1 + term2 + finalc + attendance + mark;
            char grade;
            if (total >= 90) {
                grade = 'A';
            } else if (total >= 80) {
                grade = 'B';           
            } else if (total>=70){
                grade = 'C';
            } else if (total >= 60) {
                grade = 'D';
            } else {
                grade = 'F';
            }
            out.append(String.format("Case %d: %c\n", caseNum, grade));            
            caseNum++;
            t--;
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
