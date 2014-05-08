package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 2 (a)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Shows how many bits are used to represent integers with your compiler,
 * states the largest positive integer that can be represented.
 *
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW02a {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW02a-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// ------- method 1: ask Java -------
		
		// get integer size
		int intSize = Integer.SIZE;
		
		// get maximum integer
		int maxInt = Integer.MAX_VALUE;
		
		System.out.printf("Method 1:\n");
		System.out.printf("There are %d bits used to store an integer.\n",intSize);
		System.out.printf("The largest positive integer is %d\n\n",maxInt);
		
		// ------- method 2: following the hint -------
		int myInt = 1;
		int bits = 1;
		
		while(myInt > 0){
			myInt *= 2;
			bits++;
		}
		
		System.out.printf("Method 2:\n",intSize);
		System.out.printf("There are %d bits used to store an integer.\n",bits);
		System.out.printf("The largest positive integer is %d\n\n",(int)Math.pow(2, bits));		
		

	}

}
