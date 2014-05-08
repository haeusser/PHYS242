package HW02;

public class problem02 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-2-out.txt");
		
		double precision = 1e-8; // desired precision
		int maxIteration = 500; // define maximum number of iterations
		int iteration = 1; // count number of iterations
		int n = 2; // begin with two intervals
		double Sn = simpson(0,2,n); // first evaluation
		double SnPlus1; // we need 2 variables to keep track of the difference
		double diff; // difference between the result of 2 consecutive iterations
		
		// print header
		System.out.printf("#n \t h \t\t\t S_n \t\t\t |S(n) - S(n-2)|/h^4\n");
		
		// first iteration
		System.out.printf("%d \t %.3e \t %.13f \t \n", n, (double)2./n, Sn);
		
		do{
			n *= 2; // double n in each step
			double h = 2./n; // step size of integration
			SnPlus1 = simpson(0,2,n); // evaluate integral
			diff = SnPlus1-Sn; // calculate difference
			Sn = SnPlus1; // update Sn
			System.out.printf("%d \t %.3e \t %.13f \t %.3e\n",
								n, h, Sn, diff/Math.pow(h, 4));
			iteration++; // update number of iterations
		}while(diff>precision || iteration>maxIteration);
		// stops when desired precision has been reached or maxIteration exceeded
		
		// print last iteration
		n *= 2; // double n in each step
		double h = 2./n; // step size of integration
		SnPlus1 = simpson(0,2,n); // evaluate integral
		diff = SnPlus1-Sn; // calculate difference
		Sn = SnPlus1; // update Sn
		System.out.printf("%d \t %.3e \t %.13f \t %.3e\n",
							n, h, Sn, diff/Math.pow(h, 4));
		iteration++; // update number of iterations
		
		if(diff<precision){
			System.out.printf("\nThe final result with precision " +
					"%.1e is S_%d = %.13f\n", precision, iteration, SnPlus1);
		}else{
			System.out.printf("\nMaximum number of iterations reached.\n");
		}		
	}
	
	private static double f(double x){
		return Math.exp(-x)*Math.sin(x);
	}
	
	private static double simpson(double a, double b, int n)
			throws RuntimeException{
		
		// first check if n is even
		if(n%2!=0){throw new RuntimeException("Only even n allowed");}
		
		double h=(b-a)/n;
		
		double sum=f(a)+f(b);
		
		for(int i=1; i<n; i+=2){
			sum += 4*f(a+i*h);
		}
		
		for(int i=2; i<n-1; i+=2){
			sum += 2*f(a+i*h);
		}
		return sum*h/3;
	}
}
