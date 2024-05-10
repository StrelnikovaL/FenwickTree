import java.io.*;
import java.util.Arrays;

public class Main {
    public static int[] deleteElem(int index, int[] arr) {
        int[] newArr = new int[arr.length - 1];
        for (int i = 0; i < index; i++) {
            newArr[i]  = arr[i];
        }
        for (int i = index; i < newArr.length; i++) {
            newArr[i]  = arr[i+1];
        }
        return newArr;
    }
    public static void main(String[] args) throws IOException {
        Generator generator = new Generator();
        int[] array = generator.generatedArray();
        FenwickTree ft = new FenwickTree();

        long[] timeAdd = new long[array.length];
        int[] opAdd = new int[array.length];
        PrintWriter writerTime = new PrintWriter(new FileWriter("TimeAdd.txt"));
        PrintWriter writerOp = new PrintWriter(new FileWriter("OperationsAdd.txt"));
        for (int i = 0; i < array.length; i++) {
            long startTime = System.nanoTime();
            ft.k = 0;
            ft.add(i, array);
            long finishTime = System.nanoTime();
            int operations = ft.k;
            long time = finishTime - startTime;
            timeAdd[i] = time;
            opAdd[i] = operations;
            writerTime.println(time);
            writerOp.println(operations);
        }
        writerTime.flush();
        writerOp.flush();

        long[] timeSum = new long[100];
        int[] opSum = new int[100];
        PrintWriter writerTime2 = new PrintWriter(new FileWriter("TimeSum.txt"));
        PrintWriter writerOp2 = new PrintWriter(new FileWriter("OperationsSum.txt"));
        Segment[] segments = generator.chooseSegment(array);
        for (int i = 0; i < segments.length; i++) {
            long startTime = System.nanoTime();
            ft.k = 0;
            int indexStart = segments[i].left;
            int indexEnd = segments[i].right;
            int partialSum = ft.partialSum(indexStart, indexEnd, array);
            long finishTime = System.nanoTime();
            int operations = ft.k;
            long time = finishTime - startTime;
            timeSum[i] = time;
            opSum[i] = operations;
            writerTime2.println(time);
            writerOp2.println(operations);

        }
        writerTime2.flush();
        writerOp2.flush();

        long[] timeDelete = new long[1000];
        long[] opDelete = new long[1000];
        PrintWriter writerTime3 = new PrintWriter(new FileWriter("TimeDelete.txt"));
        PrintWriter writerOp3 = new PrintWriter(new FileWriter("OperationsDelete.txt"));
        int[] chosenInd = generator.chooseIndexes(array);
        for (int i = 0; i < chosenInd.length; i++) {
            int index = chosenInd[i];
            if (index < ft.tree.length) {
                array = deleteElem(index, array);
                long startTime = System.nanoTime();
                ft.k = 0;
                ft.delete(index, array);
                long finishTime = System.nanoTime();
                int operations = ft.k;
                long time = finishTime - startTime;
                timeDelete[i] = time;
                opDelete[i] = operations;
                writerTime3.println(time);
                writerOp3.println(operations);
            }
        }
        writerTime3.flush();
        writerOp3.flush();

        System.out.println("average time of deleting: " + (int)Arrays.stream(timeDelete).average().orElse(0.0));
        System.out.println("average number of operations of deleting: " + (int)Arrays.stream(opDelete).average().orElse(0.0));
        System.out.println("average time of adding: " + (int)Arrays.stream(timeAdd).average().orElse(0.0));
        System.out.println("average number of operations of adding: " + (int)Arrays.stream(opAdd).average().orElse(0.0));
        System.out.println("average Time of partial sum: " + (int)Arrays.stream(timeSum).average().orElse(0.0));
        System.out.println("average number of operations of partial sum: " + (int)Arrays.stream(opSum).average().orElse(0.0));

    }
}
