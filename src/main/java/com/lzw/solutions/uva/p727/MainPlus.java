package com.lzw.solutions.uva.p727;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainPlus {

    BufferedReader in;
    PrintWriter out;

    MainPlus() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }

    int readInt() throws IOException {
        return Integer.parseInt(in.readLine());
    }

    enum NodeType {
        Operator,
        Value
    }

    class Node {
        NodeType type;
        String operator;
        int v;

        Node(String operator) {
            this.type = NodeType.Operator;
            this.operator = operator;
        }

        Node(int v) {
            this.type = NodeType.Value;
            this.v = v;
        }

        Node left;
        Node right;
    }

    boolean isOperator(String token) {
        String operators = "+-*/";
        return operators.contains(token);
    }

    boolean isHighOperator(String token) {
        String operators = "*/";
        return operators.contains(token);
    }

    boolean isLowOperator(String token) {
        String operators = "+-";
        return operators.contains(token);
    }

    Node buildTree(List<String> tokens) {
        if (tokens.size() == 1) {
            int v = Integer.parseInt(tokens.get(0));
            return new Node(v);
        }
        int len = tokens.size();
        int depth = 0;
        int mid = -1;
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("(")) {
                depth++;
            } else if (token.equals(")")) {
                depth--;
            } else if (isOperator(token) && depth == 0) {
                if (mid == -1) {
                    mid = i;
                } else {
                    String operator = tokens.get(mid);
                    if (isLowOperator(operator) && isHighOperator(token)) {
                        mid = i;
                    } else if (isHighOperator(operator) && isHighOperator(token)) {
                        mid = i;
                    } else if (isLowOperator(operator) && isLowOperator(token)) {
                        mid = i;
                    }
                }
            }
        }
        if (mid == -1) {
            if (tokens.get(0).equals("(") && tokens.get(len - 1).equals(")")) {
                return buildTree(tokens.subList(1, len - 1));
            }
            assert (false);
            return null;
        } else {
            List<String> leftList = tokens.subList(0, mid);
            List<String> rightList = tokens.subList(mid + 1, len);

            Node node = new Node(tokens.get(mid));
            node.left = buildTree(leftList);
            node.right = buildTree(rightList);
            return node;
        }
    }

    void print(Node node, StringBuilder sb) {
        if (node.left != null && node.right != null) {
            print(node.left, sb);
            print(node.right, sb);
            sb.append(node.operator);
        } else {
            sb.append(String.valueOf(node.v));
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
            Node node = buildTree(list);
            StringBuilder sb = new StringBuilder();
            print(node, sb);
            out.append(String.format("%s\n", sb.toString()));
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

        MainPlus main = new MainPlus();
        main.solve();
        main.close();
    }
}
