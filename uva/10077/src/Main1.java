import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main1 {

    BufferedReader in;
    PrintWriter out;

    Main1() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
    
    class Node {
        int numerator;        
        int denominator;
        Node left;
        Node right;
        Node parent;
        int level;

        Node(int numerator, int denominator) {
            this.numerator = numerator;            
            this.denominator = denominator;
        }
    }

    // type 0: root, 1: left, 2: right
    void addLevel(Node node, Node parent, int type, int level) {
        if (node.left == null && node.right == null) {
            Node n1 = null;
            if (type == 1) {
                // parent must not null 
                n1 = parent;               
            } else if (type == 2) {
                n1 = parent;
            } else {
                // root
                n1 = new Node(0, 1);
            }
            int leftNumerator = n1.numerator + node.numerator;
            int leftDenominator = n1.denominator + node.denominator;

            Node left = new Node(leftNumerator, leftDenominator);
            left.parent = node;
            left.level = level;

            Node n2 = null;
            if (type == 1) {
                n2 = parent;                                                
            } else if (type == 2) {                
                if (parent != null && parent.parent != null) {
                    n2 = parent.parent;
                } else {
                    n2 = new Node(1, 0);
                }
            } else {
                // root
                n2 = new Node(1, 0);
            }

            int rightNumberator = n2.numerator + node.numerator;
            int rightDenominator = n2.denominator + node.denominator;
            Node right = new Node(rightNumberator, rightDenominator);
            right.parent = node;
            right.level = level;

            node.left = left;
            node.right = right;
        } else {
            addLevel(node.left, node, 1, level);
            addLevel(node.right, node, 2, level);
        }
    }
    
    void traverse(Node node, ArrayList<Node>[] nodes) {
        // out.append(String.format("%d/%d ", node.numerator, node.denominator));
        nodes[node.level].add(node);
        if (node.left != null) {
            traverse(node.left, nodes);
        }
        if (node.right != null) {
            traverse(node.right, nodes);
        }
    }
    
    boolean find(Node node, int m, int n, String path) {
        if (node.left == null && node.right == null) {
            if (node.numerator == m && node.denominator == n) {
                out.append(String.format("%s\n", path));
                return true;
            }
        } else {
            boolean ok = find(node.left, m, n, path + "L");
            if (ok) {
                return true;
            }
            ok = find(node.right, m, n, path + "R");
            if (ok) {
                return true;
            }
        }
        return false;
    }
   
    void solve() throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            if (m == 1 && n == 1) {
                break;
            }
            Node root = new Node(1, 1);
            root.level = 0;
            for (int i = 0; i < 5; i++) {
                addLevel(root, null, 0, i + 1);
                ArrayList<Node>[] nodes = new ArrayList[i + 2];
                for (int j = 0; j < i + 2; j++) {
                    nodes[j] = new ArrayList<>();
                }
                traverse(root, nodes);
                for (int j = 0; j < i + 2; j++) {
                    for (int k = 0; k < nodes[j].size(); k++) {
                        Node node = nodes[j].get(k);
                        out.append(String.format("%d/%d ", node.numerator, node.denominator));
                    }
                    out.append('\n');
                }
                out.append('\n');
                boolean ok = find(root, m, n, "");
                if (ok) {
                    break;
                }                
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

    public static void Main1(String[] args) throws Exception {
        FileInputStream inStream = null;
        PrintStream outStream = null;
        boolean isLocal = System.getenv("LOCAL_JUDGE") != null;
        if (isLocal) {
            inStream = new FileInputStream("1.in");
            // outStream = new PrintStream("1.out");
            System.setIn(inStream);
            // System.setOut(outStream);
        }

        Main1 main1 = new Main1();
        main1.solve();
        main1.close();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
