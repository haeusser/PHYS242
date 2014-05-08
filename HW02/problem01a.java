package HW02;

public class problem01a {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-1a-out.txt");
		
		// get exact value
		double ln2 = Math.log(2.); // public static double log(double a)
		
		// print header
		System.out.printf("#h\t\t\tT_n\t\t\t\t|T_n - ln(2)|/h^2\n");
		
		// do some steps
		for(int n=2; n<Math.pow(2, 16); n*=2){
			double T=trapezium(n,0,1);
			double h=(double)1./n;
			double error = Math.abs(T-ln2)/Math.pow(h, 2);
			
			System.out.printf("%.3e\t\t%.13f\t%.3e\n",h,T,error);
		}
	}
	
	/**
	 * Definition of the function under the integral evaluated below
	 * @param x parameter of the function
	 * @return the function evaluated at x
	 */
	private static double f(double x){
		return 1/(1+x);
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
}
