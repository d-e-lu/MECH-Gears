import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TextReader {
	public static void readFile(String file) throws FileNotFoundException, IOException
	{
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       String[] powerScrews = line.split("\\s");
		       if(!powerScrews[0].endsWith("\""))
		       {
		    	   System.out.print(powerScrews[0] + " " + powerScrews[1]);
		    	   System.out.print(" " + powerScrews[3] + " " + powerScrews[4]);
		    	   System.out.println(" " + powerScrews[powerScrews.length - 1]);
		       }
		       else{
		    	   System.out.print(powerScrews[0]);
		    	   System.out.print(" " + powerScrews[2] + " " + powerScrews[3]);
		    	   System.out.println(" " + powerScrews[powerScrews.length - 1]);
		       }
		       
		      
		    }
		}
	}
}
