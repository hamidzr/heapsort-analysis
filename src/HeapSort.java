public class HeapSort extends Sorter {
    private int n = 2;

    public HeapSort(int n){
        this.n = n;
    }

    public HeapSort(){
    }

    @Override
    public void sort(int[] numbers) {
        int size = numbers.length;

        // build the heap inplace
        for (int i = size / 2 - 1; i >= 0; i--)
            heapify(numbers, size, i);

        // extract max
        for (int i=size-1; i>=0; i--)
        {
            // move root to end
            swap(numbers, 0, i);
            heapify(numbers, i, 0);
        }
    }

    public void heapify(int[] arr, int heapSize, int rootIndex) {
        int largest = rootIndex;
        int left = 2*rootIndex + 1;
        int right = 2*rootIndex + 2;

        // left > largest = root
        if (left < heapSize && arr[left] > arr[largest])
            largest = left;

        // right > largest
        if (right < heapSize && arr[right] > arr[largest])
            largest = right;

        // If largest is not root
        if (largest != rootIndex)
        {
            swap(arr, rootIndex, largest);
            heapify(arr, heapSize, largest);
        }
    }

    // swap elements in a list
    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
