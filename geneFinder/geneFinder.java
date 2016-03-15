/**
 * Find first gene between start codon atg and any stop codon tag, tga, taa in a particular DNA strand
 * @DinhHuuNguyen
 * @01/06/2016
 */
import edu.duke.*;
import java.io.*;
public class geneFinder {
    
    public String findFirstGene(String dna) {
        String string = dna.toLowerCase();
        int start = string.indexOf("atg");
        int stopTAG = string.indexOf("tag", start + 3);
        int stopTGA = string.indexOf("tga", start + 3);
        int stopTAA = string.indexOf("taa", start + 3);
        if (start == -1) {
            return "";
        }
        else if ((stopTAG - start) % 3 == 0) {
            return string.substring(start, stopTAG + 3);
        }
        else if ((stopTGA - start) % 3 == 0){
            return string.substring(start, stopTGA + 3);
          }
        else if ((stopTAA - start) % 3 == 0) {
            return string.substring(start, stopTAA + 3);
        }
        else {return "";}
          }
          
    public String stopCodon(String gene)  {
        if (gene.length() > 6) {return gene.substring(gene.length() - 3);}
        else {return "";} 
    }
    
	public void testgeneFinder(String dna) {
		System.out.println(findFirstGene(dna) + "\t" + stopCodon(findFirstGene(dna)));
	}
	
}
