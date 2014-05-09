package HW05;

public class problem04 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW05-4-out.txt");
		
		// params
		int N = 10000000, // number of evaluations
				dim = 10; // number of dimensions
		double a = 0,	  // lower limit
			   b = 1;	  // upper limit

		// do Monte Carlo integration
		double[] result = mcInt(a,b,N, dim);
		
		// print result
		System.out.printf("I = %.4f +/- %.4f", result[0], result[1]);
	}
	
	/**
	 * Generates a uniformly distributed random number in the interval [a,b]
	 */
	private static double[] randArrayIn(double a, double b, int dim){
		
		double[] x = new double[dim];
		for(int i=0; i<dim; i++){
			x[i] = (b-a)*Math.random()+a;
		}
		return x;
	}
	
	/**
	 * function we want to integrate at x_1, x_2, ..., x_N
	 */
	private static double f(double[] x){
		double sum = 0;
		for(double e : x) sum +=e;
		return sum*sum;
	}
	
	/**
	 * Implementation of multi dimensional Monte Carlo integration
	 * with the same integration boundaries for each dimension
	 * @param a lower integral limit
	 * @param b upper integral limit
	 * @param N number of evaluations
	 * @return returns an array whose
	 * 				- first entry is the result of the integration
	 * 				- second entry is the error bar
	 */
	private static double[] mcInt(double a, double b, int N, int dim){
		
		double  fSum = 0,		// here we sum up the (random) function evaluations
				fSquaredSum = 0;// here we keep track of f^2 for each evaluation
		
		// do N evaluations ...
		for(int i=0; i<N; i++){
			double[] x = randArrayIn(a,b,dim);
			double f = f(x); // ... randomly
			fSum += f;	// update both sums
			fSquaredSum += f*f;
		}
		
		// allocate the result array
		double[] result = new double[2];
		
		double fBar = fSum/N,
			   fSquaredBar = fSquaredSum/N;
		
		result[0] = Math.pow((b-a),dim)*fBar; // integral
		result[1] = Math.sqrt((fSquaredBar-fBar*fBar)/(N-1)); // error bar
		
		return result;
	}
}
