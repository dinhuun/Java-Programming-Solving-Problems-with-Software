/**
 * Finds the first protein starting with atg and ending with tag within each file from directory
 * @DinhHuuNguyen
 * @01/04/2016
 */
import edu.duke.*;
import java.io.*;
import java.io.File;

public class tagFinder2 {
    
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
    
    public void testfindProtein() {
        DirectoryResource dr = new DirectoryResource();
        for (File file : dr.selectedFiles()) {
            String filename = file.getName();
            FileResource fr = new FileResource(file);
            String dna = fr.asString();
            String string = dna.toLowerCase();
            String result = findProtein(string);            
            System.out.println("read " + dna.length() + " characters in " + filename);
            System.out.println("found " + result);
        }
    }
    
}
