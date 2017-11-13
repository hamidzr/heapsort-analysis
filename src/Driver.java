import java.util.Arrays;

// https://docs.oracle.com/javase/8/docs/technotes/samples/hprof.html
public class Driver {
    public static void main(String[] args) {
        int[] testArray = new int[] {5,2,3,4,-4,5, 6, 17, 3, -2, 8};


        // check heap
        Heap testHeap = new Heap(testArray,2,true, true);
        try {
            if (testHeap.getRoot() != 17)
                throw new Exception("bad getRoot");
            if (testHeap.extractRoot() != 17)
                throw new Exception("bad extract");
            if (testHeap.extractRoot() != 8)
                throw new Exception("didn't bubble down properly");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Sorter heapSort = new HeapSort(testArray, 2);
        heapSort.time();

    }
}
