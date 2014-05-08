package HW02;

public class problem05d {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-5d-out.txt");
		
		// print header
		System.out.printf("#t_m\tTrapezium\tSeries\t\t|T-S|/T\n");
		
		// get theta values ready (numerical and as String for printing)
		double[] theta = {0.1, 0.2, Math.PI/4, Math.PI/2, 3*Math.PI/4};
		String[] thetaC = {"0.1", "0.2", "PI/4", "PI/2", "3PI/4"};
		
		// integral bounds
		double a=0., b=Math.PI/2;
		
		// do some steps
		for(int i=0; i<theta.length; i++){
			double tm = theta[i];
			double T = 2/Math.PI*trapezium(100,a,b,tm);
			double S = seriesEval(tm);
			System.out.printf("%s\t%.13f\t%.13f\t%.3e\n",thetaC[i],T,S,Math.abs(T-S)/T);
		}
	}
	
	/**
	 * Definition of the function under the integral evaluated below
	 * @param psi integration variable
	 * @param tm theta_m
	 * @return the function evaluated at x
	 */
	private static double f(double psi, double tm){
		return 1./Math.sqrt(1-Math.pow(Math.sin(tm/2)*Math.sin(psi),2));
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
	private static double trapezium(int n, double a, double b, double tm){
		double h = (b-a)/n;
		double sum = (f(a,tm)+f(b,tm))/2;
		double x=a;
		
		for(int i=1; i<n; i++){
			x += h;
			sum += f(x,tm);
		}
		
		return sum*h;
	}
	
	/**
	 * Integral evaluation via series expansion
	 * @param t theta_m
	 */
	private static double seriesEval(double tm){
		double a = 3./16, b = 105./1024;
		return 1.+a*Math.pow(Math.sin(tm/2),2)+b*Math.pow(Math.sin(tm/2),4);
	}
}
