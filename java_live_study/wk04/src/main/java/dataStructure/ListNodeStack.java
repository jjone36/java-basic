package dataStructure;

public class ListNodeStack {
    private ListNode head;

    public ListNodeStack() {
        this.head = new ListNode();
    }

    public void push(int data) {
        ListNode newNode = new ListNode(data);
        ListNode.add(node, newNode, 1);
    }

    public int pop() {
        ListNode nodeToRemove = ListNode.remove(head, 1);
        return nodeToRemove.getValue();
    }
}
