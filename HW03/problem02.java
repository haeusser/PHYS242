package HW03;

public class problem02 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW03-2-out.txt");
		
		// get values obtained from the bisection method (problem 1)
		double x1 = problem01.findZeroBetween(.5, 1., 1e-4);
		double x2 = problem01.findZeroBetween(1.5, 2., 1e-4);
		
		System.out.printf("x_1 = %.14f\n", findNRzero(x1, 1e-14));
		System.out.printf("x_2 = %.14f\n", findNRzero(x2, 1e-14));
	}
	
	/**
	 * Declaration of the function we're interested in
	 */
	private static double f(double x){
		return Math.pow(x, 3)-5*x+3;
	}
	
	/**
	 * Declaration of the 1st derivative of f
	 */
	private static double f1(double x){
		return 3*Math.pow(x, 2)-5;
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
