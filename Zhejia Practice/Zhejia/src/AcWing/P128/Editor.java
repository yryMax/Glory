package AcWing.P128;

import java.io.PrintWriter;
import java.util.*;

public class Editor {
    static MyStack beforeCursor;
    static MyStack afterCursor;
    static MyStack maxAccumulation;
    static long accumulation;
    static PrintWriter printWriter = new PrintWriter(System.out);



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberInstructions = scanner.nextInt();
        beforeCursor = new MyStack(numberInstructions + 5);
        afterCursor = new MyStack(numberInstructions + 5);
        maxAccumulation = new MyStack(numberInstructions + 5);
        accumulation = 0;
        while (numberInstructions-- > 0)
            executeInstruction(scanner);
        printWriter.flush();

    }

    private static void executeInstruction(Scanner scanner) {
        switch (scanner.next()) {
            case "I":
                int insert = scanner.nextInt();
                beforeCursor.push(insert);
                accumulation = (maxAccumulation.isEmpty()) ? insert : accumulation + insert;
                maxAccumulation.push((maxAccumulation.isEmpty()) ? accumulation : Math.max(maxAccumulation.peek(), accumulation));
                break;
            case "D":
                Long delete = beforeCursor.pop();
                if (delete == null) break;
                accumulation -= delete;
                maxAccumulation.pop();
                break;
            case "L":
                Long movedRight = beforeCursor.pop();
                if (movedRight == null) break;
                afterCursor.push(movedRight);
                accumulation -= movedRight;
                maxAccumulation.pop();
                break;
            case "R":
                Long movedLeft = afterCursor.pop();
                if (movedLeft == null) break;
                beforeCursor.push(movedLeft);
                accumulation = (maxAccumulation.isEmpty()) ? movedLeft : accumulation + movedLeft;
                maxAccumulation.push((maxAccumulation.isEmpty()) ? accumulation : Math.max(maxAccumulation.peek(), accumulation));
                break;
            case "Q":
                int query = scanner.nextInt();
                printWriter.println(maxAccumulation.get(query));
                break;
            default:
                break;
        }
    }
}

class MyStack {
    Long[] elements;
    int size;

    public MyStack(int capacity) {
        elements = new Long[capacity];
        size = 0;
    }

    public void push(long element) {
        elements[size++] = element;
    }

    public Long peek() {
        if (size == 0) return null;
        else return elements[size - 1];
    }

    public Long pop() {
        if (size == 0) return null;
        Long top = elements[size - 1];
        elements[--size] = null;
        return top;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Long get(int index) {
        return elements[index - 1];
    }

}

