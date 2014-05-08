package HW03;

public class problem04 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW03-4-out.txt");
		
		// get values obtained from the bisection method (problem 1)
		double x = 1.5;
		
		System.out.printf("x = %.14f\n", findNRzero(x, 1e-14));
	}
	
	/**
	 * Declaration of the function we're interested in
	 */
	private static double f(double x){
		return Math.sqrt(2)-x;
	}
	
	/**
	 * Declaration of the 1st derivative of f
	 */
	private static double f1(double x){
		return -1.;
	}
	
	/**
	 * Declaration of Newton-Raphson rule method
	 */
	private static double findNRzero(double x, double precision){
		
		// calculate next step of iteration
		double xNew = x - f(x)/f1(x);
		
		// return this value if precision achieved, else continue
		return (Math.abs(x-xNew)<precision)?xNew:findNRzero(xNew, precision);
	}
}
