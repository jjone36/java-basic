package dataStructure;

public class ArrayQueue {
    private int[] arr;
    private int last;

    public ArrayQueue() {
        this.arr = new int[0];
        this.last = -1;
    }

    public void add(int data) {
        if (arr.length == last) {
            int[] tmp = new int[arr.length + 1];
            for (int i = 0; i < arr.length; i++) {
                tmp[i] = arr[i];
            }
            tmp[last] = data;
            arr = tmp;
        } else {
            arr[last] = data;
        }
    }

    public int remove() {
        if (arr.length == 0) {
            throw new Exception("Queue is Empty");
        }
        int removeElm = arr[0];
        for (int i = 1; i < last; i++) {
            arr[i-1] = arr[i];
        }
        last -= 1;
        return removeElm;
    }
}
