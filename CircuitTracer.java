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
		List<TraceState> bestPaths = new ArrayList<>(); // Initialize an empty list that stores TraceState objects

		// Add a new initial TraceState object for each open position
		Point start = circuitBoard.getStartingPoint();
		Point end = circuitBoard.getEndingPoint();
		int[][] directions = { {0,1}, {1,0}, {0,-1}, {-1,0} };
		boolean directConnection = false;
		for (int[] dir : directions) {
			int adjRow = start.x + dir[0];
			int adjCol = start.y + dir[1];
			if (adjRow == end.x && adjCol == end.y) {
				// '1' is directly adjacent to '2'
				System.out.println(circuitBoard); // Just print the board as the solution
				directConnection = true;
				break;
			}
		}
		if (!directConnection) {
			for (int[] dir : directions) {
				int newRow = start.x + dir[0];
				int newCol = start.y + dir[1]; 
				if (circuitBoard.isOpen(newRow, newCol)) {
					stateStore.store(new TraceState(circuitBoard, newRow, newCol));
				}
			}
			// Main search loop
			while (!stateStore.isEmpty()) {
				TraceState current = stateStore.retrieve();

				// If we found the solution 
				if (current.isSolution()) {
					if (bestPaths.isEmpty() || current.pathLength() == bestPaths.get(0).pathLength()) {
						bestPaths.add(current);
					} else if (current.pathLength() < bestPaths.get(0).pathLength()) { // Found a better path
						bestPaths.clear();
						bestPaths.add(current);
					}
				} else {
					// Generate all valid next TraceState objects and add them to stateStore
					for (int[] dir2 : directions) {
						int nextRow = current.getRow() + dir2[0];
						int nextCol = current.getCol() + dir2[1];
						if (current.isOpen(nextRow, nextCol)) {
							try {
								stateStore.store(new TraceState(current, nextRow, nextCol));
							} catch (Exception e) {
								// Ignore invalid moves
							}
						}
					}
				}
			}
			// Output results to console
			if (bestPaths.isEmpty()) {
				System.out.println("No valid solutions found.");
			} else {
				for (TraceState solution : bestPaths) {
					System.out.println(solution);
				}
			}
		}
		//TODO: output results to console or GUI, according to specified choice
	}
}
		// class CircuitTracer
