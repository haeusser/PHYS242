package MD;

public class MDFunctionsFPU extends MDFunctions{

	/**
	 * Get squared fourier components according to eqn (13)
	 * @param m	number of component / mode
	 * @param N	number of particles
	 * @param x	velocity or displacement array
	 * @return	|a_m|^2
	 */
	public static double squaredFourierComp(int m, int N, double[]x){
		
		// store the complex number in this mini array
		double[] complexSum = new double[2]; //[0,1] = Re,Im part respectively
		
		// create the sum
		for(int l=1; l<=N; l++){
			complexSum[0] += x[l-1]*Math.cos(2*Math.PI*m*l/N);
			complexSum[1] += x[l-1]*Math.sin(2*Math.PI*m*l/N);
		}
		return (Math.pow(complexSum[0],2)+Math.pow(complexSum[1], 2))/N;
	}
	
	/**
	 * Calculates the factor used for the harmonic part of the potential energy
	 * @param m	number of mode
	 * @param N	total number of particles
	 * @return omega_m
	 */
	public static double omegaM(int m, int N){
		return 2*Math.sin(m*Math.PI/N);
	}
	
	/**
	 * Calculates the energy stored in the m-th mode according to eqn (14)
	 * @param m				mode number
	 * @param aDotSquared	m-th Fourier component of the velocity
	 * @param omega			omega_m
	 * @param aSquared		m-th Fourier component of the displacement
	 * @return				E_m
	 */
	public static double energyM(int m, double aDotSquared,
												double omega, double aSquared){
		return 0.5*(aDotSquared+Math.pow(omega, 2)*aSquared);
	}
	
	/**
	 * Implementation of the position Verlet algorithm. Credits: assignment sheet
	 * This function does not return a value but changes the passed params x,v
	 * @param N		total number of particles
	 * @param h		step size (time)
	 * @param x		array in which the positions of the N atoms are stored
	 * @param v		array in which the velocities of the N atoms are stored
	 */
	public static void posVerlet(int N, double h, double[] y, double[] v){
		
		// half step in x
		for(int i=0; i<N; i++) y[i] += 0.5*h*v[i];
		
		// full step in v (treat bounds separately)
		v[0] += h*(f(y[0]-y[1]) - f(y[N-1]-y[0])); //y(x,i) = displacem.
		for(int i=1; i<N-1; i++){
			v[i] += h*(f(y[i]-y[i+1]) - f(y[i-1]-y[i]));
		}
		v[N-1] += h*(f(y[N-1]-y[0]) - f(y[N-2]-y[N-1]));
		
		// 2nd half step in x
		for(int i=0; i<N; i++) y[i] += 0.5*h*v[i];
	}
}