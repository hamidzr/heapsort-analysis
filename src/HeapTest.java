package src;

import static src.Driver.copyArray;

public class HeapTest {

    public static void run() {
        int[] testArray = {-10,5, -4, 17, 8, 1,2,3,4};

        int[] copy1a = copyArray(testArray);
        Heap maxHeap = new Heap(copy1a,2,true, true);
        testHeap(maxHeap, 17, 8);

        int[] copy1b = copyArray(testArray);
        Heap minHeap = new Heap(copy1b,2,true, false);
        testHeap(minHeap, -10, -4);

        int[] copy2 = copyArray(testArray);
        Heap heap2 = new Heap(copy2,3,true, true);
        testHeap(heap2, 17, 8);




    }

    private static void testHeap(Heap heap, int first, int second) {
        try {
            if (heap.getRoot() !=first)
                throw new Exception("bad getRoot");
            if (heap.extractRoot() !=first)
                throw new Exception("bad extract");
            if (heap.extractRoot() !=second)
                throw new Exception("didn't bubble down properly");
        } catch (Exception e) {
            // System.out.println(e.getMessage());
            heap.printHeap();
            e.printStackTrace();
        }
    }
}
