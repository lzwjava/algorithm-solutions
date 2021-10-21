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
    
    class Node {
        char v;
        Node left;
        Node right;
    }
    
    Node buildTree(String preOrder, String inOrder) {
        if (preOrder.length() == 1) {
            Node node = new Node();
            node.v = preOrder.charAt(0);
            return node;
        }
        char ch = preOrder.charAt(0);
        Node node = new Node();
        node.v = ch;
        int index = inOrder.indexOf(ch);
        String in1 = inOrder.substring(0, index);
        String in2 = inOrder.substring(index + 1);
        int len1 = in1.length();
        String pre1 = preOrder.substring(1, 1 + len1);
        String pre2 = preOrder.substring(1 + len1);
        if (in1.length() > 0) {
            node.left = buildTree(pre1, in1);
        }
        if (in2.length() > 0) {
            node.right = buildTree(pre2, in2);            
        }
        return node;
    }
    
    void postTraversal(Node node) {
        if (node.left != null) {
            postTraversal(node.left);
        }
        if (node.right != null) {
            postTraversal(node.right);
        }
        out.append(node.v);
    }
   
    void solve() throws IOException {
        while (true) {
            String line = in.readLine();
            if (line == null) {
                break;
            }
            StringTokenizer st = new StringTokenizer(line);
            String preOrder = st.nextToken();
            String inOrder = st.nextToken();
            Node root = buildTree(preOrder, inOrder);
            postTraversal(root);
            out.append('\n');
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
