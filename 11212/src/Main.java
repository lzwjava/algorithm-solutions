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
    HashSet<String> visited;
    ArrayBlockingQueue<State> queue;
    String finalStr;
    State finalSt;

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
    
    boolean isContinuous(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int delta = 0;
        if (s.charAt(1) > s.charAt(0)) {
            delta = 1;
        } else {
            delta = -1;
        }
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1) + delta) {
                return false;
            }
        }
        return true;
    }
    
    boolean isCutAlreadyContinuous(String cut, String s) {
        int start = s.indexOf(cut);
        int end = start + cut.length();
        assert(!(start == 0 && end == s.length()));
        if (start == 0) {
            char ch = s.charAt(end - 1);
            char nch = s.charAt(end);
            if (ch + 1 == nch) {
                return true;
            }
        } else {
            char ch = s.charAt(start - 1);
            char nch = s.charAt(start);
            if (ch + 1 == nch) {
                return true;
            }
        }
        return false;
    }
   
    void solve() throws IOException {
        int caseNum = 1;
        // out.append(isContinuous("5")+"");
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
            finalStr = finalSb.toString();
            State st = new State(str, 0);
            visited = new HashSet<>();
            queue = new ArrayBlockingQueue<>(362880);
            queue.add(st);
            visited.add(str);
            int len = str.length();
            State cur = st;
            finalSt = st;         
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
                        String s0 = cur.s.substring(0, i);
                        String s1 = cur.s.substring(i, i + l); // cut 

                        for (int j = i + l + 1; j <= len; j++) {                                                   
                            String s2 = cur.s.substring(i + l, j);
                            // paste
                            String s3 = cur.s.substring(j, len);                            
                            String ns = s0 + s2 + s1 + s3;
                            if (isCutAlreadyContinuous(s1, cur.s)) {
                                continue;
                            }
                            if (checkFound(cur, ns)) {
                                found = true;
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

                    // cut to left
                    for (int i = 1; i < len - l; i++) {
                        String s2 = cur.s.substring(i, i + l); // cut
                        String s3 = cur.s.substring(i + l, len);                      
                        for (int j = 0; j < i; j++) {
                            String s0 = cur.s.substring(0, j);
                            String s1 = cur.s.substring(j, i);                        
                            String ns = s0 + s2 + s1 + s3;
                            if (isCutAlreadyContinuous(s2, cur.s)) {
                                continue;
                            }
                            if (checkFound(cur, ns)) {
                                found = true;
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
                if (found) {
                    break;
                }
            }
            // printState(finalSt);
            out.append(String.format("Case %d: %d\n", caseNum, finalSt.dist));
            caseNum++;
        }
    }

    boolean checkFound(State cur, String ns) {
        assert (ns.length() == finalStr.length());
        if (!visited.contains(ns)) {
            State nst = new State(ns, cur.dist + 1);
            nst.parent = cur;
            if (ns.equals(finalStr)) {
                finalSt = nst;
                return true;
            } else {
                int h = 0;
                for (int i = 0; i < ns.length() - 1; i++) {
                    char ch = ns.charAt(i);
                    char nch = ns.charAt(i + 1);
                    if (ch + 1 != nch) {
                        h++;
                    }
                }
                if (3 * nst.dist + h <= 3 * 5) {
                    visited.add(ns);
                    queue.add(nst);
                }
            }
        }
        return false;
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
