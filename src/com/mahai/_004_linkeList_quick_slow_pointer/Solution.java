package com.mahai._004_linkeList_quick_slow_pointer;


import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 12/26/19
 * Time: 12:44 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class Solution {

    public static class Node {
        public int data;// 节点的对象，即内容

        // 节点的引用，指向下一个节点,注意：属性不能定义为public,这里只是为了演示方便,生产中还是要定义成 private!
        public Node next = null;

        public Node(int val) {
            data = val;
        }

    }

    /**
     * 链表
     */
    public static class LinkedList {
        int length = 0; // 链表长度，非必须，可不加
        Node head = new Node(0); // 哨兵节点

        /**
         * 尾插法构造链表
         * @param val
         */
        public void addNode(int val) {
            Node tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node(val);
            length++;
        }

        /**
         * 在哨兵结点里定义了链表长度的情况下，找到中间结点
         * @return
         */
        public Node findMiddleNode() {
            Node tmp = head.next;
            int middleLength = length / 2;
            while (middleLength > 0) {
                tmp = tmp.next;
                middleLength--;
            }
            return tmp;
        }

        /**
         * 在哨兵结点里未定义链表长度的情况下，先遍历找到链表的长度，再遍历 链表长度/2 找到中间结点
         * @return
         */
        public Node findMiddleNodeWithoutHead() {
            Node tmp = head.next;
            int length = 1;
            // 选遍历一遍拿到链表长度
            while (tmp.next != null) {
                tmp = tmp.next;
                length++;
            }

            // 再遍历一遍拿到链表中间结点
            tmp = head.next;
            int middleLength = length / 2;
            while (middleLength > 0) {
                tmp = tmp.next;
                middleLength--;
            }
            return tmp;
        }

        /**
         * 使用快慢指针查找找到中间结点
         * @return
         */
        public Node findMiddleNodeWithSlowFastPointer() {

            Node slow = head.next;
            Node fast = head.next;

            while (fast != null && fast.next != null) {
                // 快指针走两步
                fast = fast.next.next;
                // 慢指针走一步
                slow = slow.next;
            }

            return slow;
        }

        /**
         * 查找倒序第 k 个结点
         * @param k
         * @return
         * @throws Exception
         */
        public Node findKthToTail(int k) throws Exception {
            Node slow = head.next;
            Node fast = head.next;

            // 快指针先移到第k个结点
            int tmpK = k - 1;
            while (tmpK > 0 && fast != null) {
                fast = fast.next;
                tmpK--;
            }

            // 临界条件：k大于链表长度
            if (fast == null) {
                throw new Exception("K结点不存在异常");
            }

            // slow 和 fast 同时往后移，直到 fast 走到尾结点
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }

        public void reversedKthToTail(int k) throws Exception {
            // 直接调已实现的 寻找倒序k个结点的方法，这里是 k+1
            Node KPreNode = findKthToTail(k+1);
            Node kNode = KPreNode.next;
            Node headNext = head.next;

            KPreNode.next = null;

            head.next = kNode;

            // 寻找尾结点
            Node tmp = kNode;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = headNext;
        }

        /**
         * 判断两步链表是否有相同的结点
         * @param list1
         * @param list2
         * @return
         */
        public static Node detectCommonNode(LinkedList list1, LinkedList list2) {
            int length1 = 0;        // 链表 list1 的长度
            int length2 = 0;        // 链表 list2 的长度

            Node p1 = list1.head;
            Node p2 = list2.head;

            while (p1.next != null) {
                length1++;
                p1 = p1.next;
            }

            while (p2.next != null) {
                length2++;
                p2 = p2.next;
            }

            p1 = list1.head;
            p2= list2.head;

            // p1 或 p2 前进 |length1-length2| 步
            if (length1 >= length2) {
                int diffLength = length1-length2;
                while (diffLength > 0) {
                    p1 = p1.next;
                    diffLength--;
                }
            } else {
                int diffLength = length2-length1;
                while (diffLength > 0) {
                    p2 = p2.next;
                    diffLength--;
                }
            }
            // p1,p2分别往后遍历，边遍历边比较，如果相等，即为第一个相交结点
            while (p1 != null && p2.next != null) {
                p1 = p1.next;
                p2 = p2.next;
                if (p1.data == p2.data) {
                    // p1，p2 都为相交结点，返回 p1 或 p2
                    return p1;
                }
            }
            // 没有相交结点，返回空指针
            return null;
        }

        /**
         * 判断是否有环,返回快慢指针相遇结点,否则返回空指针
         * @return
         */
        public Node detectCrossNode() {
            Node slow = head;
            Node fast = head;

            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;

                if (fast == null) {
                    return null;
                }

                if (slow.data == fast.data) {
                    return slow;
                }
            }
            return null;
        }


        public Node getRingEntryNode() {
            // 获取快慢指针相遇结点
            Node crossNode = detectCrossNode();

            // 如果没有相遇点，则没有环
            if (crossNode == null) {
                return null;
            }

            // 分别定义两个指针，一个指向头结点，一个指向相交结点
            Node tmp1 = head;
            Node tmp2 = crossNode;

            // 两者相遇点即为环的入口结点
            while (tmp1.data != tmp2.data) {
                tmp1 = tmp1.next;
                tmp2 = tmp2.next;
            }

            return tmp1;
        }


        /**
         * 打印链表
         */
        public void printList() {
            Node tmp = head.next;
            List<Integer> arr = new ArrayList<>();
            while (tmp != null) {
                arr.add(tmp.data);
                tmp = tmp.next;
            }
            int length = arr.size();
            for (int i = 0; i < length; i++) {
                if (i != arr.size() -1) {
                    System.out.print(arr.get(i) + "--->");
                } else {
                    System.out.print(arr.get(i));
                }
            }

            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        LinkedList linkedList1 = new LinkedList();
        int[] arr1 = {1,2,3,4,5,6,7};


        for (int i = 0; i < arr1.length; i++) {
            linkedList1.addNode(arr1[i]);
        }


        Node middile;
        Node tmp = linkedList1.head;
        int length = 3;
        while (length > 0) {
            length--;
            tmp = tmp.next;
        }
        middile = tmp;

        tmp = linkedList1.head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = middile;

        Node crossNode = linkedList1.detectCrossNode();



        Node ringNode = linkedList1.getRingEntryNode();
        System.out.println("cross.value = " + ringNode.data);
    }
}