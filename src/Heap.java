import java.util.Arrays;

public class Heap{
    private int n = 2; // n-ary
    private int heapSize = 0;
    private boolean maxHeap = true;
    private int[] heap;

    /*
     * @param n {int} number of children
     */
    // TODO use an arralist to have dynamic capacity
    public Heap(int capacity, int numChildren, boolean isMaxHeap) {
        maxHeap = isMaxHeap;
        n = numChildren;
        heap = new int[capacity];
        Arrays.fill(heap, -1);
    }

    public Heap(int[] seed, int numChildren, boolean inPlace, boolean isMaxHeap) {
        maxHeap = isMaxHeap;
        n = numChildren;
        if (inPlace) {
            heap = seed;
            heapSize = seed.length;
            for (int i = heapSize / 2 - 1; i >= 0; i--)
                bubbleDown(i);
        } else {
            this.heap = new int[seed.length];
            Arrays.fill(heap, -1);
            for (int i = 0; i < seed.length; i++) {
                try {
                    insert(seed[i]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int extractRoot() {
        int rootValue = heap[0];
        swap(heap, 0, heapSize -1 );
        heapSize--;
        bubbleDown(0);
        return rootValue;
    }

    private void bubbleDown(int rootIndex) {
        int best = rootIndex;

        int bestChildIdx = bestChild(rootIndex);
        if (bestChildIdx < heapSize && isMisplaced(heap[bestChildIdx], heap[best])) {
            best = bestChildIdx;
            swap(heap, rootIndex, best);
            bubbleDown(best);
        }
    }

    // swaps elements in an array
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public boolean isEmpty( ) {
        return heapSize == 0;
    }
 
    public boolean isFull( ) {
        return heapSize == heap.length;
    }
 
    // clear the heap
    public void clear( ) {
        heapSize = 0;
    }
 
    // returns parent index of input
    private int parent(int i) {
        return (i - 1)/n;
    }

    public int size() {
        return heapSize;
    }
 
    // get kth child, it's equal to left and right if this is a binary heap
    private int kthChild(int i, int k) {
        return n * i + k;
    }
 
    public void insert(int x) throws Exception {
        if (isFull( ) )
            throw new Exception("Overflow Exception");
        // put the item at the end of the heap
        heap[heapSize++] = x;
        // bubbleUp
        bubbleUp(heapSize - 1);
    }
 
    public int getRoot() throws Exception {
        if (isEmpty())
            throw new Exception("Underflow Exception");           
        return heap[0];
    }
 
    public int delete(int idx) throws Exception {
        if (isEmpty() || heapSize <= idx)
            throw new Exception("Underflow Exception");
        int keyItem = heap[idx];
        heap[idx] = heap[heapSize - 1];
        heapSize--;
        bubbleDown(idx);        
        return keyItem;
    }

    // checks if two item are out of order considering if it's a max or min heap
    private boolean isMisplaced(int a, int b) {
        if (maxHeap) {
            return a > b;
        }
        return a < b;
    }
 
    private void bubbleUp(int childInd) {
        int tmp = heap[childInd];    
        while (childInd > 0 && isMisplaced(tmp, heap[parent(childInd)]))
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
 
    // finds the "best child" min if minHeap, max if maxHeap. root candidate
    private int bestChild(int idx) {
        int bestChild = kthChild(idx, 1);
        int k = 2;
        int pos = kthChild(idx, k);
        while ((k <= n) && (pos < heapSize))
        {
            if (isMisplaced(heap[pos], heap[bestChild]))
                bestChild = pos;
            pos = kthChild(idx, k++);
        }
        return bestChild;
    }

    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++)
            System.out.print(heap[i] +" ");
        System.out.println();
    }     

    public String toString() {
        String msg = "";
        for (int i = 0; i < heapSize; i++) {
            msg += heap[i] + " ";
        }
        return msg;
    }
}
