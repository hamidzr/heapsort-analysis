import java.util.Arrays;
import java.util.LinkedList;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;



public class Driver {
	static public LinkedList<Option> options = new LinkedList<Option>();

    public static void main(String[] args) {
        // TODO remove tests for production
        // run some tests
        heapTest();

		// setup commandline arguments
		String[] mandatory_args = {"array_size", "max_number"};
		createCommandLineOptions();
		CommandLineUtilities.initCommandLineParameters(args, options, mandatory_args);
        int arraySize = Integer.parseInt(CommandLineUtilities.getOptionValue("array_size"));
        int maxNumber = Integer.parseInt(CommandLineUtilities.getOptionValue("max_number"));

        // generate test array
        int[] testArray = generateArray(arraySize, maxNumber);


        // test different algorithms / implementations
        Sorter heapSort = new HeapSort(testArray, 2);
        heapSort.time();

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

    private static void heapTest() {
        int[] testArray = {-10,5, -4, 17, 8, 1,2,3,4};
        Heap testHeap = new Heap(testArray,2,true, true);
        try {
            if (testHeap.getRoot() != 17)
                throw new Exception("bad getRoot");
            if (testHeap.extractRoot() != 17)
                throw new Exception("bad extract");
            if (testHeap.extractRoot() != 8)
                throw new Exception("didn't bubble down properly");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
