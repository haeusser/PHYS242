package HW02;

public class problem03 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-3-out.txt");
		
		// call the romberg function and print the final result
		System.out.printf("The final result is %.12f",romberg(2,0,1,1e-12));
	}
		
	/**
	 * Calculates the integral of f(x) (specified below) from a to b with
	 * starting value k. If the desired precision could not be achieved, double
	 * k and try again.
	 * @param k starting value
	 * @param a lower integration endpoint
	 * @param b upper integration endpoint
	 * @param precision
	 * @return prints the n-k-table and returns a double representing the integral
	 */
	private static double romberg(int k, double a, double b, double precision){

			int       // n: running index will be i, representing n = 2^i 
				n=k; //  k: running index will be j

			// in this array we are going to store the calculated components
			double[][] I = new double[n][k];
			
			// start by filling the first column with the trapezium rule
			for(int i=0; i<n; i++){
				I[i][0] = trapezium((int)Math.pow(2, i), a, b);
			}
			
			// fill the second column with Simpson's rule
			for(int i=1; i<n; i++){
				I[i][1] = simpson(a, b, (int)Math.pow(2, i));
			}
			
			// now go through the other columns and fill them with the "Romberg rule"
			for(int j=2; j<k; j++){
				for(int i=j; i<n; i++){
					I[i][j] = (Math.pow(4, j)*I[i][j-1]-I[i-1][j-1])/(Math.pow(4, j)-1);
				}
			}
			
			// check if desired precision is achieved
			if(Math.abs(I[n-1][n-1]-I[n-2][n-2]) < precision){
				printTable(I);
				return I[n-1][n-1];
			} // if not, try again with n doubled
			else return romberg(n*n,a,b,precision);
	}


	private static double f(double x){
		return Math.exp(-Math.pow(x, 2)/2);
	}
	
	private static double simpson(double a, double b, int n)
			throws RuntimeException{
		
		// first check if n is even
		if(n%2!=0){throw new RuntimeException("Only even n allowed");}
		
		double h   =(b-a)/n,
			   sum =f(a)+f(b);
		
		for(int i=1; i<n; i+=2){
			sum += 4*f(a+i*h);
		}
		
		for(int i=2; i<n-1; i+=2){
			sum += 2*f(a+i*h);
		}
		return sum*h/3;
	}
	
	/** Returns an estimate for the trapezium rule integral for the function
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
	
	private static void printTable(double[][] I){
		for(int i=0; i<I.length; i++){
			for(int j=0; j<I.length; j++){
				if(I[i][j] != 0.){
					System.out.printf("%.13f\t", I[i][j]);
				} else System.out.printf("               \t");
				
			}
			System.out.println();
		}
		System.out.println();
	}
}
