public class HeapSort extends Sorter {
    private int[] input;
    private int numChildren = 2;

    public HeapSort(int[] numbers){
        input = numbers;
    }

    public HeapSort(int[] numbers, int numChildren){
        this.numChildren = numChildren;
        input = numbers;
    }

    @Override
    public void sort() {
        // create the heap
        // Heap mHeap = new Heap(input, numChildren, true, true);
        MaxHeap mHeap = new MaxHeap(input, numChildren); // use the second implementation

        // sort
        int size = mHeap.size();
        for (int i = 0; i < size; i++) {
            mHeap.extractRoot();
        }

    }

    @Override
    public String describe() {
        return numChildren + "-ary " + getClass().getSimpleName();
    }
}
