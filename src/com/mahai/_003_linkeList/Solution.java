package com.mahai._003_linkeList;


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
        }

        /**
         * 头插法构造链表
         * @param val
         */
        public void headInsert(int val) {
            // 构造新结点
            Node newNode = new Node(val);

            // 新结点指向头结点之后的结点
            newNode.next = head.next;

            // 头结点指向新结点
            head.next = newNode;
        }

        /**
         * 删除指定的结点
         * @param deletedNode
         */
        public void removeSelectedNode(Node deletedNode) {
            // 如果此结点是尾结点我们还是要从头遍历到尾结点的前继结点，再将尾结点删除
            if (deletedNode.next == null) {
                Node tmp = head;
                while (tmp.next != deletedNode) {
                    tmp = tmp.next;
                }
                // 找到尾结点的前继结点，把尾结点删除
                tmp.next = null;
            } else {
                Node nextNode = deletedNode.next;
                // 将删除结点的后继结点的值赋给被删除结点
                deletedNode.data = nextNode.data;;
                // 将 nextNode 结点删除
                deletedNode.next = nextNode.next;;
                nextNode.next = null;
            }
        }

        /**
         * 递归翻转结点 node 开始的链表
         * @param node
         * @return
         */
        public Node invertLinkedList(Node node) {
            if (node.next == null) {
                return node;
            }

            // 步骤 1: 先翻转 node 之后的链表
            Node newHead = invertLinkedList(node.next);

            // 步骤 2: 再把 node 节点后继结点的后继结点(3)指向 node，node 的后继节点设置为空
            node.next.next = node;
            node.next = null;

            // 步骤 3: 返回翻转后的头结点
            return newHead;
        }

        /**
         * 迭代翻转
         */
        public void iterationInvertLinkedList() {
            // 步骤 1
            Node pre = head.next;
            Node cur = pre.next;
            pre.next = null;

            while (cur != null) {
                /**
                 * 务必注意：在 cur 指向 pre 之前一定要先保留 cur 的后继结点，不然 cur 指向 pre 后就再也找不到后继结点了
                 * 也就无法对 cur 后继之后的结点进行翻转了
                 */
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // 此时 pre 为头结点的后继结点
            head.next = pre;
        }

        /**
         * 迭代翻转 from 到 to 的结点
         */
        public void iterationInvertLinkedList(int fromIndex, int toIndex) throws Exception {
            // 步骤 1
            Node fromPre = null;            // from-1结点
            Node from = null;               // from 结点
            Node to = null;                 // to 结点
            Node toNext = null;             // to+1 结点

            Node tmp = head.next;
            int curIndex = 1;      // 头结点的index为1
            while (tmp != null) {
                if (curIndex == fromIndex-1) {
                    fromPre = tmp;
                } else if (curIndex == fromIndex) {
                    from = tmp;
                } else if (curIndex == toIndex) {
                    to = tmp;
                } else if (curIndex == toIndex+1) {
                    toNext = tmp;
                }
                tmp = tmp.next;
                curIndex++;
            }

            if (from == null || to == null) {
                // from 或 to 都超过尾结点不翻转
                throw new Exception("不符合条件");
            }

            // 以下使用循环迭代法翻转从 from 到 to 的结点
            Node pre = from;
            Node cur = pre.next;
            while (cur != toNext) {
                Node next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }

            if (fromPre != null) {
                fromPre.next = to;
            } else {
                // 如果 fromPre 为空，说明是从head 的后继节点开始翻转的
                head.next = to;
            }
            from.next = toNext;
        }

        /**
         * 顺序每 k 个一组翻转链表
         * @param k
         */
        public void iterationInvertLinkedListEveryK(int k) {
            Node tmp = head.next;
            int step = 0;               // 计数，用来找出首结点和尾结点

            Node startK = null;         // k个一组链表中的头结点
            Node startKPre = head;      // k个一组这段链表头结点的前置结点
            Node endK;                  // k个一组链表中的尾结点
            while (tmp != null) {
                // 提前保存 tmp 的下一个节点,因为由于翻转，tmp 的后继结点会变
                Node tmpNext = tmp.next;
                if (step == 0) {
                    // k 个一组链表区间的头结点
                    startK = tmp;
                    step++;
                } else if (step == k-1) {
                    // 此时找到了 k 个一组链表区间的尾结点（endK）,对这段链表用迭代进行翻转
                    endK = tmp;
                    Node pre = startK;
                    Node cur = startK.next;
                    if (cur == null) {
                        break;
                    }
                    Node endKNext = endK.next;
                    while (cur != endKNext) {
                        Node next = cur.next;
                        cur.next = pre;
                        pre = cur;
                        cur = next;
                    }
                    // 翻转后此时 endK 和 startK 分别是是 k 个一组链表中的首尾结点
                    startKPre.next = endK;
                    startK.next = endKNext;

                    // 当前的 k 个一组翻转完了，开始下一组 k 个一组的翻转
                    startKPre = startK;
                    step = 0;
                } else {
                    step++;
                }
                tmp = tmpNext;
            }
        }

        /**
         * 逆序每 k 个一组翻转链表
         * @param k
         */
        public void reverseIterationInvertLinkedListEveryK(int k) {
            // 先翻转链表
            iterationInvertLinkedList();
            // k 个一组翻转链表
            iterationInvertLinkedListEveryK(k);
            // 再次翻转链表
            iterationInvertLinkedList();
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

    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();
        int[] arr = {1,2,3,4,5};
        for (int i = 0; i < arr.length; i++) {
            linkedList.addNode(arr[i]);
        }

        // 头插法构造链表
        // for (int i = 0; i < arr.length; i++) {
        //      linkedList.headInsert(arr[i]);
        // }

        // 删除指定结点（假设指定值为 2 的结点）
        // Node tmp = linkedList.head;
        // while (tmp.data != 2) {
        //     tmp = tmp.next;
        // }
        // linkedList.removeSelectedNode(tmp);

        // 递归翻转
          Node newHead = linkedList.invertLinkedList(linkedList.head.next);
         linkedList.head.next = newHead;
         linkedList.printList();

        // 迭代翻转
        // linkedList.iterationInvertLinkedList();
        // linkedList.printList();

        // 翻转从链表从结点 from 到结点 to
        // linkedList.iterationInvertLinkedList(2, 3);
        // linkedList.printList();

        // 每 k 个一组翻转链表
        // linkedList.iterationInvertLinkedListEveryK(1);
        // linkedList.printList();

        // 逆序每 k 个一组翻转链表
//        linkedList.reverseIterationInvertLinkedListEveryK(3);
//        linkedList.printList();
    }
}