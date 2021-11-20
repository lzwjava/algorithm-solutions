package com.lintcode;

public class ThreadSafeLinkedList {

    private ListNode left;
    private ListNode right;

    public ThreadSafeLinkedList() {
    }

    private void create(int element) {
        ListNode node = new ListNode(element);
        left = node;
        right = node;
    }

    public void appendLeft(int element) {
        synchronized (this) {
            if (left == null && right == null) {
                create(element);
            } else {
                ListNode node = new ListNode(element);
                node.setNext(left);
                left = node;
            }
        }
    }

    public void appendRight(int element) {
        synchronized (this) {
            if (left == null && right == null) {
                create(element);
            } else {
                ListNode node = new ListNode(element);
                right.setNext(node);
                right = node;
            }
        }
    }

    public ListNode getLinkedList() {
        return left;
    }
}