/**
 * For a given number, determines whether or not it is prime.
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;
public class primeFinder {
    
	public boolean isPrime(int num) {
		int div = 2;
		if (num == 2) {
			return true;
		}
		while (true) {
			if (num % div == 0) {
				return false;
			}
			div += 1;
			if (div > Math.sqrt(num)) {
				break;
			}
		}
		return true;
	}
	
	public void testPrimes() {
		RangeResource rr = new RangeResource(2, 200);
		for (int num : rr.sequence()){
			if (isPrime(num)) {
				System.out.println(num + "\t is prime");
			}
		}
	}
	
}
