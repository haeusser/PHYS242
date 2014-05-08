package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 2 (d)
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Shows how many digits of precision there are for ﬂoating point numbers.
 * 
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW02d {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW02d-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		float myFloat = 1;
		float aSmallFloat = 1;
		float floatDigits = 1;
		
		while(myFloat + aSmallFloat != 1){
			aSmallFloat /= 10;
			floatDigits++;
		}
		
		double myDouble = 1;
		double aSmallDouble = 1;
		double doubleDigits = 1;
		
		while(myDouble + aSmallDouble != 1){
			aSmallDouble /= 10;
			doubleDigits++;
		}
		
		// the condition was "as long as the small number is different from zero"
		// when it's not met we get thrown out of the loop
		// which means that we have to subtract 1 from the number of digits
		
		System.out.printf("There are %.0f digits of precision for a float " +
				"and %.0f digits of precision for a double.\n",--floatDigits, --doubleDigits);		

	}

}
