package HW03;

public class problem05 {

	public static void main(String[] args) {
		
		// initialize printing output to file
		philIO.tools.initializeIO("HW03-5-out.txt");
		
		// initialize parameters
		double x0 = 0,			// start value for x (from boundary condition)
			   xn = Math.PI/4,	// target value (where y shall be evaluated)
			   y0 = 0;			// start value for y (from boundary condition)
		
		// error evaluation: error = | rk(PI/4) - 1 | / 1 (cf. problem 5, (a))
		
		// Print header
		System.out.printf("#n\th\t\trk2(PI/4)\t|rk2(PI/4)-1|\n");
		
		// evaluate RK2 with (exponentially) increasing stepsize 
		for(int n=2; n<Math.pow(2,25); n*=2){
			double value = rk2(x0, xn, y0, n);
			System.out.printf("%d\t%.3e\t%.12f\t%.6e\n", n, (xn-x0)/n, value, 
															Math.abs(value-1));
		}
	}
	
	// differential equation "function"
	private static double dydx(double y){
		return 1.+Math.pow(y,2);
	}
	
	/**
	 * Implementation of Runge-Kutta 2
	 * @param x0	start value for x (from boundary condition)
	 * @param xn	target value (where y shall be evaluated)
	 * @param y		start value for y (from boundary condition)		
	 * @param n		number of steps
	 * @return		y(xn) evaluated via RK2
	 */
	private static double rk2(double x0, double xn, double y, int n){
		
		// determine step size
		double h = (xn-x0)/n;
		
		// evaluate the o.d.e. at each step
		for(int i=0; i<n; i++){
			double k1 = dydx(y);
			double k2 = dydx(y+k1*h);
			y += 0.5*h*(k1+k2);	
		}
		
		return y;
	}
}
