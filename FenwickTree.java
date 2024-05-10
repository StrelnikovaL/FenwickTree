public class FenwickTree {
    int[] tree;
    int k; //количество операций
    public FenwickTree() {
        tree = new int[0];
    }
    public FenwickTree(int[] array) {
        tree = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tree[i] = this.valOnIndex(i, array);
        }
    }
    private int indexCountStart(int index, int[] array) {
        return  index & (index + 1);
    }
    private int valOnIndex(int index, int[] array) {
        int indexStart =  indexCountStart(index, array);
        int partSum = 0;
        for (int i = indexStart; i <= index; i++) {
            partSum += array[i];
            k++;
        }
        return partSum;
    }
    private int sumFromStart (int index, int[] array) {
        if (index == 0) {
            return tree[index];
        }
        int current = index;
        int sum = 0;
        while (current != 0) {
            int temp = current;
            sum += tree[current];
            current = indexCountStart(current, array);
            if (current == temp) {
                current --;
            }
            k++;
        }
        return sum;
    }
    public int partialSum(int indexStart, int indexEnd, int[] array) {
        if (indexStart == 0) {
            return  sumFromStart(indexEnd, array);
        }
        return sumFromStart(indexEnd, array) - sumFromStart(indexStart - 1, array);
    }
    public void add (int index, int[] array) {
        int[] newTree = new int[tree.length + 1];
        for (int i = 0; i < index; i++) {
            newTree[i] = tree[i];
            k++;
        }
        for (int i = index; i < newTree.length; i++) {
            newTree[i] = valOnIndex(i,array);;
            k++;
        }
        tree = newTree;
    }

    public void delete(int index, int[] array) {
        int[] newTree = new int[tree.length - 1];
        for (int i = 0; i < index; i++) {
            newTree[i]  = tree[i];
            k++;
        }
        for (int i = index; i < tree.length - 1; i++) {
            newTree[i]  = valOnIndex(i, array);
            k++;
        }
        tree = newTree;
    }
    public void change(int index, int old, int fresh, int[] array) {
        int changes = fresh - old;
        int currIndex = index;
        while(currIndex < tree.length) {
            tree[currIndex] += changes;
            currIndex = currIndex | (currIndex + 1);
        }
    }
    public void print() {
        for (int el: tree) {
            System.out.println(el);
        }
    }
}

