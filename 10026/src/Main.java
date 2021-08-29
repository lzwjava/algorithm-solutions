import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    BufferedReader in;
    PrintWriter out; 

    class Job implements Comparable<Job> {
        int id;
        int time;
        int fine;

        Job(int id, int time, int fine) {
            this.id = id;
            this.time = time;
            this.fine = fine;
        }

        @Override
        public int compareTo(Main.Job o) {
            int f1 = time * o.fine;
            int f2 = fine * o.time;
            if (f1 == f2) {
                return id - o.id;
            } else {
                return f1 - f2;
            }
        }
    }

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
       
    void solve() throws IOException {
        int t = Integer.parseInt(in.readLine());
        while (t > 0) {
            in.readLine();            
            String s = in.readLine();
            int n = Integer.parseInt(s);
            ArrayList<Job> jobs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String line = in.readLine();
                StringTokenizer st = new StringTokenizer(line);
                int time = Integer.parseInt(st.nextToken());
                int fine = Integer.parseInt(st.nextToken());
                Job j = new Job(i+1, time, fine);
                jobs.add(j);
            }
            Collections.sort(jobs);
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    out.append(' ');
                }
                Job j = jobs.get(i);
                out.append(String.format("%d", j.id));
            }
            out.append('\n');        
            t--;
            if (t != 0) {
                out.append('\n');
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
