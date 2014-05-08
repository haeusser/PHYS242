package HW02;

public class problem07b {

	public static void main(String[] args) {
		
		// initilize printing output to file
		philIO.tools.initializeIO("HW02-7b-out.txt");		
		
		System.out.printf("#N \tI\n");
		
		for(int N=4; N<=4; N++){
			System.out.printf("%d\t", N);
			System.out.printf("%.12f\t", hermiteEval(N));
			System.out.printf("\n");
		}
	}

	/**
	 * returns x^n
	 */
	private static double f(double x){
		return 1./(1+Math.exp(x));
	}
	
	private static double hermiteEval(int N){
		double[][] x = new double[3][4];
		double[][] w = new double[3][4];
		
		x[0][0]= 0.7071068;
		x[0][1]=-0.7071068;
		
		x[1][0]=1.2247449 ;
		x[1][1]=0.0000000;
		x[1][2]=-1.2247449 ;
		
		x[2][0]=1.6506801 ;
		x[2][1]=0.5246476 ;
		x[2][2]=-0.5246476;
		x[2][3]=-1.6506801;
		
		w[0][0]=0.8862269;
		w[0][1]=0.8862269;
		
		w[1][0]=0.2954090;
		w[1][1]=1.1816359;
		w[1][2]=0.2954090;
		
		w[2][0]=0.0813128;
		w[2][1]=0.8049141;
		w[2][2]=0.8049141;
		w[2][3]=0.0813128;
		
		double result=0;
		
		for(int i=1; i<=N; i++){
			result += w[N-2][i-1]*f(x[N-2][i-1]);
		}
		return result;
	}
}
