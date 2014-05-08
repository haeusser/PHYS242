package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 4
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

public class HW04 {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW04-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// part (a) --------------------
		
		final double phi = (Math.sqrt(5)-1)/2;
		final int N = 51;
		
		double[] aPhi = new double[N];
		
		aPhi[0] = 1;
		aPhi[1] = phi;
		
		for(int n=2; n<N; n++){
			aPhi[n] = aPhi[n-1]*phi;
		}
		
		// part (c) --------------------
		
		float[] fPhi  = new float[N];
		double[] dPhi = new double[N];
		
		// initialize
		fPhi[0] = 1;
		fPhi[1] = (float)phi;
		
		dPhi[0] = 1;
		dPhi[1] = phi;
		
		// fill array with values
		for(int i=2; i<fPhi.length; i++){
			fPhi[i] = fPhi[i-2]-fPhi[i-1];
		}
		
		for(int i=2; i<dPhi.length; i++){
			dPhi[i] = dPhi[i-2]-dPhi[i-1];
		}
		
		
		
		
		
		
		System.out.print("# n\tphi^n (a)\t\tfloat phi^n (b)\t\tdouble phi^n (b)" +
				"\tfloat error\t\t\tdouble error\n");
		
		for(int n=0; n<N; n++){
			System.out.printf("%d\t%.10f\t%.10f\t%.10f" +
					"\t%.10f\t%.10f\n",
					n,
					aPhi[n],
					fPhi[n],
					dPhi[n],
					Math.abs(fPhi[n]-aPhi[n])/aPhi[n],
					Math.abs(dPhi[n]-aPhi[n])/aPhi[n]
					);
		}
	}
}
