/**
 * MONTE CARLO PROJECT - Main Class
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 */

package MC;

public class MCMain {

	public static void main(String[] args) {
		
		philIO.tools.initializeIO("MC-8-test2.txt");
		
		// parameters
		double T=0,
				dT = 0.1,
				Tmax=4;
		int n = 20;
		int L = 8;
		// /*
		while(T <= Tmax){
			Lattice l;
			
			double[] measurements = new double[n];
			
			for(int i=0;i<n;i++){
				l=new Lattice(L,T);
				
				double m = l.measureMperSpin(1000);
				measurements[i] = m*m;
			}
			
			double m2 = getAv(measurements),
					error = getStdDev(m2, measurements);
			
			System.out.printf("%.3f\t%.3f\t%.3e\n", T, m2, error);
			T += dT;
		}
		
		// */
		// ---------
		/*
		philIO.tools.initializeIO("MC-8-Tc.txt");
		T=Lattice.Tc-5./L;
		Tmax=Lattice.Tc+5./L;
		dT=0.01;
		
		while(T <= Tmax){
			Lattice l;
			
			double[] measurements = new double[n];
			
			for(int i=0;i<n;i++){
				l=new Lattice(L,T);
				
				measurements[i] = l.measureM2(1000);
			}
			
			double m2 = getAv(measurements),
					error = getStdDev(m2, measurements);
			
			System.out.printf("%.3f\t%.3f\t%.3e\n", T, m2, error);
			T += dT;
		}
		*/
		
	}
	
	private static double getAv(double[] a){
		double r=0;
		for(double x:a){
			r += x;
		}
		return r/a.length;
	}
	
	private static double getStdDev(double mean, double[]a){
		double r=0;
		for(double x:a){
			r += (x-mean)*(x-mean);
		}
		return Math.sqrt(r/a.length);
	}
	
	

}
