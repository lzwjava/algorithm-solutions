import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    BufferedReader in;
    PrintWriter out;

    Main() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    boolean isOperator(char ch) {
        return "+-*/".indexOf(ch) >= 0;
    }

    int precedence(char ch) {
        if (ch == '(' || ch == ')') {
            return 0;
        } else if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        }
        return -1;
    }

    void pushOp(Stack<Character> op, StringBuilder postfix, char ch) {
        if (op.isEmpty() || precedence(op.peek()) < precedence(ch)) {
            op.push(ch);
        } else {
            while (!op.isEmpty() && precedence(op.peek()) >= precedence(ch)) {
                postfix.append(op.pop());
            }
            op.push(ch);
        }
    }

    void solve() throws IOException {
        int t = readInt();
        in.readLine();
        while (t > 0) {
            ArrayList<String> list = new ArrayList<String>();
            while (true) {
                String s = in.readLine();
                if (s == null || s.isEmpty()) {
                    break;
                }
                list.add(s);
            }
            StringBuilder postfix = new StringBuilder();
            Stack<Character> op = new Stack<Character>();
            for (String token : list) {
                char ch = token.charAt(0);
                if (ch == '(') {
                    op.push('(');
                } else if (ch == ')') {
                    while (op.peek() != '(') {
                        postfix.append(op.pop());
                    }
                    op.pop();
                } else if (Character.isDigit(ch)) {
                    postfix.append(ch);
                } else if (isOperator(ch)) {
                    pushOp(op, postfix, ch);
                }
            }
            while (!op.isEmpty()) {
                postfix.append(op.pop());
            }
            out.append(String.format("%s\n", postfix.toString()));
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
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("2.in");
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
