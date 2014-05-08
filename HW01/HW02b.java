package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 2 (b)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Shows how many bits are used to represent float/double with your compiler,
 * states the largest positive float/double that can be represented.
 *
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW02b {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW02b-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// ------- method 1: ask Java -------
		
		// get float size
		int floatSize = Float.SIZE;
		
		// get maximum float
		float maxFloat = Float.MAX_VALUE;
		
		// get double size
		int doubleSize = Double.SIZE;
		
		// get maximum double
		double maxDouble = Double.MAX_VALUE;
		
		System.out.printf("Method 1:\n");
		System.out.printf("There are %d bits used to store an float " +
				"and %d bits to store a double.\n",floatSize, doubleSize);
		System.out.printf("The largest positive float is %.3e " +
				"and the largest positive double is %.3e\n\n",maxFloat, maxDouble);
		
		// ------- method 2: following the hint -------
		float myFloat = 1;
		int floatBits = 1;
		
		while(myFloat > 0){
			myFloat *= 2;
			floatBits++;
		}
		
		double myDouble = 1;
		int doubleBits = 1;
		
		while(myDouble > 0){
			myDouble *= 2;
			doubleBits++;
		}
		
		System.out.printf("Method 2:\n");
		System.out.printf("There are %d bits used to store a float.\n",floatBits);
		System.out.printf("The largest positive float is %.3e\n",Math.pow(2, floatBits));		
		System.out.printf("There are %d bits used to store a double.\n",doubleBits);
		System.out.printf("The largest positive double is %.3e\n",Math.pow(2, doubleBits));			

	}

}
