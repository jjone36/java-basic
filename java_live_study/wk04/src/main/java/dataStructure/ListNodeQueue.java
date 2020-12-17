package dataStructure;

public class ListNodeQueue {
    private ListNode head;
    private int last;

    public ListNodeQueue() {
        this.head = new ListNode();
        this.last = 0;
    }

    public void add(int data) {
        ListNode newNode = new ListNode(data);
        ListNode.add(head, newNode, last);
    }

    public int remove() throws Exception {
        if (last != 0) {
            last--
            return ListNode.remove(head, 1).getValue();
        }
        throw new Exception("Queue is Empty");
    }
}
