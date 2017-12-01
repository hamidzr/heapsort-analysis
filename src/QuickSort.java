// repeated elements ..
public class QuickSort extends Sorter {
    private int[] input;

    public QuickSort(int[] arr) {
        input = arr;
    }

    /* This function takes last element as pivot,
      places the pivot element at its correct
      position in sorted array, and places all
      smaller (smaller than pivot) to left of
      pivot and all greater elements to right
      of pivot */
    private int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++) {
            // if current element is smaller than or equal to pivot
            if (arr[j] <= pivot)
            {
                i++;
                swap(arr, i, j);
            }
        }

        // swap with pivot
        swap(arr, i+1, high);

        return i+1;
    }

    void quickSort(int arr[], int startIdx, int endIdx) {
        if (startIdx < endIdx) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, startIdx, endIdx);

            // Recursively sort elements before and after parts
            quickSort(arr, startIdx, pi-1);
            quickSort(arr, pi+1, endIdx);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Override
    public void sort() {
        quickSort(input, 0, input.length-1);
    }

    @Override
    public String describe() {
        return getClass().getSimpleName();
    }
}
