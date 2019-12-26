package com.mahai._004_linkeList_invert;

import com.mahai.model.Node;
import sun.awt.OSInfo;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 12/26/19
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {
    /**
     * 链表
     */
    public static class LinkedList {
        int length = 0; // 链表长度，非必须，可不加
        Node head = null; // 翻转链表我们不用哨兵结点

        /**
         * 构造链表
         * @param val
         */
        public void addNode(int val) {
            if (head == null) {
                head = new Node(val);
            } else {
                Node tmp = head;
                while (tmp.getNext() != null) {
                    tmp = tmp.getNext();
                }
                tmp.setNext(new Node(val));
            }
        }

        /**
         * 翻转结点 node 开始的链表
         * @param head
         * @return
         */
        public Node invertLinkedList(Node head) {
            if (head.getNext() == null) {
                return head;
            }

            // 步骤 1: 先翻转 head 之后的链表
            Node newHead = invertLinkedList(head.getNext());

            // 步骤 2: 再把head节点的后继结节指向 head，head 的后继节点设置为空
            head.getNext().setNext(head);
            head.setNext(null);

            // 步骤 3
            return newHead;
        }

        /**
         * 迭代翻转
         */
        public void iterationInvertLinkedList() {
            // 步骤 1
            Node pre = head;
            Node cur = pre.getNext();
            pre.setNext(null);

            while (cur != null) {
                /**
                 * 务必注意：在 cur 指向 pre 之前一定要先保留 cur 的后继结点，不然 cur 指向 pre 后就再也找不到后继结点了
                 * 也就无法对 cur 后继之后的结点进行翻转了
                 */
                Node next = cur.getNext();
                cur.setNext(pre);
                pre = cur;
                cur = next;
            }
            // 此时 pre 为新的头结点
            head = pre;
        }

        /**
         * 打印链表
         */
        public void printList() {
            Node tmp = head;
            while (tmp != null) {
                System.out.println("i = " + tmp.getData());
                tmp = tmp.getNext();
            }
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        int[] arr = {4,3,2,1};
        for (int i = 0; i < arr.length; i++) {
            linkedList.addNode(arr[i]);
        }

        // 递归翻转
        //Node newHead = linkedList.invertLinkedList(linkedList.head);
        // 设置新的头结点
        //linkedList.head = newHead;


        // 迭代翻转
        linkedList.iterationInvertLinkedList();

        linkedList.printList();

    }
}