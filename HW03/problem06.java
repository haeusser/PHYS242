package HW03;

public class problem06 {

	public static void main(String[] args) {
		
		// initialize printing output to file
		philIO.tools.initializeIO("HW03-6-out.txt");
		
		// initialize parameters
		double t0 = 0,
			   p0 = 0,
			   precision = 1e-7;
		double[] x0 = {0.1, 1., 10.}; // start values for x (amplitudes)
		
		for(int i=0; i<x0.length; i++){
			System.out.printf("x0 = %.1f \t",x0[i]);
			System.out.printf("T = %.3e\n", 4*rk2zero(t0, x0[i], p0, precision));
		}
	}
	
	// differential equation "function"
	private static double dpdt(double x){
		return -Math.pow(x, 3);
	}
	
	/**
	 * Find first zero right of the start value employing RK2
	 * @param t0	start value for the time
	 * @param x0	start value for x, i.e. x0 = x(t0)
	 * @param p0	start value for momentum
	 * @param dt	time interval
	 * @return
	 */
	private static double rk2zero(double t0, double x0, double p0, double dt){
		
		// start at x = x0
		double x = x0,
			   p = p0,
			   t = t0;
		
		// step through RK2 until sign(x) changes
		do{
			// first take care of p
			double k1p = dpdt(x);
			double k2p = dpdt(x+k1p*dt);
			double pNew = p+0.5*dt*(k1p+k2p);
			
			// now update x
			double k1x = p;
			double k2x = pNew;
			x += 0.5*dt*(k1x+k2x);
			p = pNew;
			t += dt;
			
			// print out x(t) for a later plot
			// System.out.printf("%.3e\t%.3e\n", t,x);
		}while(Math.signum(x) == Math.signum(x0));
		// now sign(x(t)) != sign(x(t+h)
		
		// interpolate linearly between the last two values
		return t - 0.5*dt;
	}
}