import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class State {
        String s;
        int dist;
        State parent;

        State() {
        }
        
        State(String s, int dist) {
            this.s = s;
            this.dist = dist;
        }
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        while (true) {
            String s = in.readLine();
            int n = Integer.parseInt(s);
            if (n == 0) {
                break;
            }
            int nums[] = new int[n];
            StringBuilder sb = new StringBuilder();
            StringBuilder finalSb = new StringBuilder();
            StringTokenizer tokenizer = new StringTokenizer(in.readLine());            
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(tokenizer.nextToken());
                sb.append(nums[i]);
                finalSb.append(i + 1);
            }            
            String str = sb.toString();
            String finalStr = finalSb.toString();
            HashSet<String> visited = new HashSet<>();
            ArrayBlockingQueue<State> queue = new ArrayBlockingQueue<>(362880);
            State st = new State(str, 0);
            queue.add(st);
            visited.add(str);
            int len = str.length();
            State cur = st;
            State finalSt = st;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                // 4 , 5
                boolean found = false;
                for (int l = 1; l < len; l++) {
                    // cut to right
                    // l = 4, len = 5, i = 0, i < 1
                    // start from i, l length str cut to right                    
                    for (int i = 0; i < len - l; i++) {
                        // 0, 4
                        // l = 3, len = 5, i = 0,  
                        // (0, 4) 
                        // 2 4 1 5    3   6
                        // 0 1 2 3 4 5
                        // 2 4 1 5 3 6                        
                        for (int j = i + l + 1; j <= len; j++) {
                            String s0 = cur.s.substring(0, i);
                            String s1 = cur.s.substring(i, i + l); // cut
                            String s2 = cur.s.substring(i + l, j);
                            // paste
                            String s3 = cur.s.substring(j, len);

                            String ns = s0 + s2 + s1 + s3;
                            assert (ns.length() == finalStr.length());
                            if (!visited.contains(ns)) {
                                State nst = new State(ns, cur.dist + 1);
                                nst.parent = cur;
                                if (ns.equals(finalStr)) {
                                    found = true;
                                    finalSt = nst;
                                    break;
                                } else {
                                    visited.add(ns);
                                    queue.add(nst);                                    
                                }
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }

                    // cut to left
                    for (int i = 1; i < len - l; i++) {
                        for (int j = 0; j < i; j++) {
                            String s0 = cur.s.substring(0, j);
                            String s1 = cur.s.substring(j, i);
                            String s2 = cur.s.substring(i, i + l); // cut
                            String s3 = cur.s.substring(i + l, len);
                            String ns = s0 + s2 + s1 + s3;
                            assert (ns.length() == finalStr.length());                                                        
                            if (!visited.contains(ns)) {
                                State nst = new State(ns, cur.dist + 1);
                                nst.parent = cur;
                                if (ns.equals(finalStr)) {
                                    found = true;
                                    finalSt = nst;
                                    break;
                                } else {
                                    visited.add(ns);
                                    queue.add(nst);
                                }                               
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            printState(finalSt);
            out.append(String.format("Case %d: %d\n", caseNum, finalSt.dist));
            caseNum++;
        }
    }

    private void printState(Main.State cur) {
        while (cur != null) {
            out.append(cur.s).append(' ');
            cur = cur.parent;
        }
        out.append('\n');
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
