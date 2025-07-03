package com.lzw.solutions.uva.p12657;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    class MyLinkedList {

        class Node {
            Integer item;

            Node prev;
            Node next;

            Node(Integer item) {
                this.item = item;
            }
        }

        Node first;
        Node last;
        ArrayList<Node> nodes;

        MyLinkedList(int capacity) {
            nodes = new ArrayList<Node>(capacity);
        }

        Node getNode(int value) {
            return nodes.get(value - 1);
        }

        void setNode(int value, Node node) {
            nodes.set(value - 1, node);
        }

        void add(Integer item) {
            Node node = new Node(item);
            nodes.add(node);

            if (first == null && last == null) {
                first = node;
                last = node;
            } else {
                last.next = node;
                node.prev = last;
                last = node;
            }
        }

        void moveLeft(Integer x, Integer y) {
            Node xNode = getNode(x);
            Node yNode = getNode(y);

            if (xNode.next == yNode) {
                return;
            }

            // del x
            Node xPrevNode = xNode.prev;
            Node xNextNode = xNode.next;
            if (xPrevNode != null) {
                xPrevNode.next = xNextNode;
            } else {
                // first Node
                this.first = xNextNode;
            }
            if (xNextNode != null) {
                xNextNode.prev = xPrevNode;
            }
            if (xNode == last) {
                last = xPrevNode;
            }

            // insert x before y
            Node yPrevNode = yNode.prev;
            if (yPrevNode != null) {
                yPrevNode.next = xNode;
            } else {
                first = xNode;
            }
            yNode.prev = xNode;

            xNode.next = yNode;
            xNode.prev = yPrevNode;
        }

        void moveRight(Integer x, Integer y) {
            Node xNode = getNode(x);
            Node yNode = getNode(y);

            if (xNode == yNode.next) {
                return;
            }

            // del x
            Node xPrevNode = xNode.prev;
            Node xNextNode = xNode.next;
            if (xPrevNode != null) {
                xPrevNode.next = xNextNode;
            } else {
                // first Node
                this.first = xNextNode;
            }
            if (xNextNode != null) {
                xNextNode.prev = xPrevNode;
            }
            if (xNode == last) {
                last = xPrevNode;
            }

            // insert x after y
            Node yNextNode = yNode.next;
            if (yNextNode != null) {
                yNextNode.prev = xNode;
            } else {
                last = xNode;
            }
            yNode.next = xNode;

            xNode.next = yNextNode;
            xNode.prev = yNode;
        }

        void swap(Integer x, Integer y) {
            Node xNode = getNode(x);
            Node yNode = getNode(y);

            xNode.item = y;
            yNode.item = x;

            setNode(x, yNode);
            setNode(y, xNode);
        }

        void printNode(Node node) {
            System.out.print(node.item + " ");
            System.out.print(" prev: ");
            if (node.prev != null) {
                System.out.print(node.prev.item);
            }
            System.out.print(" next: ");
            if (node.next != null) {
                System.out.print(node.next.item);
            }
            System.out.println();
        }

        void reverse() {
            for (Node node : nodes) {
                // printNode(node);
                Node tmp = node.next;
                node.next = node.prev;
                node.prev = tmp;
                // System.out.println("after");
                // printNode(node);
                // System.out.println();
            }
            Node tmp = first;
            first = last;
            last = tmp;
        }

        long sum(boolean reversed) {
            if (!reversed) {
                Node node = first;
                long sum = 0;
                int i = 1;
                while (node != null) {
                    if (i % 2 == 1) {
                        sum += node.item;
                    }
                    node = node.next;
                    i++;
                }
                return sum;
            } else {
                Node node = last;
                long sum = 0;
                int i = 1;
                while (node != null) {
                    if (i % 2 == 1) {
                        sum += node.item;
                    }
                    node = node.prev;
                    i++;
                }
                return sum;
            }
        }

        void print(boolean reversed) {
            if (!reversed) {
                Node node = first;
                while (node != null) {
                    System.out.print(node.item + " ");
                    node = node.next;
                }
                System.out.println();
            } else {
                Node node = last;
                while (node != null) {
                    System.out.print(node.item + " ");
                    node = node.prev;
                }
                System.out.println();
            }
        }

        void check() {
            ArrayList<Integer> list = new ArrayList<>();
            Node node = first;
            while (node != null) {
                list.add(node.item);
                node = node.next;
            }
            ArrayList<Integer> reversed = new ArrayList<>();
            node = last;
            while (node != null) {
                reversed.add(node.item);
                node = node.prev;
            }
            assert (reversed.size() == list.size());
            Collections.reverse(reversed);
            for (int i = 0; i < list.size(); i++) {
                assert (list.get(i) == reversed.get(i));
            }
        }
    }

    interface Callback {
        void run(Integer item);
    }

    void work() {
        int caseNum = 1;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (; ; ) {
            String str = "";
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str == null || str.isEmpty()) {
                break;
            }
            String[] strs = str.trim().split("\\s+");
            if (strs.length != 2) {
                break;
            }
            int n = Integer.parseInt(strs[0]);
            int m = Integer.parseInt(strs[1]);
            MyLinkedList list = new MyLinkedList(n);
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            boolean reversed = false;
            for (int i = 0; i < m; i++) {
                try {
                    str = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                strs = str.trim().split("\\s+");
                assert (strs.length == 3 || strs.length == 1);

                int command = Integer.parseInt(strs[0]);
                if (command == 4) {
                    reversed = !reversed;
                    // list.reverse();
                } else {
                    int x = Integer.parseInt(strs[1]);
                    int y = Integer.parseInt(strs[2]);
                    if (command == 3) {
                        list.swap(x, y);
                    } else {
                        int op;
                        if (reversed) {
                            op = 3 - command;
                        } else {
                            op = command;
                        }
                        if (op == 1) {
                            list.moveLeft(x, y);
                        } else if (op == 2) {
                            list.moveRight(x, y);
                        }
                    }
                }
                // list.check();
                // list.print(reversed);
            }
            System.out.print(String.format("Case %d: ", caseNum));
            System.out.println(list.sum(reversed));
            caseNum++;
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

        new Main().work();

        if (isLocal) {
            inStream.close();
            // outStream.close();
        }
    }
}
