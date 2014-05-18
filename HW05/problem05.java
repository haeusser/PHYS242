package HW05;

public class problem05 {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW05-5-out.txt");
		
		// params
		int tMin = 0,	// minimum number of steps
			tMax=1000,	// maximum number of steps
			dt=10,		// increment in steps
			N=100000;	// trials per t
		
		// print header
		System.out.println("t\t<x(t)>\t<x^2(t)>");
		
		double avgX=0; // for temporal average over sample average
		
		// try different "walk times"
		for(int t=tMin; t<tMax+1; t+=dt){
			double x=0,
				xSqu=0;
			// try the same walk time N times
			for(int n=0; n<N; n++){
				double xTmp = randomWalk(t);
				x += xTmp;
				xSqu += xTmp*xTmp;
			}
			x /= N; // <x> (avg over samples at this time)
			xSqu /= N; // <x^2> (avg over samples at this time)
			System.out.printf("%d\t%.6f\t%.6f\n", t, x, xSqu);
			avgX += x; // <<x>> avg over time and over samples
		}
		avgX /= (tMax-tMin); // normalize <<x>>
		
		// print <<x>> (with preceding # so that my plotting program doesn't get confused)
		System.out.printf("# <<x(t)>> = %.6f", avgX);
	}

	/**
	 * Do one random step, either +1 or -1
	 */
	public static int randomStep(){
		return Math.random()>0.5? 1 : -1;
	}
	
	/**
	 * Do a random walk starting at time=0 up to time=t
	 * @param t
	 * @return position after t
	 */
	public static int randomWalk(int t){
		int x=0;
		for(int i=0; i<t; i++) x += randomStep();
		return x;
	}
}
