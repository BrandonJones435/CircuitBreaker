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

1. Open your terminal using ctrl + `
2. Make sure that you are in the directory containing all of your source files
3. First run javac CircuitTracer.java
4. Then run java CircuitTracer followed by -s or -q then followed by -c or -g and lastly followed by input-file. See the usage documentation below. This is depending on whether you want a stack or queue based search. The -c or -g is whether you want the results to go to the console or GUI. 
 Following the following usage guidlines to generate your stack or queue based search as well as output either to the console or GUI. 
Usage: javac CircuitTracer.java && java CircuitTracer -s|-q -c|-g <input-file>
	 -s : use a stack-based search
	 -q : use a queue-based search
	 -c : output results to the console
	 -g : output results to a GUI
	 <input-file> : path to your circuit board text file


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

This program takes in the 

 This is the sort of information someone who really wants to
 understand your program - possibly to make future enhancements -
 would want to know.

 Explain the main concepts and organization of your program so that
 the reader can understand how your program works. This is not a repeat
 of javadoc comments or an exhaustive listing of all methods, but an
 explanation of the critical algorithms and object interactions that make
 up the program.

 Explain the main responsibilities of the classes and interfaces that make
 up the program. Explain how the classes work together to achieve the program
 goals. If there are critical algorithms that a user should understand, 
 explain them as well.
 
 If you were responsible for designing the program's classes and choosing
 how they work together, why did you design the program this way? What, if 
 anything, could be improved? 

TESTING:

I first checked my exception handling 
 How did you test your program to be sure it works and meets all of the
 requirements? What was the testing strategy? What kinds of tests were run?
 Can your program handle bad input? Is your program  idiot-proof? How do you 
 know? What are the known issues / bugs remaining in your program?


DISCUSSION:
 
 Discuss the issues you encountered during programming (development)
 and testing. What problems did you have? What did you have to research
 and learn on your own? What kinds of errors did you get? How did you 
 fix them?
 
 What parts of the project did you find challenging? Is there anything
 that finally "clicked" for you in the process of working on this project?
 
 
EXTRA CREDIT:

 If the project had opportunities for extra credit that you attempted,
 be sure to call it out so the grader does not overlook it.


----------------------------------------------------------------------------

All content in a README file is expected to be written in clear English with
proper grammar, spelling, and punctuation. If you are not a strong writer,
be sure to get someone else to help you with proofreading. Consider all project
documentation to be professional writing for your boss and/or potential
customers.
