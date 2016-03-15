/**
 * Print the names of all files selected within a directory (or folder).
 * @author Duke Software Team 
 */
import edu.duke.*;
import java.io.*;
public class DirReader {
    
    public void checkDir() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }
    
    public void printNames() {
        DirectoryResource folder = new DirectoryResource();
        for (File name : folder.selectedFiles()) {
            System.out.println(name);
        }
    }
    
}