import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    static class State {
        String str;
        int dist;

        State() {
            this.str = "";
            this.dist = 0;
        }

        State(String str) {
            this.str = str;
            this.dist = 0;
        }

        static Main.State fromNums(int nums[]) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                if (i != 0) {
                    sb.append(',');
                }
                sb.append(String.valueOf(nums[i]));
            }
            String s = sb.toString();
            return new State(s);
        }

        int[] nums() {
            String[] ss = str.split(",");
            int nums[] = new int[ss.length];
            for (int i = 0; i < ss.length; i++) {
                nums[i] = Integer.parseInt(ss[i]);
            }
            return nums;
        }
    }

    ArrayBlockingQueue<State> queue;
    HashSet<Integer> set;

    void tryAddToQueue(State cur, int nums[], int sum) {
        if (set.contains(sum)) {
            return;
        }
        set.add(sum);
        int newNums[] = Arrays.copyOf(nums, nums.length + 1);
        newNums[nums.length] = sum;
        State newState = State.fromNums(newNums);
        newState.dist = cur.dist + 1;
        queue.add(newState);        
    }
    
    int cal(int n) {
        queue = new ArrayBlockingQueue<>(1000);
        set = new HashSet<>();    
        set.add(1);
        State startState = new State("1");
        queue.add(startState);
        while (!queue.isEmpty()) {
            State st = queue.poll();
            int[] nums = st.nums();
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < nums.length; j++) {
                    int sum = nums[i] + nums[j];
                    if (sum == n) {
                        return st.dist + 1;
                    }                   
                    tryAddToQueue(st, nums, sum);
                    int subtract = nums[i] - nums[j];
                    if (subtract == n) {
                        return st.dist + 1;
                    }
                    tryAddToQueue(st, nums, sum);
                }
            }
        }
        return 0;
    }
   
    void solve() throws IOException {
        while (true) {
            int n = Integer.parseInt(in.readLine());
            if (n == 0) {
                break;
            }
            int ans = cal(n);
            out.append(String.valueOf(ans)).append('\n');
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
