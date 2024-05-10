import java.util.Random;

public class Generator {
    public  int[] generatedArray() {
        int[] array = new int[10000];
        Random rd = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rd.nextInt();
        }
        return array;
    }
    public Segment[] chooseSegment(int[] arrOfElems) {
        Segment[] array = new Segment[100];
        Random rd = new Random();
        for (int i = 0; i < array.length; i++) {
            int index1;
            index1 = rd.nextInt(10000);
            int index2;
            do {
                index2 = rd.nextInt(10000);
            } while (index2 == index1);

            Segment segment;
            if (index1 > index2) {
                segment = new Segment(index2, index1);
            } else {
                segment = new Segment(index1, index2);
            }
            array[i] = segment;
        }
        return array;
    }
    public int[] chooseIndexes(int[] arrOfElems) {
        int[] array = new int[1000];
        Random rd = new Random();
        for (int i = 0; i < array.length; i++) {

            int index = rd.nextInt(10000);
            array[i] = index;
        }
        return array;
    }
}
