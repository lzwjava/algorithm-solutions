import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.Stack;

public class Main {

   
    void work() {
        Scanner sc = new Scanner(System.in);
        for (;;) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            for (;;) {
                int nums[] = new int[n];
                nums[0] = sc.nextInt();
                if (nums[0] == 0) {
                    break;
                }
                for (int i = 1; i < n; i++) {
                    nums[i] = sc.nextInt();
                }
                int train = 1;
                Stack<Integer> stack = new Stack<>();        
                int i = 0;
                while (i < n) {
                    if (stack.size() == 0) {
                        if (train <= n) {
                            stack.add(train);
                            train++;
                        } else {
                            break;
                        }
                    }
                    if (nums[i] == stack.peek()) {
                        stack.pop();
                        i++;                       
                    } else {
                        if (train <= n) {
                            stack.push(train);
                            train++;
                        } else {
                            break;
                        }
                    }
                }
                if (i == n && stack.size() == 0) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
            System.out.println();           
        }
        sc.close();
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
