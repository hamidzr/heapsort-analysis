import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/technotes/samples/hprof.html
public class Driver {
    public static void main(String[] args) {
        int[] testArray = new int[] {5,2,3,4,-4,5, 6, 17, 3, -2, 8};
        Sorter heapSort = new HeapSort();

        Heap binaryHeapInPlace = new Heap(testArray, 2, false, false);
        heapSort.time(testArray);

    }
}
