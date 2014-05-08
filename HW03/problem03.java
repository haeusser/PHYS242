package HW03;

public class problem03 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW03-3-out.txt");
		
		System.out.printf("x = %.12f\n", secantZero(0.8, 1., 1e-12));
	}
	
	/**
	 * Declaration of the function we're interested in
	 */
	private static double f(double x){
		return (Math.exp(2*x) - Math.exp(-2*x)) / (Math.exp(2*x) + Math.exp(-2*x)) -x;
	}
	
	
	/**
	 * Declaration of secant rule method
	 * @param x1 corresponds to x_(n-1)
	 * @param x2 corresponds to x_(n)
	 * @param precision
	 * @return x_(n+1)
	 */
	public static double secantZero(double x1, double x2, double precision){
		
		// calculate next step of iteration
		double x = x2 - f(x2)*(x2-x1)/(f(x2)-f(x1));
		
		// return this value if precision achieved, else continue
		return (Math.abs(x-x2)<precision)?x:secantZero(x2, x, precision);
	}
}
