import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {

	/** Launch the program. 
	 * 
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 */
	public static void main(String[] args) {
		new CircuitTracer(args); //create this with args
	}

	/** Print instructions for running CircuitTracer from the command line. */
	private void printUsage() {
		System.out.println("Usage: javac CircuitTracer.java && java CircuitTracer -s|-q -c|-g <input-file>");
		System.out.println(" -s : use a stack-based search"); 
		System.out.println(" -q : use a queue-based search");
		System.out.println(" -c : output results to the console");
		System.out.println(" -g : output results to a GUI");
		System.out.println(" <input-file> : path to your circuit board text file");
		System.exit(1);
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
	}

	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 */
	public CircuitTracer(String[] args) {
		// Parse and validate command line args
		if (args.length != 3) {
			printUsage();
			return;
		}
		List<String> argList = java.util.Arrays.asList(args); // Conver to list for easier searching
		// Check to make sure they chose console or GUI output
		if (!(argList.contains("-c") || argList.contains("-g"))) {
			System.out.println("You need to choose console (-c) or GUI (-g) output");
			printUsage();
			return;
		}
		// initialize an empty storage object
		Storage<TraceState> stateStore;
		if (args[0].equals("-s")) {
			stateStore = Storage.getStackInstance();
		} else if (args[0].equals("-q")) {
			stateStore = Storage.getQueueInstance();
		} else {
			System.out.println("First argument must be -s (stack) or -q (queue)");
			printUsage();
			return;
		}
		// Read in the circuit board from the given file with exception handling
		CircuitBoard circuitBoard = null;
		try {
			circuitBoard = new CircuitBoard(args[2]);
		} catch (Exception e) {
			System.out.println("Error reading circuit board file: " + args[2]);
			e.printStackTrace();
			System.exit(1);
		}

		// Run the search for best paths
		
		//TODO: initialize the Storage to use either a stack or queue
		//TODO: read in the CircuitBoard from the given file
		//TODO: run the search for best paths
		//TODO: output results to console or GUI, according to specified choice
	}
	
} // class CircuitTracer
