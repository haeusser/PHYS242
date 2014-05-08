package HW03;

public class problem07 {

	public static void main(String[] args) {
		
		// initialize printing output to file
		philIO.tools.initializeIO("HW03-7-out.txt");
		
		// initialize parameters
		double x0 = 0,			// start value for x (from boundary condition)
			   xn = Math.PI/4,	// target value (where y shall be evaluated)
			   y0 = 0;			// start value for y (from boundary condition)
		
		// error evaluation: error = | rk4(PI/4) - 1 | / 1 (cf. problem 5, (a))
		
		// Print header
		System.out.printf("#n\th\t\trk4(PI/4)\t|rk4(PI/4)-1|\n");
		
		// evaluate rk4 with (exponentially) increasing stepsize 
		for(int n=2; n<Math.pow(2,12); n*=2){
			double value = rk4(x0, xn, y0, n);
			System.out.printf("%d\t%.3e\t%.13f\t%.4e\n", n, (xn-x0)/n, value, 
															Math.abs(value-1));
		}
	}
	
	// differential equation "function"
	private static double dydx(double y){
		return 1.+Math.pow(y,2);
	}
	
	/**
	 * Implementation of Runge-Kutta 4
	 * @param x0	start value for x (from boundary condition)
	 * @param xn	target value (where y shall be evaluated)
	 * @param y		start value for y (from boundary condition)		
	 * @param n		number of steps
	 * @return		y(xn) evaluated via rk4
	 */
	private static double rk4(double x0, double xn, double y, int n){
		
		// determine step size
		double h = (xn-x0)/n;
		
		// evaluate the o.d.e. at each step
		for(int i=0; i<n; i++){
			double k1 = dydx(y);
			double k2 = dydx(y+k1*h/2);
			double k3 = dydx(y+k2*h/2);
			double k4 = dydx(y+k3*h);
			y += h*(k1+2*k2+2*k3+k4)/6;	
		}
		
		return y;
	}
}
