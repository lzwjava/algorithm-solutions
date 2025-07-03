package com.algorithm.solutions.uva.p12219;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static class TreeNode {

        String value;

        TreeNode left;
        TreeNode right;

        int num;

        TreeNode() {
        }

    }
    
    private static int pos;
    
    private static TreeNode buildTree(String str) {
        // System.out.println(pos);
        StringBuilder value = new StringBuilder();
        for (;;) {
            if (pos >= str.length()) {
                break;
            }
            char ch = str.charAt(pos);
            if (Character.isLetter(ch)) {
                value.append(ch);
                pos++;
            } else {
                break;
            }
        }

        if (pos < str.length()) {
        
            char pch = str.charAt(pos);

            if (pch == '(') {
                String v = value.toString();
                pos++;
                TreeNode leftNode = buildTree(str);
                pos++;
                TreeNode rightNode = buildTree(str);
                pos++;
    
                TreeNode node = new TreeNode();
                node.value = v;
                node.left = leftNode;
                node.right = rightNode;
                return node;
            } else if (pch == ',' || pch == ')') {
                TreeNode node = new TreeNode();
                node.value = value.toString();
                return node;            
            }
        } else {
            TreeNode node = new TreeNode();
            node.value = value.toString();
            return node;
        }
        return null;
    }
    
    private static String traverse(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s", root.value));
        if (root.left != null && root.right != null) {
            sb.append("(");
            sb.append(traverse(root.left));
            sb.append(",");
            sb.append(traverse(root.right));
            sb.append(")");
        }
        return sb.toString();
    }

    private static void calNum(TreeNode node, HashMap<String, Integer> map) {
        if (node.left != null && node.right != null) {
            calNum(node.left, map);
            calNum(node.right, map);
        }
        String expr = getExpression(node);
        Integer num = map.get(expr);
        if (num == null) {
            int num1 = map.size()+1;
            map.put(expr, num1);
            node.num = num1;
        } else {
            node.num = num;
        }
    }

    private static String getExpression(TreeNode node) {
        int left = 0, right = 0;        
        if (node.left != null && node.right != null) {
            left = node.left.num;
            right = node.right.num;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        sb.append(node.value);
        sb.append(",");
        sb.append(left);
        sb.append(",");
        sb.append(right);
        sb.append(")");
        return sb.toString();
    }

    private static void printTree(TreeNode node, HashMap<String, Integer> map) {
        String expr = getExpression(node);
        Integer num = map.get(expr);
        if (num == null) {
            int nextNum = map.size() + 1;
            map.put(expr, nextNum);
            System.out.print(node.value);
            if (node.left != null && node.right != null) {
                System.out.print("(");
                printTree(node.left, map);
                System.out.print(",");
                printTree(node.right, map);
                System.out.print(")");                
            }
        } else {
            System.out.print(num);
        }
    }    
    
    private static void work(){
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        for (int i = 0; i < c; i++) {
            pos = 0;
            String expression = sc.next();
            // System.out.println(expression);
            TreeNode root = buildTree(expression);

            // traverse(root);

            HashMap<String, Integer> map = new HashMap<>();
            calNum(root, map);
            // for (String key : map.keySet()) {
            //     System.out.println(key + " " + map.get(key));
            // }

            // System.out.println(map.size());
            // System.out.println(traverse(root));
            HashMap<String, Integer> printMap = new HashMap<>();
            printTree(root, printMap);
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

        work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
