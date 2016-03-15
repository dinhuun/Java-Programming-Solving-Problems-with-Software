/**
 * Finds a particular protein starting with atg and ending with tag of length divisible by 3 in a particular DNA strand
 * @DinhHuuNguyen
 * 01/04/2016
 */
import edu.duke.*;
import java.io.*;

public class tagFinder1 {
    
    public String findProtein(String string) {
        int start = string.indexOf("atg");
        if (start == -1) {
            return "";
        }
        int stop = string.indexOf("tag", start+3);
        if ((stop - start) % 3 == 0) {
            return string.substring(start, stop+3);
        }
        else {
            return "";
        }
    }
    
    public void testfindProtein(String dna) {
        String string = dna.toLowerCase();
        String protein = "atggggtttaaataataatag";
        String result = findProtein(string);
        if (protein.equals(result)) {
            System.out.println("found " + protein + " of length " + protein.length() + " in " + dna);
        }
        else {
            System.out.println("did not find " + protein);
            System.out.println("found instead " + result);
        }
    }
    
}
