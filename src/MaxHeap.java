// a slightly different implementation of n-ary Heap which just supports a MaxHeap to allow for a simpler, more readable code
public class MaxHeap{
    private int n = 2; // n-ary
    private int heapSize = 0;
    private int[] heap;

    /*
     * @param n {int} number of children
     */
    public MaxHeap(int[] seed, int numChildren) {
        n = numChildren;
        heap = seed;
        heapSize = seed.length;
        for (int i = heapSize / 2 - 1; i >= 0; i--)
            bubbleDown(i);
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
        heap = null;
    }

    // returns parent index of input
    private int parent(int i) {
        return (i - 1)/n; // floor
    }

    public int size() {
        return heapSize;
    }

    // get kth child, it's equal to left and right if this is a binary heap
    private int kthChild(int i, int k) {
        return n * i + k;
    }

    private int[] childrenRange(int root) throws Exception{
        // n*root + 1 to n*root + n
        int start = n*root +1;
        int end = n*root +n;
        if (start > heapSize-1) throw new Exception("children out of range");
        if (end > heapSize-1) end = heapSize -1;
        int[] range = {start, end};
        return range;
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

    // checks if two item are out of order considering if it's a max or min heap
    private boolean isMisplaced(int a, int b) {
        return a > b;
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

    // largest child index
    private int bestChild(int root) {
        if (n*root +1 > heapSize -1) return root;
        int maxChildVal = -1 * Integer.MAX_VALUE;
        int maxChildIdx = 0; // init to make the compiler happy
        int[] range;
        try {
            range = childrenRange(root);
        } catch (Exception e) {
            e.printStackTrace();
            return root;
        }
        for (int i = range[0]; i <= range[1]; i++) {
            if (heap[i] > maxChildVal) {
                // this is the largest child so far
                maxChildIdx = i;
                maxChildVal = heap[i];
            }
        }
        return maxChildIdx;
    }

    public void printHeap() {
        // dump();
        for (int i = 0; i < heapSize; i++) {
            for(int j=0;j<Math.pow(n,i)&&j+Math.pow(n,i)<heapSize;j++){
                System.out.print(heap[j+(int)Math.pow(n,i)-1]+" ");
            }
            System.out.println();
        }
    }

    public String toString() {
        String msg = "";
        for (int i = 0; i < heapSize; i++) {
            msg += heap[i] + " ";
        }
        return msg;
    }
}
