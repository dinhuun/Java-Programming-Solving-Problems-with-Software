/**
 * Find and print all genes from a particular DNA strand
 * Find and store all genes from a particular file
 * Find and print long genes and high CG genes
 * Find number of ctg stops from a particular file
 * @DinhHuuNguyen
 * @01/06/2016
 */
import edu.duke.*;
import java.io.*;
public class genesFinder {
    
   public int FindStopIndex(String string, int index) {
        int stopTGA = string.indexOf("tga", index);
        if (stopTGA == -1 || (stopTGA - index) % 3 != 0) {stopTGA = string.length();}
        int stopTAA = string.indexOf("taa", index);
        if (stopTAA == -1 || (stopTAA - index) % 3 != 0) {stopTAA = string.length();}
        int stopTAG = string.indexOf("tag", index);
        if (stopTAG == -1 || (stopTAG - index) % 3 != 0) {stopTAG = string.length();}
        return Math.min(stopTGA, Math.min(stopTAA, stopTAG));
   }
   
   public StorageResource FindandStoreAllGenes(String dna) {
        StorageResource store = new StorageResource(); 
        String string = dna.toLowerCase();
        int start = 0;
        while (true) {
            start = string.indexOf("atg", start);
            if (start == -1) {break;}
            int stop = FindStopIndex(string, start + 3);
            if (stop != string.length()) {
                store.add(dna.substring(start, stop + 3));
                start = stop + 3;}
            else {start = start + 3;}
        }
        return store;
   }
   
   public double cgRatio(String gene) {
        double ratio = 0;
        for (int i = 0; i < gene.length(); i++) {
        char ch = gene.charAt(i);
            if (ch == 'c' || ch == 'C' || ch == 'g' || ch == 'G') {
            ratio = ratio + 1.0/gene.length();
        }
        }
        return ratio;
   }
   
   public void PrintCertainGenes(StorageResource genes, int length, double ratio) {
        int max = 0;
        int numberoflonggenes = 0;
        int numberofCGgenes = 0;
        for (String gene : genes.data()) {
            if (gene.length() > max) {
                max = gene.length();
            }
            if (gene.length() > length) {
                System.out.println(gene + "\t" + gene.length());
                numberoflonggenes +=1;
            }
            if (cgRatio(gene) > ratio) {
                System.out.println(gene + "\t" + gene.length());
                numberofCGgenes +=1;
            }
        }
        System.out.println(genes.size() + " genes");        
        System.out.println("max length" + "\t" + max);
        System.out.println(numberoflonggenes + " genes are long than " + length);
        System.out.println(numberofCGgenes + " genes have CG ratio higher than " + ratio);
   }
    
   public void testFindandStoreAllGenes() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource genes = FindandStoreAllGenes(dna);
        System.out.println("there are " + genes.size() + " genes");
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
   }
   
   public void testPrintCertainGenes(int length, double ratio) {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource genes = FindandStoreAllGenes(dna);
        PrintCertainGenes(genes, length, ratio);
   }
   
    public void processDNAstrings() {
        FileResource fr = new FileResource();
        String dna = fr.asString();
        String string = dna.toLowerCase();
        int count = 0;
        int stop = 0;
        while (true) {
            stop = string.indexOf("ctg", stop);
            if (stop == -1) {break;}
            stop = stop + 3;
            count += 1;
        }
        System.out.println(count + " ctg stops");
   }
   
}
