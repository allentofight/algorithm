package com.mahai._003_linkedList_headInsert;


import com.mahai.model.Node;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 12/25/19
 * Time: 7:22 PM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    /**
     * 链表
     */
    public static class LinkedList {
        int length = 0; // 链表长度，非必须，可不加
        Node head = new Node(0); // 哨兵节点

        /**
         * 头插法
         * @param val
         */
        public void headInsert(int val) {
            // 构造新结点
            Node newNode = new Node(val);

            // 新结点指向头结点之后的结点
            newNode.setNext(head.getNext());

            // 头结点指向新结点
            head.setNext(newNode);
        }

        /**
         * 打印链表
         */
        public void printList() {
            Node tmp = head.getNext();
            while (tmp != null) {
                System.out.println("i = " + tmp.getData());
                tmp = tmp.getNext();
            }
        }

        /**
         * 删除指定的结点
         * @param deletedNode
         */
        public void removeSelectedNode(Node deletedNode) {
            // 如果此结点是尾结点我们还是要从头遍历到尾结点的前继结点，再将尾结点删除
            if (deletedNode.getNext() == null) {
                Node tmp = head;
                while (tmp.getNext() != deletedNode) {
                    tmp = tmp.getNext();
                }
                // 找到尾结点的前继结点，把尾结点删除
                tmp.setNext(null);
            } else {
                Node nextNode = deletedNode.getNext();
                // 将删除结点的后继结点的值赋给被删除结点
                deletedNode.setData(nextNode.getData());;
                // 将 nextNode 结点删除
                deletedNode.setNext(nextNode.getNext());;
                nextNode.setNext(null);
            }
        }

        public static void main(String[] args) {
            LinkedList linkedList = new LinkedList();
            int[] arr = {1,2,3,4};

            // 头插法构造链表
            for (int i = 0; i < arr.length; i++) {
                linkedList.headInsert(arr[i]);
            }

            // 删除指定结点（假设指定值为 2 的结点）
            Node tmp = linkedList.head;
            while (tmp.getData() != 2) {
                tmp = tmp.getNext();
            }
            linkedList.removeSelectedNode(tmp);

            linkedList.printList();
        }

    }


}