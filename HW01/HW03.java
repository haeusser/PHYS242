package HW01;
/**
 * PHYS242 - Computational Physics
 * Homework Assignment #1
 * Problem 3
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Showcases roundoff errors
 * 
 * @param  args 	no input arguments required
 */

// import file I/O packages
import java.io.FileOutputStream;
import java.io.PrintStream;

public class HW03 {

	public static void main(String[] args) {
		
		// define output filename
		String outputFilename = "HW03-out.txt";
		
		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(outputFilename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
		
		// part (a) --------------------
		System.out.print("# Part (a)\n" +
				         "# single precision\n" +
				         "# p\tsumUp\t\tsumDown\t\tdifference\n");
		
		for(int p=2; p<8; p++){
			float N = (float)Math.pow(10, p);
			float sumUp=0, sumDown=0;
			
			for(float n=1; n<=N; n++){
				sumUp += 1/n;
			}
			
			for(float n=N; n>=1; n--){
				sumDown += 1/n;
			}
				
			System.out.printf("%d\t%e\t%e\t%e\n",
					p,
					sumUp,
					sumDown,
					Math.abs(sumUp-sumDown));	
		}
		
		
		// part (b) --------------------
		System.out.print("\n\n# Part (b)\n" +
				         "# double precision\n" +
				         "# p\tsumUp\t\tsumDown\t\tdifference\n");
		
		for(int p=2; p<8; p++){
			double N = Math.pow(10, p);
			double sumUp=0, sumDown=0;
			
			for(double n=1; n<=N; n++){
				sumUp += 1/n;
			}
			
			for(double n=N; n>=1; n--){
				sumDown += 1/n;
			}
				
			System.out.printf("%d\t%e\t%e\t%e\n",
					p,
					sumUp,
					sumDown,
					Math.abs(sumUp-sumDown));	
		}
	}

}
