/**
 * Print all words at some URL 
 * @author Duke Software Team 
 */
import edu.duke.*;

public class HelloWorld {
    
	public void runHello () {
		URLResource webpage = new URLResource("http://www.dukelearntoprogram.com/java/somefile.txt");
		for (String word : webpage.words()) {
			System.out.println(word);
		}
	}
	
}
