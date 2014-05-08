/**
 * File I/O helper class
 * 
 * @author	Philip Haeusser
 * 			dev@PH1L.tv
 * 			http://dev.PH1L.tv
 * 
 * Provides functions for file reading / writing
 */

package philIO;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class tools {
	
	/**
	 * Creates file output interface. All printed lines arrive in this file
	 */
	public static void initializeIO(String filename){
		// set locale to english to avoid point/comma problems
		Locale.setDefault(Locale.ENGLISH);

		// create file output stream
		try{
			PrintStream out = new PrintStream(new FileOutputStream(filename));
			System.setOut(out);
		}catch(Exception e){
			System.out.print("Problem with writing to file."); // catch I/O error
		}
	}
	
	/**
	 * Reads a file in the format "(blank) x (blank) y (blank) z (newline)"
	 * @param filename	file to read
	 * @return Double array a[i][j] where i=line number and j=x,y,z resp.
	 */
	public static double[][] readYoungsTxt(String filename){
		
		// for debugging (print reading steps)
		boolean debug = false;
		
		// set double format (point instead of comme (German here ...))
		Locale.setDefault(Locale.ENGLISH);

		// we're going to store the text file into a dynamic array
        List<String> lines = new ArrayList<String>();
        String line = null;

		try{ // open file and read lines
	        FileReader fileReader = new FileReader(filename);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        while ((line = bufferedReader.readLine()) != null) {
	            lines.add(line);
	        }
	        bufferedReader.close();
		}catch(Exception e){
			System.err.println("Error opening file + " + filename);
			return null;
		}
		
		if(debug){
			System.out.println("File read:");
			for(int i=0; i<lines.size(); i++){
				System.out.printf("%s\n", lines.get(i));
			}
		}

		// allocate result
        double[][] result = new double[lines.size()][3];
        
        // go through read lines, parse them and convert them to doubles
        for(int i=0; i<lines.size(); i++){ 	
        	String[] datapoint = lines.get(i).split("\\s+");
        	
        	for(int j=0; j<result[0].length; j++){
        		result[i][j] = Double.parseDouble(datapoint[j+1]);
        		if(debug){
        			System.out.printf("x=%s\ty=%s\terr=%s\n",
        					datapoint[1],datapoint[2],datapoint[3]);
        		}
        	}
        }
        return result;
	}
}
