package org.lights1eep.listnode;

/**
 * 链表
 * @author lights1eep
 */
public class ListNode {
    /**
     * 尾插法实现链表初始化
     * @param nums  int数组
     * @return      链表头节点
     */
    public static Node initListNodeByTailInsert(int[] nums) {
        Node head = new Node();
        Node cur = head;
        for(int num : nums) {
            Node node = new Node(num);
            cur.setNext(node);
            cur = cur.getNext();
        }
        return head.getNext();
    }

    /**
     * 头插法实现链表初始化
     * @param nums  int数组
     * @return      链表头节点
     */
    public static Node initListNodeByHeadInsert(int[] nums) {
        Node head = new Node();
        Node cur = head;
        for(int num : nums) {
            Node node = new Node(num);
            node.setNext(cur.getNext());
            cur.setNext(node);
        }
        return head.getNext();
    }

    /**
     * 根据位置索引获取节点
     * @param head  链表头节点
     * @param index 位置索引
     * @return  节点
     * @throws IllegalArgumentException     index范围错误
     * @throws IndexOutOfBoundsException    位置超出链表长度
     */
    public static Node getNodeByIndex(Node head, int index) throws IllegalArgumentException, IndexOutOfBoundsException {
        if(index < 0) {
            throw new IllegalArgumentException("index要求大于等于0");
        }
        Node cur = head;
        int n = 0;
        while(cur.getNext() != null && n < index) {
            cur = cur.getNext();
            n++;
        }
        if(n != index) {
            throw new IndexOutOfBoundsException("位置索引超出链表长度");
        }
        return cur;
    }

    /**
     * 输出链表
     * @param node  链表头节点
     */
    public static void printListNode(Node node) {
        while(node != null) {
            System.out.printf("%d ", node.getValue());
            node = node.getNext();
        }
    }

    /**
     * 根据位置索引插入新节点
     * @param head  链表头节点
     * @param index 位置索引 从0开始 0表示将新节点作为头节点
     * @param node 新节点
     * @return  新的链表的头节点
     */
    public static Node insertNodeByIndex(Node head, int index, Node node) {
        Node head1 = new Node();
        head1.setNext(head);
        Node node1 = getNodeByIndex(head1, index);
        insertNode(node1, node);
        return head1.getNext();
    }

    /**
     * 在cur节点后插入next节点
     * @param cur   当前节点
     * @param next  要插入的节点
     */
    private static void insertNode(Node cur, Node next) {
        next.setNext(cur.getNext());
        cur.setNext(next);
    }
    /**
     * 根据索引删除节点
     * @param head  链表头节点
     * @param index 要删除的节点的索引 从0开始
     * @return      新的链表的头节点
     */
    public static Node deleteNodeByIndex(Node head, int index){
        Node head1 = new Node();
        head1.setNext(head);
        Node node1 = getNodeByIndex(head1, index);
        if(node1.getNext() != null) {
            node1.setNext(node1.getNext().getNext());
        }
        return head1.getNext();
    }





    /**
     * 根据位置索引更新节点
     * @param head  链表头节点
     * @param index 位置索引
     * @param value 修改值
     * @return  新链表头节点
     */
    public static Node updateNodeByIndex(Node head, int index, int value) {
        Node node = getNodeByIndex(head, index);
        node.setValue(value);
        return head;
    }

    /**
     * 反转链表
     * @param head  链表头节点
     * @return      新链表头节点
     */
    public static Node reverseListNode(Node head) {
        Node pre = null;
        Node cur = head;
        while(cur != null) {
            Node next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 获取链表长度
     * @param head  链表头节点
     * @return  链表长度
     */
    public static int getListNodeLength(Node head) {
        int n = 0;
        while(head != null) {
            n++;
            head = head.getNext();
        }
        return n;
    }

    /**
     * 判断链表是否为空
     * @param node  链表头节点
     * @return  true表示空 false表示不空
     */
    public static boolean isNull(Node node) {
        return node == null;
    }

}
