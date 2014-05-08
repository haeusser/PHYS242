package HW03;

public class problem01 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW03-1-out.txt");
		
		System.out.printf("x_1 = %.4f\n", findZeroBetween(.5, 1., 1e-4));
		System.out.printf("x_2 = %.4f\n", findZeroBetween(1.5, 2., 1e-4));
	}
	
	/**
	 * Declaration of the function we're interested in
	 */
	private static double f(double x){
		return Math.pow(x, 3)-5*x+3;
	}
	
	/**
	 * Declaration of midpoint rule method
	 * 
	 * @param a lower root estimate
	 * @param b upper root estimate
	 * @param precision desired precision, e.g. 1e-4
	 * @return midpoint rule estimate for root between a and b
	 */
	public static double findZeroBetween(double a, double b, double precision){
		
		// check if desired precision achieved
		if(Math.abs(b-a)<precision) return (a+b)/2;
		
		// midpoint
		double m = (a+b)/2.;
		
		// check if the root is between a and m or between m and b
		if(Math.signum(f(a)) != Math.signum(f(m))){ // root between a and midpoint
			return findZeroBetween(a, m, precision);
		} else{ // root between m and b
			return findZeroBetween(m, b, precision);
		}
	}
}
