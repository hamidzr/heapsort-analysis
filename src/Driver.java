import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/technotes/samples/hprof.html
public class Driver {
    public static void main(String[] args) {
        int[] testArray = new int[] {5,2,3,4,-4};
        Sorter heapSort = new HeapSort();
        heapSort.time(testArray);
    }
}
