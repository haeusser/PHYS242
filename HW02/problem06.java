package HW02;

public class problem06 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-6-out.txt");
		
		// integral bounds
		double a=0., b=1.;
		
		// desired accuracy
		double accuracy = 1e-4;

		// print header
		System.out.printf("#n \t I(n) \t\t |I(n)-I(n-1)| \n");
		
		// evaluate midpoint rule
		// keep track of progress
		int n = 1;
		double In = 2.*midpoint(n, a, b), InPlus1;
		
		double difference;

		System.out.printf("%d \t %.6f \n",  n, In );
		
		do{
			n*=2;
			InPlus1 = 2.*midpoint(n, a, b);
			difference = Math.abs(InPlus1-In);
			System.out.printf("%d \t %.6f \t %.6f \n",  n, In, difference );
			In = InPlus1;
			
		}while(difference > accuracy);
		
		System.out.printf("\nThe final value is %.4f with an accuracy of %1.0e",
							InPlus1, difference);
		
	}
	
	/**
	 * Definition of the function under the integral evaluated below
	 * @param x integration variable
	 * @return the function evaluated at x
	 */
	private static double f(double x){
		return Math.sin(Math.pow(x, 2))/Math.pow(x, 2);
	}
	
	/**
	 * Returns an estimate for the midpoint rule integral for the function
	 * f(x) defined above.
	 * 
	 * @param n number of intervals
	 * @param a lower integration endpoint
	 * @param b upper integration endpoint
	 * @return the midpoint integral
	 */
	private static double midpoint(int n, double a, double b){
		double h = (b-a)/n;
		double sum = 0;
		double x=a+0.5*h;
		
		for(int i=0; i<n; i++){
			sum += f(x);
			x += h;
		}
		return sum*h;
	}
}
