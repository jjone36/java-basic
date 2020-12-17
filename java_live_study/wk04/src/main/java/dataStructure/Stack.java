package dataStructure;

public class Stack {
    private int[] arr;
    private int top;

    public Stack() {
        arr = new int[0];
        top = -1;           // 초기 index값
    }

    public void push(int data) {
        if (arr.length == this.top) {
            int[] tmp = new int[arr.length + 1];
            for (int idx = 0; idx < arr.length; idx++) {
                tmp[idx] = arr[idx];
            }
            tmp[top+1] = data;
            this.arr = tmp;
        }
        arr[++top] = data;
    }

    public int pop() throws Exception {
        if (top >= 0) {
            return arr[top--];
        } else {
            throw new Exception("Stack Is Empty");
        }
    }
}
