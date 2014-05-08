package HW05;

public class problem02 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW05-2-out.txt");
		
		// params
		int Nsamples = 10000000, // number of samples
			count = 0; // number of random numbers in [-1,1]
		
		// generate a lot of random numbers and forget them immediately
		for(int i=0; i<Nsamples; i++){
			if(Math.abs(lorentzRand())<1) count++; // except if they're |x|<1
		}
		
		// print the result
		System.out.printf("The fraction of random numbers " +
				"with modulus less than 1 is q = %.4f", (double)count/Nsamples);

	}
	
	/**
	 * Function that converts a uniformly distributet (built in) random number
	 * to a Lorentz distributed random number
	 */
	private static double lorentzRand(){
		double r = Math.random(); // get uniformly r.n.
		return Math.tan(Math.PI*(r-0.5)); // use formula derived above
	}
}
