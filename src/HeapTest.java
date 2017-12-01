public class HeapTest {

    public static void run() {
        int[] testArray = {-10,5, -4, 17, 8, 1,2,3,4};

        int[] copy1a = Driver.copyArray(testArray);
        MaxHeap maxHeap = new MaxHeap(copy1a,2);
        testHeap(maxHeap, 17, 8);

        int[] copy1b = Driver.copyArray(testArray);
        MaxHeap minHeap = new MaxHeap(copy1b,2);
        testHeap(minHeap, -10, -4);

        int[] copy2 = Driver.copyArray(testArray);
        MaxHeap heap2 = new MaxHeap(copy2,3);
        testHeap(heap2, 17, 8);


    }

    private static void testHeap(MaxHeap heap, int first, int second) {
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
