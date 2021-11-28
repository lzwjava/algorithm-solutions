import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    boolean isHard(String s) {
        int len = s.length();
        int maxlen = len / 2;
        for (int i = 1; i <= maxlen; i++) {
            String right = s.substring(len - i, len);
            String left = s.substring(len - 2 * i, len - i);
            if (left.equals(right)) {
                return false;
            }
        }
        return true;
    }
   
    void solve() throws IOException {
        // boolean hard = isHard("ABCABC");
        // System.out.println(hard ? "hard":"easy");
        while (true) {
            String s = in.readLine();
            StringTokenizer st = new StringTokenizer(s);
            int n = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            if (n == 0 || L == 0) {
                break;
            }
            PriorityQueue<String> pq = new PriorityQueue<>();
            for (int i = 0; i < L; i++) {
                String a = "";
                a += (char) ('A' + i);
                pq.add(a);
            }
            int count = 0;
            while (!pq.isEmpty()) {
                count++;
                if (count == n) {
                    break;
                }
                String a = pq.poll();
                // out.append(a).append('\n');
                for (int i = 0; i < L; i++) {
                    char ch = (char) ('A' + i);
                    String na = a + ch;
                    if (isHard(na)) {
                        pq.add(na);
                    }
                }
            }
            String ans = pq.poll();
            int groupSize = 4;
            int group = (ans.length()-1) / groupSize+1;
            for (int i = 0; i < group; i++) {
                int end = (i + 1) * groupSize;
                if (end > ans.length()) {
                    end = ans.length();
                }
                String sub = ans.substring(i * groupSize, end);
                if (i % 16 == 0 && i != 0) {
                    out.append('\n');
                }
                if (i % 16 != 0) {
                    out.append(' ');
                }
                out.append(sub);
            }            
            out.append('\n');
            out.append(String.format("%d\n", ans.length()));
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
