package MD;

public class MDFunctions {
	
	/**
	 * Function to initialize the N-atomic chain with sinusoidal start values
	 * for the velocities of each atom.
	 * @param l 	atoms equilibrium position
	 * 				(coincides with index (non-zero indexed array))
	 * @param delta	a parameter between 0,1 excluding 1/2 to make it asymmetric
	 * @param N		number of total atoms
	 * @return		returns the initial velocity of the l-th atom
	 */
	public static double vInitial(int l, double delta, int N){
		return Math.sin(2*Math.PI*(l+delta)/N);
	}
	
	/**
	 * Force function assuming anharmonic potential
	 * @param t		the parameter (usually differences of y(l)s)
	 * @return		the force (i.e. the second time derivative since m=1)
	 */
	public static double f(double t){
		return -(t);//+ t*t + t*t*t);
	}
	
	/**
	 * Implementation of the position Verlet algorithm. Credits: assignment sheet
	 * This function does not return a value but changes the passed params x,v
	 * @param N		total number of particles
	 * @param h		step size (time)
	 * @param x		array in which the positions of the N atoms are stored
	 * @param v		array in which the velocities of the N atoms are stored
	 */
	public static void posVerlet(int N, double h, double[] x, double[] v){
		
		// half step in x
		for(int i=0; i<N; i++) x[i] += 0.5*h*v[i];
		
		// full step in v (treat bounds separately)
		v[0] += h*(f(y(x,0)-y(x,1)) - f(y(x,N-1)-y(x,0))); //y(x,i) = displacem.
		for(int i=1; i<N-1; i++){
			v[i] += h*(f(y(x,i)-y(x,i+1)) - f(y(x,i-1)-y(x,i)));
		}
		v[N-1] += h*(f(y(x,N-1)-y(x,0)) - f(y(x,N-2)-y(x,N-1)));
		
		// 2nd half step in x
		for(int i=0; i<N; i++) x[i] += 0.5*h*v[i];
	}
	
	/**
	 * Get displacement for a given atom
	 * @param x		array in which the positions of the N atoms are stored
	 * @param i		index of the atom (0,1,2,3,...,N-1)
	 * @return		displacement
	 */
	public static double y(double[] x, int i){
		return x[i]-i;
	}
	
	/**
	 * Function to print x and v values tab-separated
	 * @param x	vector of x values
	 * @param v	vector of y values
	 */
	public static void printXnV(double[] x, double[] v){
		for(int i=0; i<x.length; i++){
			System.out.printf("\t%.12f\t%.12f", x[i], v[i]);
		}
		System.out.println(); // add a new line symbol
	}
	
	/**
	 * Function to fill the histogram array with data
	 * @param v			array with velocities
	 * @param hist		array which velocities are sorted in
	 * @param binSize	bin size for histogram
	 */
	public static void fillHist(double[] v, double[] hist, double binSize){
		for(int i=0; i<v.length; i++){
			hist[vToI(v[i], binSize, hist.length)]++;
		}
	}
	
	/**
	 * Converts a velocity value to the index of a histogram array
	 * @param v				velocity
	 * @param binSize		bin size of histogram
	 * @param histLength	total size of histogram
	 * @return index of histogram array which v should be sorted into
	 */
	public static int vToI(double v, double binSize, int histLength){
		return (int)(v/binSize + histLength/2);
		
	}
	
	/**
	 * Converts a histogram array index to the corresponding average velocity
	 * @param i				index of the histogram
	 * @param binSize		bin size of the histogram
	 * @param histLength	total size of the histogram
	 * @return
	 */
	public static double iToV(int i, double binSize, int histLength){
		return binSize*(i-histLength/2 + 0.5); // 0.5 --> linear interpolation
	}
	
	/**
	 * Normalizes a given histogram array
	 * @param hist	histogram to be normalized
	 */
	public static void normalizeHisto(double[] hist){
		double sum = 0;	// here we will sum up all entries of the histogram
		
		for(int i=0; i<hist.length; i++){ // sum up
			sum += hist[i];
		}
		
		for(int i=0; i<hist.length; i++){ // divide each bin by sum
			hist[i] /= sum;
		}
	}
	
	/**
	 * Function to print a histogram array in the form of a tab-separated table
	 * @param hist		histogram array
	 * @param binSize	bin size
	 */
	public static void printHisto(double[] hist, double binSize){
		System.out.printf("#v\tN\n"); // print a header
		
		for(int i=0; i<hist.length; i++){ // print each value and its number
			double v = iToV(i, binSize, hist.length);
			System.out.printf("%.3f\t%.3f\n", v, hist[i]);
		}
	}
}
