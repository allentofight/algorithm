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
    public int data;// 节点的对象，即内容

    // 节点的引用，指向下一个节点,注意：属性不能定义为public,这里只是为了演示方便,生产中还是要定义成 private!
    public Node next = null;

    public Node(int val) {
        data = val;
    }

}