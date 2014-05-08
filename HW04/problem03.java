package HW04;

public class problem03 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW04-3d-ellip-out.txt");
		
		// constants and parameters
		double   T = 2*Math.PI,
			     h = 1e-5,
	    		 steps = T/h;
		
		// position and velocity arrays
		double[] x = {1,0},
				 v = {0,0.7}; // {0,1} for part (b)
		
		// print header
		System.out.println("t\tx\ty\tvx\tvy\tfx\tfy");
		
		// print initialized values
		System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f\n", 
				h*0, x[0], x[1], v[0], v[1], f(x)[0], f(x)[1]);
		
		// do iteration
		for(int i=0; i<=steps; i++){
			posVerlet(h, x, v);
			System.out.printf("%.3f\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f\t%.3f\n", 
					h*i, x[0], x[1], v[0], v[1], f(x)[0], f(x)[1]);
		}
	}

	/**
	 * Force function (derived from V(r), cf. homework documentation)
	 * @param t		the parameter (usually differences of y(l)s)
	 * @return		the force (i.e. the second time derivative since m=1)
	 */
	public static double[] f(double[] x){
		double   fAbs  = -1/(x[0]*x[0]+ x[1]*x[1]),
		         theta = Math.atan2(x[1],x[0]), // -pi/2, +pi/2 
		         fX    = fAbs*Math.cos(theta),
		         fY    = fAbs*Math.sin(theta);
		double[] f     = {fX, fY};
		return f;
	}
	
	/**
	 * Implementation of the position Verlet algorithm.
	 * Credits: Molecular Dynamics Project assignment sheet
	 * This function does not return a value but changes the passed params x,v
	 * @param h		step size (time)
	 * @param x		position array
	 * @param v		velocity array
	 */
	public static void posVerlet(double h, double[] x, double[] v){
		
		// half step in x
		for(int i=0; i<x.length; i++) x[i] += 0.5*h*v[i];
		
		// full step in v
		for(int i=0; i<x.length; i++){
			v[i] += h*f(x)[i];
		}
		
		// 2nd half step in x
		for(int i=0; i<x.length; i++) x[i] += 0.5*h*v[i];
	}
}
