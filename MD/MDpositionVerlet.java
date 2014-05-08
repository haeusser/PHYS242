package MD;

public class MDpositionVerlet {
	
	/**
	 * Implementation of the position Verlet algorithm
	 * @param N	total number of atoms
	 * @param h	temporal step size
	 * @param x	array with position of each atom
	 * @param v	array with velocity of each atom
	 */
	public static void posVerlet(int N, double h, double[] x, double[] v){
		
		// half step in x
		for(int i=0; i<N; i++) x[i] += 0.5*h*v[i];
		
		// full step in v
		v[0] += h*(MDFunctions.f(x[0]-x[1]) - MDFunctions.f(x[N-1]-x[0]));
		for(int i=1; i<N-1; i++){
			v[i] += h*(MDFunctions.f(x[i]-x[i+1]) - MDFunctions.f(x[i-1]-x[i]));
		}
		v[N-1] += h*(MDFunctions.f(x[N-1]-x[0]) - MDFunctions.f(x[N-2]-x[N-1]));
		
		// 2nd half step in x
		for(int i=0; i<N; i++) x[i] += 0.5*h*v[i];
	}
}