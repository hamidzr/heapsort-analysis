// space: O(n)
public class MergeSort extends Sorter {

    private int[] input;

    public MergeSort(int[] arr) {
        input = arr;
    }

    // merge two subarrays of arr separated by midIdx
    private void merge(int arr[], int leftIdx, int midIdx, int rightIdx) {
        // Find sizes of two subarrays to be merged
        int n1 = midIdx - leftIdx + 1;
        int n2 = rightIdx - midIdx;

        /* Create temp arrays */
        int left[] = new int[n1];
        int right[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            left[i] = arr[leftIdx + i];
        for (int j = 0; j < n2; ++j)
            right[j] = arr[midIdx + 1 + j];

        // merge the temp arrays

        // initial indexes of first and second subarrays
        int i = 0, j = 0;

        // initial index of merged subarry array
        int k = leftIdx;
        while (i < n1 && j < n2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of left[] if any
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }

        // copy remaining elements of right[] if any
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }

    public void split(int arr[], int l, int r) {
        if (l < r) {
            int middle = (l+r)/2;

            // sort
            split(arr, l, middle);
            split(arr , middle+1, r);

            // merge
            merge(arr, l, middle, r);
        }
    }

    @Override
    public void sort() {
        split(input, 0, input.length-1);
    }

    @Override
    public String describe() {
        return getClass().getSimpleName();
    }
}
