package HW04;

public class problem01 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW04-1-out.txt");
		
		// define parameters
		double precision = 1e-5,						// 5 digits precision
			   currentPrecision = Integer.MAX_VALUE,	// bookkeeping
			   lowerBound = 0,							// lower estimate for a
			   upperBound = 10;							// upper estimate for a
	    int n = 10;										// # trapezium intervals
	    
	    // print header
	    System.out.println("#n\ta\n-----------");
		
	    // calcluate first a
		double a = findZeroBetween(lowerBound, upperBound, precision, n);
		System.out.printf("%d\t%.5f\n", n,a); // print n and a
		
		// increase n until desired precision reached
		while(currentPrecision > precision){
			// double n
			n *= 2;
			
			// calculate the next a
			double aNew = findZeroBetween(lowerBound, upperBound, precision, n);
			
			// precision bookkeeping
			currentPrecision = Math.abs(a-aNew);
			a = aNew;
			
			// print intermediate result
			System.out.printf("%d\t%.5f\n", n,a);
		}
	}

	/**
	 * Definition of the function under the integral evaluated below
	 * @param x parameter of the function
	 * @return the function evaluated at x
	 */
	private static double f(double x){
		return Math.exp(-x*x/2);
	}
	
	/**
	 * Declaration of the function we want to feed into the root finding algo
	 * @param n	number of intervals (passed through)
	 * @param x	plus/minus integration bound
	 * @return
	 */
	private static double rootFn(int n, double x){
		return trapezium(n, -x, x)-Math.sqrt(2*Math.PI)/2;
	}
	
	/**
	 * Returns an estimate for the trapezium rule integral for the function
	 * f(x) defined above.
	 * 
	 * @param n number of intervals
	 * @param a lower integration endpoint
	 * @param b upper integration endpoint
	 * @return the trapezium integral
	 */
	private static double trapezium(int n, double a, double b){
		double h = (b-a)/n;
		double sum = (f(a)+f(b))/2;
		double x=a;
		
		for(int i=1; i<n; i++){
			x += h;
			sum += f(x);
		}
		return sum*h;
	}
	
	/**
	 * Declaration of midpoint rule method
	 * 
	 * @param a lower root estimate
	 * @param b upper root estimate
	 * @param precision desired precision, e.g. 1e-4
	 * @return midpoint rule estimate for root between a and b
	 */
	public static double findZeroBetween(double a, double b, double precision, int n){
		
		// check if desired precision achieved
		if(Math.abs(b-a)<precision) return (a+b)/2;
		
		// midpoint
		double m = (a+b)/2.;
		
		// check if the root is between a and m or between m and b
		if(Math.signum(rootFn(n, a)) != Math.signum(rootFn(n, m))){ 
			return findZeroBetween(a, m, precision, n);
		} else{ // root between m and b
			return findZeroBetween(m, b, precision, n);
		}
	}
}
