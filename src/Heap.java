import java.util.Scanner;
import java.util.Arrays;

public class Heap{
    private int n = 2; // n-ary
    private int heapSize = 0;
    private int[] heap;

    /*
     * @param n {int} number of children
     */
    // TODO use an arralist to have dynamic capacity
    public void Heap(int capacity, int n) {
        this.n = n;
        this.heap = new int[capacity];
        Arrays.fill(heap, -1);
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
        heapifyUp(heapSize - 1);
    }
 
    public int findMin( ) throws Exception {
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
        heapifyDown(idx);        
        return keyItem;
    }
 
    private void heapifyUp(int childInd) {
        int tmp = heap[childInd];    
        while (childInd > 0 && tmp < heap[parent(childInd)])
        {
            heap[childInd] = heap[ parent(childInd) ];
            childInd = parent(childInd);
        }                   
        heap[childInd] = tmp;
    }
 
    private void heapifyDown(int idx) {
        int child;
        int tmp = heap[ idx ];
        while (kthChild(idx, 1) < heapSize)
        {
            child = minChild(idx);
            if (heap[child] < tmp)
                heap[idx] = heap[child];
            else
                break;
            idx = child;
        }
        heap[idx] = tmp;
    }
 
    // finds the minimum child
    private int minChild(int idx) {
        int bestChild = kthChild(idx, 1);
        int k = 2;
        int pos = kthChild(idx, k);
        while ((k <= n) && (pos < heapSize))
        {
            if (heap[pos] < heap[bestChild]) 
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
