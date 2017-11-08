public abstract class Sorter {
    public abstract void sort(int[] numbers);

    // sorts and times sorting
    public void time(int[] numbers) {
        System.out.println("sorting an array of size " + numbers.length);
        long startTime = System.nanoTime();
        sort(numbers);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(this.getClass().getSimpleName() + " took " + duration + " nanoseconds.");
    }
}
