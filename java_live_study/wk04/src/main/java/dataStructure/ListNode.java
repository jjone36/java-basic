package dataStructure;

public class ListNode {
    private int value;
    private ListNode nextNode;

    public ListNode() {}

    public ListNode(int value) {
        this.value = value;
        this.nextNode = null;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNextNode() {
        return this.nextNode;
    }

    public void setNextNode(ListNode node) {
        this.nextNode = node;
    }

    public ListNode add(ListNode head, ListNode nodeToAdd, int position) {

        ListNode before = null;
        ListNode current = head;   // 1.첫번째 노드에서

        // 2.새로운 노드를 붙일 이전 노드의 위치를 찾음
        for (int i=0; i < position-1; i++) {
            before = current;
            current = current.getNextNode();
        }

        before.setNextNode(nodeToAdd);     // 4. 마지막 노드의 다음 위치를 새로운 노드로 연결
        nodeToAdd.setNextNode(current);     // 3. 마지막 노드의 위치를 삽입하는 새로운 노드의 위치 값으로 복사
        return nodeToAdd;
    }

    public ListNode remove(ListNode head, int positionToRemove) {

        ListNode before = null;
        ListNode current = head;

        // 1. 제일 앞단의 노드를 삭제하는 경우
        if (positionToRemove == 0) {
            ListNode nodeToRemove = current;
            head = nodeToRemove.getNextNode();    // 1-1. 다음 노드를 head로
            nodeToRemove.setNextNode(null);       // 1-2. 지울 노드의 링크를 제거
            return nodeToRemove;
        }

        // 2. 그 외의 경우
        // 2-1. 지울 노드가 있는 위치까지 접근함
        for (int i = 0; i < positionToRemove - 1; i++) {
            before = current;
            current = current.getNextNode();
        }
        ListNode nodeToRemove = current.getNextNode();

        ListNode next = nodeToRemove.getNextNode();
        before.setNextNode(next);              // 2-2. 지울 노드의 다음 노드를 이전 노드의 위치 값으로 복사
        nodeToRemove.setNextNode(null);        // 2-2. 지울 노드의 링크를 제거
        return nodeToRemove;
    }

    public boolean contains(ListNode head, ListNode nodeTocheck) {
        ListNode current = head;
        while (current.getNextNode() != null) {
            if (current.getNextNode() == nodeTocheck) {
                return true;
            }
            current = current.getNextNode();
        }
        return false;
    }
}
