package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 2 (c)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * States the smallest float/double that can be represented.
 *
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW02c {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW02c-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// ------- method 1: ask Java -------
		
		// get minimum float
		float minFloat = Float.MIN_VALUE;
		
		// get minimum double
		double minDouble = Double.MIN_VALUE;
		
		System.out.printf("Method 1:\n");
		System.out.printf("The smallest float is %.3e " +
				"and the smallest double is %.3e\n\n",minFloat, minDouble);
		
		// ------- method 2: following the hint -------
		float myFloat = 1;
		
		while(myFloat > 0){
			myFloat /= 2;
		}
		
		double myDouble = 1;
		
		while(myDouble > 0){
			myDouble /= 2;
		}
		
		System.out.printf("Method 2:\n");
		System.out.printf("The smallest float is %.3e " +
				"and the smallest double is %.3e\n\n",myFloat, myDouble);		

	}

}
