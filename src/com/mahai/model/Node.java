package com.mahai.model;

/**
 * Created with IntelliJ IDEA.
 * User: ronaldo
 * Date: 12/26/19
 * Time: 12:45 AM
 * To change this template use File | Settings | File Templates.
 * Description:
 */

/**
 * 链表的结点
 */
public class Node {
    private int data;// 节点的对象，即内容

    public Node getNext() {
        return next;
    }

    private Node next = null;// 节点的引用，指向下一个节点

    public Node(int data) {
        this.data = data;
    }
    public void setNext(Node next) {
        this.next = next;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}