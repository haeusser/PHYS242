package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 1 (a)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Hello World Program
 *
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW01a {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW01a-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// print "Hello world" to the file specified
		System.out.print("Hello world.");
	}

}
