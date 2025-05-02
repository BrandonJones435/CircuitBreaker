****************
* Project circuit
* CS-221-3
* 4/30/25
* Brandon Jones 
**************** 

OVERVIEW:

 Concisely explain what the program does. If this exceeds a couple
 of sentences, you're going too far. The details go in other
 sections.


INCLUDED FILES:

 List the files required for the project with a brief
 explanation of why each is included.

 e.g.
 * CircuitTracer.java - source file
 * CircuitBoard.java - source file
 * CircuitTracerTester.java - Tester file
 * invalid#.dat - invalid formated maze files
 * valid#.dat - valid formated maze files
 * Storage.java - source file
 * TraceState.java - source file
 * InvalidFileFormatException.java - file used to throw specific exception
 * OccupiedPositionException.java - file used to throw specific exception
 * README - this file

COMPILING AND RUNNING:

1. Open your terminal.
2. Navigate to the directory containing all your source files.
3. Compile all Java files:
   javac *.java
4. Run the program with:
   java CircuitTracer -s|-q -c|-g <input-file>
   - -s : use a stack-based search
   - -q : use a queue-based search
   - -c : output results to the console
   - -g : output results to a GUI window
   - <input-file> : path to your circuit board text file

EXAMPLES:
   java CircuitTracer -s -c valid1.dat
   java CircuitTracer -q -g valid2.dat

NOTES:
- If no valid solution exists, the program will print "No valid solutions found." (in the console or GUI).
- If the input file is invalid, an error message will be displayed.
- If -g is specified, a GUI window will open to display the results; otherwise, results are printed to the terminal.

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

This program is desinged to find the shortest path between two components on a circuit board as described by the input file. The program supports sack-based and queue-based search strategies. This is possible via the storage.java file and is specified by the user in their command line input. 
 
 The program uses a stack or a queue to manage the search for valid paths from the start to the end component. Each step, it explores possible moves (up, down, left, right) from the current position. It checks that the move is within bounds and not blocked. If the path is valid than it repeats the process. It does this until the starting position is connect to the end position. The path that has the shortest amount of moves is collect and reported. 

 **Main Classes and Responsiblities 

- **CircuitTracer**: Handles command-line argument parsing, initializes the search, and manages output. It is the main entry point of the program.
- **CircuitBoard**: Represents the circuit board, reads and validates the board from a file, and provides access to board data and start/end points.
- **TraceState**: Encapsulates the current state of a path being traced, including the current position and the path taken so far.
- **Storage<T>**: Abstracts the storage mechanism for search states, allowing the program to use either a stack or a queue for search (selected by the user).
- **InvalidFileFormatException / OccupiedPositionException**: Custom exceptions for robust error handling and clear reporting of invalid input or illegal moves.

If I were responsible for designing the program's classes, I would have the queue-based storage system be the only storage system. This would reduce the program's complexity and lessen the number of choices that the user needs to worry about. The queue system also allows a breadth-first search, which leads the algorithm to find the shortest path in a graph without needing extra logic to track path lengths.


TESTING:

I first checked to make sure that the exception handling for the command line inputs was correct. I did this by trying a variety of different commands that deviated from the usage instructions. Some of these included providing too many arguments, not choosing a search storage system, not choosing an output method, and using incorrect syntax like s- instead of -s. For each of these incorrect commands, I made sure that the printUsage() method was triggered. Once I confirmed that my exception handling for the command line input was correct, I used the CircuitTracerTester class to ensure that my program created correct paths for a variety of valid and invalid data files. Between these two types of testing, I believe my program to be robust against user error.


DISCUSSION:
 
One of the problems that I ran into when coding my project was that when trying to 
catch if the user put in the wrong file name, I would throw a FileNotFoundException
and call exit(1) within my catch block. This led to an error because the exit(1) code 
would never be reached. This was pretty easy to fix, though. I opted to print out 
the error with the faulty file name and then exited the program after that. 

I had a hard time compiling my project. On my Windows computer, 
I had an out-of-date Java SDK, which caused a lot of problems. I ended up just using my MacBook Air, 
which had a more up-to-date SDK. From there, I was able to compile and run my program correctly. 
Once I did that, I installed the newer Java SDK on my Windows computer and was able to get 
it working on there as well. 

I think the part that clicked the most for me was the exception handling for the arguments. Before
args in the command line were kind of a nebulous idea. But once I realised they are just like any other
parameter than can be exception handling they become a lot easier to code for. 
 
 
EXTRA CREDIT:

I did create a GUI option for the user. 

----------------------------------------------------------------------------

