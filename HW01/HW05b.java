package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 5
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW05b {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW05b-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// For simplicity, choose f(x)=sin(x)
		// Hence, f''(x)=-sin(x)
		// Let x=0.2 and h range from 0.05 ... 1
		// the error is | f''_mid(x) - f''(x) / f''(x) |
		
		double x = 0.2;

		System.out.print("# h \t\t\t f''_mid(x) \t f''(x) \t\t error \n");
		
		for(double h=0.05; h<=1; h+=0.05){
			double fmid = (Math.sin(x-h)+Math.sin(x+h)-2*Math.sin(x))/Math.pow(h, 2);
			double f = -Math.sin(x);
			double error = Math.abs((fmid-f)/f);
			
			System.out.printf("%.4e \t %.4e \t %.4e \t %.4e \n",
					h,
					fmid,
					f,
					error);
		}

	}
}
