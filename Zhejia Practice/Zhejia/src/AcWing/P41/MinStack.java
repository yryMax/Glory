package AcWing.P41;

public class MinStack {
    private int[] elements;
    private int[] minElements;
    private int size;

    public MinStack() {
        elements = new int[10000];
        minElements = new int[10000];
        size = 0;
    }

    public void push(int x) {
        elements[size] = x;
        minElements[size] = (size == 0) ? x : Math.min(x, minElements[size - 1]);
        size++;
    }

    public void pop() {
        elements[size] = 0;
        minElements[size] = 0;
        size--;
    }

    public int top() {
        return elements[size - 1];
    }

    public int getMin() {
        return minElements[size - 1];
    }
}
