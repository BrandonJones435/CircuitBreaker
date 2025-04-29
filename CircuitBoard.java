import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Represents a 2D circuit board as read from an input file.
 *  
 * @author mvail
 */
public class CircuitBoard {
	/** current contents of the board */
	private char[][] board;
	/** location of row,col for '1' */
	private Point startingPoint;
	/** location of row,col for '2' */
	private Point endingPoint;

	//constants you may find useful
	private final int ROWS; //initialized in constructor
	private final int COLS; //initialized in constructor
	private final char OPEN = 'O';	//capital 'o', an open position
	private final char CLOSED = 'X';//a blocked position
	private final char TRACE = 'T';	//part of the trace connecting 1 to 2
	private final char START = '1';	//the starting component
	private final char END = '2';	//the ending component
	private final String ALLOWED_CHARS = "OXT12"; //useful for validating with indexOf

	/** Construct a CircuitBoard from a given board input file, where the first
	 * line contains the number of rows and columns as ints and each subsequent
	 * line is one row of characters representing the contents of that position.
	 * Valid characters are as follows:
	 *  'O' an open position
	 *  'X' an occupied, unavailable position
	 *  '1' first of two components needing to be connected
	 *  '2' second of two components needing to be connected
	 *  'T' is not expected in input files - represents part of the trace
	 *   connecting components 1 and 2 in the solution
	 * 
	 * @param filename
	 * 		file containing a grid of characters
	 * @throws FileNotFoundException if Scanner cannot open or read the file
	 * @throws InvalidFileFormatException for any file formatting or content issue
	 */
	public CircuitBoard(String filename) throws FileNotFoundException {
		// Initialize Scanner so my program doesn't get mad at me putting it in a try block
		Scanner fileScan = null; 
		try {
			fileScan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Could not find your file given the File Name. DO BETTER");
		}
		
		// Check to make sure that the file has a first line 
		if (!fileScan.hasNextLine()) {
			fileScan.close();
			throw new InvalidFileFormatException("Your file does not have row and col parameter");
		}

		// Read the first line as a string array and split it into two integers 
		String[] dimensions = fileScan.nextLine().trim().split("\\s+");
		if (dimensions.length != 2) { // check if you have more than or less than two integers 
			fileScan.close();
			throw new InvalidFileFormatException("You have too little or too many parameters");
		}
		// Store the first two integers as rows and cols 
		try {
			ROWS = Integer.parseInt(dimensions[0]);
			COLS = Integer.parseInt(dimensions[1]);
		} catch (NumberFormatException e) {
			fileScan.close();
			throw new InvalidFileFormatException("You need to have the two parameters be integers");
		}

		// Initialize the board 
		char[][] board = new char[ROWS][COLS];

		// Read the next board 
		for (int row = 0; row < ROWS; row++) {
			// Check you can always parse more data with in the parameters
			if (!fileScan.hasNextLine()) {
				fileScan.close();
				throw new InvalidFileFormatException("Does not have enough data");
			}
			// Store each row as a string 
			String line = fileScan.nextLine();
			// Split each line into a string array 
			String[] tokens = line.trim().split("\\s+");

			// Check to make sure the number of tokens in a row is the same as the cols amount 
			if (tokens.length != COLS) {
				fileScan.close(); 
				throw new InvalidFileFormatException("You have too many or too like chars in data"); 
			}

			for (int col = 0; col < COLS; col++) {
				char c = tokens[col].charAt(0);
				if (ALLOWED_CHARS.indexOf(c) == -1) { // Checks to see if my the character is one of my ALLOWED_CHARS
					fileScan.close();
					throw new InvalidFileFormatException("One of your data points is not allowed");
				}
				board[row][col] = c; 
				if (c == START) { // If the data point I just parsed is the START point than assign it to startingPoint
					startingPoint = new Point(row, col); 
				} else if (c == END) { // If data point is the end point
					endingPoint = new Point(row, col); 
				}
			}
			fileScan.close();
		}
	}
	
	/** Copy constructor - duplicates original board
	 * 
	 * @param original board to copy
	 */
	public CircuitBoard(CircuitBoard original) {
		board = original.getBoard();
		startingPoint = new Point(original.startingPoint);
		endingPoint = new Point(original.endingPoint);
		ROWS = original.numRows();
		COLS = original.numCols();
	}

	/** Utility method for copy constructor
	 * @return copy of board array */
	private char[][] getBoard() {
		char[][] copy = new char[board.length][board[0].length];
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				copy[row][col] = board[row][col];
			}
		}
		return copy;
	}
	
	/** Return the char at board position x,y
	 * @param row row coordinate
	 * @param col col coordinate
	 * @return char at row, col
	 */
	public char charAt(int row, int col) {
		return board[row][col];
	}
	
	/** Return whether given board position is open
	 * @param row
	 * @param col
	 * @return true if position at (row, col) is open 
	 */
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length) {
			return false;
		}
		return board[row][col] == OPEN;
	}
	
	/** Set given position to be a 'T'
	 * @param row
	 * @param col
	 * @throws OccupiedPositionException if given position is not open
	 */
	public void makeTrace(int row, int col) {
		if (isOpen(row, col)) {
			board[row][col] = TRACE;
		} else {
			throw new OccupiedPositionException("row " + row + ", col " + col + "contains '" + board[row][col] + "'");
		}
	}
	
	/** @return starting Point(row,col) */
	public Point getStartingPoint() {
		return new Point(startingPoint);
	}
	
	/** @return ending Point(row,col) */
	public Point getEndingPoint() {
		return new Point(endingPoint);
	}
	
	/** @return number of rows in this CircuitBoard */
	public int numRows() {
		return ROWS;
	}
	
	/** @return number of columns in this CircuitBoard */
	public int numCols() {
		return COLS;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				str.append(board[row][col] + " ");
			}
			str.append("\n");
		}
		return str.toString();
	}
	
}// class CircuitBoard
