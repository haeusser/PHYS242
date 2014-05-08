/**
 * TITLE OF CLASS
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * DESCRIPTION
 *
 * @param  param 	description
 * @return      	description
 */

package MD;
import org.apache.commons.math3.complex.*;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Complex z = new Complex(0,Math.PI);
		z = z.exp();
		System.out.print(z.toString());
		
		/*
		double[] x = {0,10,20,10,0,-10,-20,-10,0,10};
		int m = 1, N=10;
		System.out.println(MDFunctionsFPU.squaredFourierComp(m, N, x));
		*/
	}

}
