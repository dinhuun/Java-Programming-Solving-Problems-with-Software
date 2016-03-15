/**
 * find and print the first line containing a given word in a given file
 * use StorageResource to store unique words in a given file
 * @DinhHuuNguyen
 * @01/08/2016
 */
import edu.duke.*;
import java.io.*;

public class wordFinder {
    
    public void findWisdom() {
		FileResource fr = new FileResource();
		for (String line : fr.lines()) {
			if (line.contains("wisdom")) {
				System.out.println(line);
				break;
			}
		}
	}
	
	public StorageResource storeUniqueWords(FileResource fr) {
        StorageResource store = new StorageResource();
        for (String word : fr.words()) {
            if (! store.contains(word)) {store.add(word);}
        }
        return store;
    }
    
	public void testgetUniqueWords() {
        FileResource fr = new FileResource();
        StorageResource store = storeUniqueWords(fr);
		for (String word : store.data()){
				System.out.println(word);
			}
		System.out.println("number of unique words " + store.size());
	}
	
}
