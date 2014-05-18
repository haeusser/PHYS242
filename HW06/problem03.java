package HW06;

public class problem03 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW06-3-out.txt");
		
		// parameter
		int N = 50000;
		
		// calculate log(N!) and print the result
		double logNfactorial = factorialLog(N);
		System.out.printf("log(%d!) = %.6f\n", N, logNfactorial);
		
		// get the exponential representation and print the estimate
		double[] expFormat = getExpFormat(logNfactorial);
		System.out.printf("=> %d! ~ %.6f * 10 ^ %.0f", N, expFormat[0], expFormat[1]);
	}
	
	/**
	 * Calculate log(n!)
	 * Idea: log(n!) = log(n*(n-1)*(n-2)*...*3*2*1)
	 *               = log(n) + log(n-1) + log(n-1) + ... + log(3) + log(2) + log(1)
	 * @param n integer of whose factorial we want to know the log (ln)
	 */
	public static double factorialLog(int n){
		double sum = 0;
		for(int i=2; i<=n; i++){
			sum += Math.log(i);
		}
		return sum;
	}
	
	/**
	 * Get an estimate for N! when y = log(N!) is known.
	 * @param y array with components 0: mantissa
	 *                                1: exponent of the estimate
	 */
	public static double[] getExpFormat(double y){
	/* Derivation of estimate for mantissa and exponent (to the base 10)
	 * log(x!) = y
	 *      x! = e^y = a*10^b    where a should be of the order of 1
	 *       a = e^y / 10^b ~ 1
	 *    => b = y * log10(e) = y * 0.434294481903251827651128918916...
	 *    => a = e^y / 10^b
	 *         = e^y * 10^(-b)
	 *         = e^y * e^(ln(^0^(-b)))
	 *         = e^(y-b*ln(10))
	 *         ~ 1 + (y-b*ln(10)) + ^/2 * (y-b*ln(10))^2 + ...
	 *       
	 */
		double[] result = new double[2];
		// exponent:
		result[1] = (int)(y*Math.log10(Math.E)); // !! round to int (=> mantissa != 1)
		
		// mantissa:
		result[0] = Math.exp(y-result[1]*Math.log(10));
		return result;
	}
}