package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 1 (b)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * i.  Prints the number 19 billion in scientiﬁc notation
 * ii. Prints the value of the golden mean 
 *
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW01b {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW01b-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// i.  Prints the number 19 billion in scientiﬁc notation
		double bigNumber = 19*Math.pow(10, 9);
		System.out.printf("part (i):  %.2e\n",bigNumber);
		
		// ii. Prints the value of the golden mean 
		double goldenMean = (Math.sqrt(5)-1)/2;
		System.out.printf("part (ii): %.8e\n",goldenMean);
	}

}
