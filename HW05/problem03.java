package HW05;

public class problem03 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW05-3-out.txt");
		
		// params
		int N = 10000000; // number of evaluations
		double a = 1,	  // lower limit
			   b = 2;	  // upper limit

		// do Monte Carlo integration
		double[] result = mcInt(a,b,N);
		
		// print result
		System.out.printf("I = %.4f +/- %.4f", result[0], result[1]);
	}
	
	/**
	 * Generates a uniformly distributed random number in the interval [a,b]
	 */
	private static double randIn(double a, double b){
		double r = Math.random(); // get uniformly distributed r.n.
		return (b-a)*r+a;
	}
	
	/**
	 * function we want to integrate at x
	 */
	private static double f(double x){
		return Math.log(x);
	}
	
	/**
	 * Implementation of Monte Carlo integration
	 * @param a lower integral limit
	 * @param b upper integral limit
	 * @param N number of evaluations
	 * @return returns an array whose
	 * 				- first entry is the result of the integration
	 * 				- second entry is the error bar
	 */
	private static double[] mcInt(double a, double b, int N){
		
		double  fSum = 0,		// here we sum up the (random) function evaluations
				fSquaredSum = 0;// here we keep track of f^2 for each evaluation
		
		// do N evaluations ...
		for(int i=0; i<N; i++){
			double f = f(randIn(a,b)); // ... randomly
			fSum += f;	// update both sums
			fSquaredSum += f*f;
		}
		
		// allocate the result array
		double[] result = new double[2];
		
		double fBar = fSum/N,
			   fSquaredBar = fSquaredSum/N;
		
		result[0] = (b-a)*fBar; // integral
		result[1] = Math.sqrt((fSquaredBar-fBar*fBar)/(N-1)); // error bar
		
		return result;
	}
}
