package com.lzw.solutions.lintcode.p2471;

public class ListNode {
    private int val;
    private ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
        next = null;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode node) {
        next = node;
    }

    public int getVal() {
        return val;
    }

    public static String getListString(ListNode node) {
        String str = "";
        while (node != null) {
            str += String.valueOf(node.getVal());
            str += "->";
            node = node.next;
        }
        str += "null";
        return str;
    }
}