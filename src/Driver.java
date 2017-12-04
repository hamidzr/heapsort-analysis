import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;



public class Driver {
	static public LinkedList<Option> options = new LinkedList<Option>();

    public static void main(String[] args) {
        // TODO remove tests for production
        // run some tests
        HeapTest.run();

		// setup commandline arguments
		String[] mandatory_args = {"array_size", "max_number"};
		createCommandLineOptions();
		CommandLineUtilities.initCommandLineParameters(args, options, mandatory_args);
        int arraySize = Integer.parseInt(CommandLineUtilities.getOptionValue("array_size"));
        int maxNumber = Integer.parseInt(CommandLineUtilities.getOptionValue("max_number"));

        // generate test array
        int[] testArray = generateArray(arraySize, maxNumber);


        // test different algorithms / implementations

        // input statistics
        System.out.println("input array -> size: " + (double)testArray.length / 1000000
                + "m, range: " + (double)maxNumber/1000000 + "m");
        System.out.println("input memory requirements: " + 4*testArray.length + " bytes or " + (double) 4*testArray.length /1048576 + " megabytes");

        System.out.println("====================== tests ===================");
        // 2-ary HeapSort
        int[] copy1a = copyArray(testArray);
        Sorter heapSort2 = new HeapSort(copy1a, 2);
        heapSort2.time();

        // 3-ary HeapSort
        int[] copy1b = copyArray(testArray);
        Sorter heapSort3 = new HeapSort(copy1b, 3);
        heapSort3.time();

        // 4-ary HeapSort
        int[] copy1c = copyArray(testArray);
        Sorter heapSort4 = new HeapSort(copy1c, 4);
        heapSort4.time();

        // 5-ary HeapSort
        int[] copy1d = copyArray(testArray);
        Sorter heapSort5 = new HeapSort(copy1d, 5);
        heapSort5.time();

        // QuickSort
        int[] copy2 = copyArray(testArray);
        Sorter quickSort = new QuickSort(copy2);
        quickSort.time();

        // MergeSort
        int[] copy3 = copyArray(testArray);
        Sorter mergeSort = new MergeSort(copy3);
        mergeSort.time();

        // TODO do I need to manually clear memory?

    }

	private static void registerOption(String option_name, String arg_name, boolean has_arg, String description) {
		OptionBuilder.withArgName(arg_name);
		OptionBuilder.hasArg(has_arg);
		OptionBuilder.withDescription(description);
		Option option = OptionBuilder.create(option_name);
		options.add(option);
	}

	private static void createCommandLineOptions() {
		registerOption("array_size", "int", true, "test array size");
		registerOption("max_number", "int", true, "max array range starting from 0");
	}

    private static int[] generateArray(int size, int maxNumber) {
        int[] numbers = new int[size];

        // genrate random number from 1 to maxNumber
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = (int)(Math.random()*maxNumber + 1);
        }

        System.out.println("generated array of size " + numbers.length + " with range " + maxNumber);
        return numbers;
    }

    protected static int[] copyArray(int[] src) {
        int[] copy = new int[src.length];
        System.arraycopy(src, 0, copy, 0, src.length);
        return copy;
    }

}
