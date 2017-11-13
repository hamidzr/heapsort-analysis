public abstract class Sorter {
    // private int[] input; // input array QUESTION how to define an abstract attribute to be able to get the size down

    // NOTICE don't do any computation in the constructor to have accurate timing

    public abstract void sort();

    // sorts and times sorting
    public void time() {
        // System.out.println("sorting an array of size " + input.length);
        System.out.println("sorting..");
        long startTime = System.nanoTime();
        sort();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println(this.getClass().getSimpleName() + " took " + duration + " nanoseconds.");
    }
}
