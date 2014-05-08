package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 6
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Recursive Bessel function
 * 
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW06 {
	
	
	/**
	 * Calculates a series of Bessel functions
	 *
	 * @param x value for Bessel function evaluation
	 * @param N highest order (should be >50)
	 */
	public static double[] Bessel(double x, int N){

		double[] j = new double[N+1]; // values of Bessel fn j_l for given x
		
		// initialize two highest j's
		j[j.length-1] = 1;
		j[j.length-2] = 1;
		
		for(int i=j.length-3; i>=0; i--){
			j[i]=j[i+1]*(2*i+3)/x-j[i+2]; // recursion formula
		}
		
		// correct entries by gauging j[0]
		double factor = (Math.sin(x)/x)/j[0];
		for(int i=0; i<j.length; i++){
			j[i]*=factor;
		}
		
		return j;
		
	}

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW06-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// x=0.1
		double[] j1 = Bessel(0.1,100);
		
		// x=1
		double[] j2 = Bessel(1,100);
		
		// x=10
		double[] j3 = Bessel(10,100);
		
		int[] l = {3,5,8,12};
		
		System.out.printf("\t x=.1 \t\t x=1 \t\t x=10 \n");
		
		for(int i=0; i<l.length; i++){
			System.out.printf("l=%d \t %.5e \t %.5e \t %.5e \n",
					l[i],
					j1[l[i]],
					j2[l[i]],
					j3[l[i]]
					);

		}

	}
}
