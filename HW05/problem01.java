package HW05;

public class problem01 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW05-1-out.txt");
		
		// params
		int N = 12,
			Nsamples = 10000;
		
		// here I store my samples
		double[] samples = new double[Nsamples];
		
		// generate samples and store them
		for(int i=0; i<Nsamples; i++){
			samples[i] = generateX(N);
		}
		
		// calculate moments 1-6
		for(int n=1;n<7; n++){
			double moment = calcMoment(n, samples);
			System.out.printf("<x^%d> =\t%.6f\n", n,moment);
		}
		
		// for plotting reasons, print the random numbers, too
		for(int i=0; i<Nsamples; i++){
			System.out.printf("%.6f\n", samples[i]);
		}
	}
	
	/**
	 * Generates a value for X = sqrt(12/N) sum(i=1,N) x_i - 1/2
	 */
	private static double generateX(int N){
		// initialize value
		double x = 0;
		
		// sum
		for(int i=0; i<N; i++){
			x += Math.random()-0.5;
		}
		
		return x*Math.sqrt(12./N);
	}
	
	/**
	 * Calculates the n-th moment of the samples in array a
	 */
	private static double calcMoment(int n, double[] a){

		// initialize sum
		double sum = 0;
		int N = a.length;
		
		// add elements of a, raised to n-th power
		for(int i=0; i<N; i++){
			sum += Math.pow(a[i], n);
		}
		
		// return average
		return sum/N;
	}
}
